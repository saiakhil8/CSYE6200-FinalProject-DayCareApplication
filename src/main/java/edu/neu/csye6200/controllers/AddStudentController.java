package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.ThreadManager;
import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.Utils.FunctionalUtilities;
import edu.neu.csye6200.models.ClassRules;
import edu.neu.csye6200.models.ClassSections;
import edu.neu.csye6200.models.Student;
import edu.neu.csye6200.models.Teacher;
import edu.neu.csye6200.repositories.ClassRoomRepository;
import edu.neu.csye6200.repositories.ClassRulesRepository;
import edu.neu.csye6200.repositories.StudentRepository;
import edu.neu.csye6200.repositories.TeacherRepository;
import edu.neu.csye6200.sessions.AuthenticationAndSessionManager;
import edu.neu.csye6200.views.AddStudentLayout;
import edu.neu.csye6200.views.ApplicationLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SaiAkhil
 */
@Service
public class AddStudentController extends AppViewsController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRulesRepository classRulesRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ClassRoomRepository classRoomRepository;

    private ThreadManager threadManager;

    @Override
    public ApplicationLayout getAppPage() {
        return new AddStudentLayout(Constants.getImageFromName("teacher.jpeg"), ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }

    @Override
    protected void onCreate(Listeners.AppControlEventListener appControlListener) {
        super.onCreate(appControlListener);
        this.threadManager = ThreadManager.getInstance();
        ((AddStudentLayout) this.getCurrentFrame()).setDbCrudCallBack(dbCrudFunction);
    }

    protected FunctionalUtilities.BiFunctionWithReturnType<Object, Integer, Boolean> dbCrudFunction = (student, eventType) -> {
        Student temp = studentRepository.save((Student) student);
        threadManager.getFixedPoolThread().execute(() -> {
            mapStudentToClass(temp);
            AuthenticationAndSessionManager.getInstance().sendEmail(((Student) student).getEmailId(), "Daycare Account created", "" +
                    "Hi,\n\n Your Account created and mapped to a class & teahcer. Your Password is " + ((Student) student).getPassword() + " .Login for more details. \n\n Thanks");
        });
        return true;
    };

    private void mapStudentToClass(Student student) {
        ClassSections classSections = classRoomRepository.findTopByMinAgeBeforeAndMaxAgeAfterOrderByClassRoomId(student.getAge() + 1, student.getAge() - 1);
        if (classSections == null || classSections.getCurrentCapacity() >= classSections.getMaxCapacity()) {
            ClassRules classRules = classRulesRepository.findTopByMinAgeBeforeAndMaxAgeAfter(student.getAge() + 1, student.getAge() - 1);
            Teacher finalized = teacherRepository.findTopByAssignedClassRoomIdOrderById(0);
            if (finalized == null) {
                System.out.println("No teachers available to assist");
                return;
            }
            classSections = new ClassSections(classRules.getClassId(), classRules.getMinAge(),
                    classRules.getMaxAge(), classRules.getMaxGroupsPerClassRoom() * classRules.getStudentTeacherRation(),
                    classRules.getStudentTeacherRation(), String.format("#%d#", student.getId()),
                    student.getFirstName(), String.format("#%d#", finalized.getId()), finalized.getFirstName());
            classRoomRepository.save(classSections);
            finalized.setAssignedClassRoomId(classSections.getClassRoomId());
            teacherRepository.save(finalized);
            return;
        }
        if (classSections.getCurrentCapacity() % classSections.getGroupSize() != 0) {
            classSections.addStudent(student.getId(), student.getFirstName());
            classRoomRepository.save(classSections);
        } else {
            Teacher finalized = teacherRepository.findTopByAssignedClassRoomIdOrderById(0);
            if (finalized == null) {
                System.out.println("No teachers available to assist");
                return;
            }
            classSections.addStudent(student.getId(), student.getFirstName(), finalized.getId(), finalized.getFirstName());
            finalized.setAssignedClassRoomId(classSections.getClassRoomId());
            classRoomRepository.save(classSections);
            teacherRepository.save(finalized);
        }
    }


    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {
        appControlListener.onGoToNextScreenEvent(ViewAllStudentsController.class);
    }
}
