package edu.neu.csye6200.controllers;

import com.google.gson.Gson;
import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.Utils.FunctionalUtilities;
import edu.neu.csye6200.Utils.Utils;
import edu.neu.csye6200.models.ImmunizationTracker;
import edu.neu.csye6200.models.Student;
import edu.neu.csye6200.models.Vaccine;
import edu.neu.csye6200.repositories.ImmunizationRepository;
import edu.neu.csye6200.repositories.StudentRepository;
import edu.neu.csye6200.repositories.VaccineRepository;
import edu.neu.csye6200.sessions.AuthenticationAndSessionManager;
import edu.neu.csye6200.views.ApplicationLayout;
import edu.neu.csye6200.views.ImmunizationLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author SaiAkhil
 */
@Service
public class ImmunizationController extends AppViewsController {

    @Autowired
    private ImmunizationRepository immunizationRepository;
    @Autowired
    private VaccineRepository vaccineRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public ApplicationLayout getAppPage() {
        return new ImmunizationLayout(Constants.getImageFromName("daycare_landing_background.jpg"), ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {

    }

    @Override
    protected void onCreate(Listeners.AppControlEventListener appControlListener) {
        super.onCreate(appControlListener);
        ((ImmunizationLayout) this.getCurrentFrame()).setDbCrudCallBack(dbCrudFunction);
        initData();
    }

    private void initData() {
        ImmunizationLayout immunizationLayout = ((ImmunizationLayout) this.getCurrentFrame());
        Optional<Student> studentOpt = studentRepository.findById(AuthenticationAndSessionManager.getInstance().getReqData());
        if (studentOpt.isEmpty()) return;
        Student student = studentOpt.get();
        Vaccine tempVaccine;
        ImmunizationTracker immunizationTracker = immunizationRepository.findTopByStudentId(AuthenticationAndSessionManager.getInstance().getReqData());
        String vaccineName = null;

        if (immunizationTracker != null) {
            Map<String, Object> master = immunizationTracker.getImmunizationDetailsMap();
            Map<String, String> temp = new HashMap<>();
            Date leastDate = null;
            String[] tempString = new String[1];
            if (master != null && master.containsKey("hib")) {
                temp = (Map<String, String>) master.get("hib");
                String[] hib = new String[4];
                leastDate = this.validateDates(hib, temp, vaccineRepository.findByVaccineName("HIB"), student.getDateOfBirth());
                immunizationLayout.refreshHibData(hib);
                vaccineName = "HIB";
            } else {
                String[] tempSt = new String[4];
                leastDate = this.validateDates(tempSt, temp, vaccineRepository.findByVaccineName("HIB"), student.getDateOfBirth());
                immunizationLayout.refreshHibData(tempSt);
            }

            Date gap = null;
            if (master != null && master.containsKey("dtap")) {
                temp = (Map<String, String>) master.get("dtap");
                String[] dtap = new String[4];
                gap = this.validateDates(dtap, temp, vaccineRepository.findByVaccineName("DTAP"), student.getDateOfBirth());
                immunizationLayout.refreshDtapData(dtap);
            } else {
                String[] tempSt = new String[4];
                gap = this.validateDates(tempSt, temp, vaccineRepository.findByVaccineName("DTAP"), student.getDateOfBirth());
                immunizationLayout.refreshDtapData(tempSt);
            }
            if (leastDate == null || (gap != null && leastDate.compareTo(gap) > 0)) {
                leastDate = gap;
                vaccineName = "DTAP";
            }

            if (master != null && master.containsKey("hepatitisb")) {
                temp = (Map<String, String>) master.get("hepatitisb");
                String[] hb = new String[3];
                gap = this.validateDates(hb, temp, vaccineRepository.findByVaccineName("HEPATITIS B"), student.getDateOfBirth());
                immunizationLayout.refreshHepetatisData(hb);
                if (leastDate == null || (gap != null && leastDate.compareTo(gap) > 0)) {
                    leastDate = gap;
                    vaccineName = "HEPATITIS B";
                }
            } else {
                String[] tempSt = new String[4];
                gap = this.validateDates(tempSt, temp, vaccineRepository.findByVaccineName("HEPATITIS B"), student.getDateOfBirth());
                immunizationLayout.refreshHepetatisData(tempSt);
            }
            if (leastDate == null || (gap != null && leastDate.compareTo(gap) > 0)) {
                leastDate = gap;
                vaccineName = "HEPATITIS B";
            }

            String mmr = null;
            if (master != null && master.containsKey("mmr")) {
                mmr = (String) master.get("mmr");
            } else {
                tempVaccine = vaccineRepository.findByVaccineName("MMR");
                gap = Utils.getDateAfterDays(Utils.getDateFromString(student.getDateOfBirth()), tempVaccine.getFirstDoseGap());
                mmr = "Dose Next Due: " + Utils.getDateString(gap);
                if (leastDate == null || leastDate.compareTo(gap) > 0) {
                    leastDate = gap;
                    vaccineName = "MMR";
                }
            }
            String varicella = null;
            if (master != null && master.containsKey("varicella")) {
                varicella = (String) master.get("varicella");
            } else {
                tempVaccine = vaccineRepository.findByVaccineName("varicella".toUpperCase());
                gap = Utils.getDateAfterDays(Utils.getDateFromString(student.getDateOfBirth()), tempVaccine.getFirstDoseGap());
                varicella = "Dose Next Due: " + Utils.getDateString(gap);
                if (leastDate == null || leastDate.compareTo(gap) > 0) {
                    leastDate = gap;
                    vaccineName = "varicella".toUpperCase();
                }
            }
            immunizationLayout.refreshMMRVaricellaData(mmr, varicella);

            if (vaccineName == null) return;
            String message = vaccineName + " Next Due On";
            immunizationTracker.setUpcomingDueDateMessage(message);
            immunizationTracker.setUpcomingDueDate(Utils.getDateString(leastDate));
            immunizationRepository.save(immunizationTracker);
        } else {
            Date least;
            vaccineName = "HIB";
            Date temp = this.getNextDueDate(student.getDateOfBirth(), vaccineRepository.findByVaccineName("HIB").getFirstDoseGap(), immunizationLayout::refreshHibData);
            least = temp;
            temp = this.getNextDueDate(student.getDateOfBirth(), vaccineRepository.findByVaccineName("DTAP").getFirstDoseGap(), immunizationLayout::refreshDtapData);
            if (least.compareTo(temp) > 0) {
                least = temp;
                vaccineName = "DTAP";
            }
            temp = this.getNextDueDate(student.getDateOfBirth(), vaccineRepository.findByVaccineName("HEPATITIS B").getFirstDoseGap(), immunizationLayout::refreshHepetatisData);
            if (least.compareTo(temp) > 0) {
                least = temp;
                vaccineName = "HEPATITIS B";
            }
            temp = this.getNextDueDate(student.getDateOfBirth(), vaccineRepository.findByVaccineName("MMR").getFirstDoseGap(), immunizationLayout::refreshMMRData);
            if (least.compareTo(temp) > 0) {
                least = temp;
                vaccineName = "MMR";
            }
            temp = this.getNextDueDate(student.getDateOfBirth(), vaccineRepository.findByVaccineName("varicella".toUpperCase()).getFirstDoseGap(), immunizationLayout::refreshVaricellaData);
            if (least.compareTo(temp) > 0) {
                least = temp;
                vaccineName = "varicella".toUpperCase();
            }
            String message = vaccineName + " Next Due On";
            immunizationTracker = new ImmunizationTracker();
            immunizationTracker.setStudentId(student.getId());
            immunizationTracker.setUpcomingDueDateMessage(message);
            immunizationTracker.setUpcomingDueDate(Utils.getDateString(least));
            immunizationRepository.save(immunizationTracker);
        }

    }

    private Date getNextDueDate(String dob, int gap, Consumer<String> consumer) {
        Date date = Utils.getDateAfterDays(Utils.getDateFromString(dob), gap);
        consumer.accept("Dose 1 Next Due: " + Utils.getDateString(date));
        return date;
    }

    private Date validateDates(String[] data, Map<String, String> map, Vaccine vaccine, String dob) {
        int i = 0;
        for (i = 0; i < data.length; i++) {
            if (!map.containsKey(Integer.toString(i + 1))) {
                break;
            } else data[i] = map.get(Integer.toString(i + 1));
        }
        if (i < data.length) {
            int gap = getGapBasedOnIndex(i + 1, vaccine);
            Date date;
            if (i == 0) date = Utils.getDateAfterDays(Utils.getDateFromString(dob), gap);
            else date = Utils.getDateAfterDays(Utils.getDateFromString(data[i - 1]), gap);
            data[i] = "Dose " + (i + 1) + " Next Due: " + Utils.getDateString(date);
        }
        return null;
    }

    private int getGapBasedOnIndex(int index, Vaccine vaccine) {
        switch (index) {
            case 1:
                return vaccine.getFirstDoseGap();
            case 2:
                return vaccine.getSecondDoseGap();
            case 3:
                return vaccine.getThirdDoseGap();
            case 4:
                return vaccine.getFourthDoseGap();
        }
        return -1;
    }

    protected FunctionalUtilities.BiFunctionWithReturnType<Object, Integer, Boolean> dbCrudFunction = (map, eventType) -> {
        String data = new Gson().toJson((Map<String, Object>) map);
        ImmunizationTracker immunizationTracker = immunizationRepository.findByStudentId(AuthenticationAndSessionManager.getInstance().getReqData());
        if (immunizationTracker == null)
            immunizationTracker = new ImmunizationTracker(AuthenticationAndSessionManager.getInstance().getReqData(), data, "", "");
        immunizationTracker.setImmunizationDetails(data);
        this.immunizationRepository.save(immunizationTracker);
        this.initData();
        return true;
    };

}
