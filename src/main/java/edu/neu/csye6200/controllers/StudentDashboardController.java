package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.Utils.Utils;
import edu.neu.csye6200.models.ClassSections;
import edu.neu.csye6200.models.ImmunizationTracker;
import edu.neu.csye6200.models.Student;
import edu.neu.csye6200.repositories.ClassRoomRepository;
import edu.neu.csye6200.repositories.ImmunizationRepository;
import edu.neu.csye6200.repositories.VaccineRepository;
import edu.neu.csye6200.sessions.AuthenticationAndSessionManager;
import edu.neu.csye6200.views.ApplicationLayout;
import edu.neu.csye6200.views.StudentDashboardLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Consumer;

/**
 * @author SaiAkhil
 */
@Service
public class StudentDashboardController extends AppViewsController {

    @Autowired
    private ClassRoomRepository classRoomRepository;
    @Autowired
    private ImmunizationRepository immunizationRepository;
    @Autowired
    private VaccineRepository vaccineRepository;

    @Override
    public ApplicationLayout getAppPage() {
        return new StudentDashboardLayout(Constants.getImageFromName("teacher.jpeg"), ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }

    @Override
    protected void onCreate(Listeners.AppControlEventListener appControlListener) {
        super.onCreate(appControlListener);
        this.initData();
        this.initImmunization();
    }

    @Override
    protected void onPagePushedToForeground(Listeners.AppControlEventListener appControlListener) {
        super.onPagePushedToForeground(appControlListener);
        this.initImmunization();
    }

    private void initImmunization() {
        ImmunizationTracker immunizationTracker = this.immunizationRepository.findByStudentId(this.getCurrentLoggedInUser().getId());
        String immunizationDue = "NA";
        String immunizationMessage = "Vaccine Due";
        if (immunizationTracker != null) {
            immunizationDue = immunizationTracker.getUpcomingDueDate();
            immunizationMessage = immunizationTracker.getUpcomingDueDateMessage();
        } else {
            Date least;
            Student student = (Student) this.getCurrentLoggedInUser();
            String vaccineName = "HIB";
            Date temp = this.getNextDueDate(student.getDateOfBirth(), vaccineRepository.findByVaccineName("HIB").getFirstDoseGap(), (str) -> {
            });
            least = temp;
            temp = this.getNextDueDate(student.getDateOfBirth(), vaccineRepository.findByVaccineName("DTAP").getFirstDoseGap(), (str) -> {
            });
            if (least.compareTo(temp) > 0) {
                least = temp;
                vaccineName = "DTAP";
            }
            temp = this.getNextDueDate(student.getDateOfBirth(), vaccineRepository.findByVaccineName("HEPATITIS B").getFirstDoseGap(), (str) -> {
            });
            if (least.compareTo(temp) > 0) {
                least = temp;
                vaccineName = "HEPATITIS B";
            }
            temp = this.getNextDueDate(student.getDateOfBirth(), vaccineRepository.findByVaccineName("MMR").getFirstDoseGap(), (str) -> {
            });
            if (least.compareTo(temp) > 0) {
                least = temp;
                vaccineName = "MMR";
            }
            temp = this.getNextDueDate(student.getDateOfBirth(), vaccineRepository.findByVaccineName("varicella".toUpperCase()).getFirstDoseGap(), (str) -> {
            });
            if (least.compareTo(temp) > 0) {
                least = temp;
                vaccineName = "varicella".toUpperCase();
            }
            immunizationMessage = vaccineName + " Next Due On";
            immunizationTracker = new ImmunizationTracker();
            immunizationTracker.setStudentId(student.getId());
            immunizationTracker.setUpcomingDueDateMessage(immunizationMessage);
            immunizationTracker.setUpcomingDueDate(Utils.getDateString(least));
            immunizationDue = immunizationTracker.getUpcomingDueDate();
            immunizationRepository.save(immunizationTracker);
        }
        ((StudentDashboardLayout) this.getCurrentFrame()).refreshCards(immunizationMessage, immunizationDue);
    }

    private Date getNextDueDate(String dob, int gap, Consumer<String> consumer) {
        Date date = Utils.getDateAfterDays(Utils.getDateFromString(dob), gap);
        consumer.accept("Dose 1 Next Due: " + Utils.getDateString(date));
        return date;
    }

    private void initData() {

        ClassSections classSection = classRoomRepository.findTopByStudentIdsContaining("#" + this.getCurrentLoggedInUser().getId() + "#");

        int classRoomId = 0;
        String teacherName = "";
        if (classSection != null) {
            classRoomId = classSection.getClassRoomId();
            String[] studentIds = classSection.getStudentIds().replaceAll("#", "").split(",");
            int studentNumber = 0;
            for (int i = 0; i < studentIds.length; i++) {
                if (studentIds[i].equals(Integer.toString(this.getCurrentLoggedInUser().getId()))) {
                    studentNumber = i;
                }
            }
            int groupId = (studentNumber + 1) / classSection.getGroupSize();
            String[] teacherNames = classSection.getTeacherFirstNames().split(",");
            teacherName = teacherNames[groupId];
        }


        ((StudentDashboardLayout) this.getCurrentFrame()).refreshCards(classRoomId, teacherName);

    }

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {
        AuthenticationAndSessionManager.getInstance().setReqData(this.getCurrentLoggedInUser().getId());
        appControlListener.onGoToNextScreenEvent(ImmunizationController.class);
    }
}
