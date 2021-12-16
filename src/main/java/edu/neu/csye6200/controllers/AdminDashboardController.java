package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.repositories.StudentRepository;
import edu.neu.csye6200.repositories.TeacherRepository;
import edu.neu.csye6200.views.AdminDashboardLayout;
import edu.neu.csye6200.views.ApplicationLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SaiAkhil
 */
@Service
public class AdminDashboardController extends AppViewsController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public ApplicationLayout getAppPage() {
        return new AdminDashboardLayout("./src/main/resources/daycare_landing_background.jpg", ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }

    @Override
    public void onEvent(int eventType) {
        super.onEvent(eventType);
    }

    @Override
    protected void processEvent(int eventType, Listeners.AppControlEventListener appControlListener) {
        switch (eventType) {
            case EVENT_GOTO_ADD_STUDENT: {
                appControlListener.onGoToNextScreenEvent(AddStudentController.class);
                break;
            }
            case EVENT_GOTO_ADD_TEACHER: {
                appControlListener.onGoToNextScreenEvent(AddTeacherController.class);
                break;
            }
            case EVENT_GOTO_VIEW_TEACHER: {
                appControlListener.onGoToNextScreenEvent(ViewAllTeachersController.class);
                break;
            }
            case EVENT_GOTO_VIEW_STUDENT: {
                appControlListener.onGoToNextScreenEvent(ViewAllStudentsController.class);
                break;
            }
        }
    }

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {

    }

    public static final int REQUEST_TYPE_STUDENT_COUNT = 8001;
    public static final int REQUEST_TYPE_TEACHER_COUNT = 8002;
    public static final int REQUEST_TYPE_CLASSROOM_COUNT = 8003;
    public static final int REQUEST_TYPE_GROUPS_COUNT = 8003;
    public static final int EVENT_GOTO_ADD_TEACHER = 7002;
    public static final int EVENT_GOTO_ADD_STUDENT = 7003;
    public static final int EVENT_GOTO_VIEW_STUDENT = 7004;
    public static final int EVENT_GOTO_VIEW_TEACHER = 7005;

    @Override
    public int getIntegerData(int dataType) {
        switch (dataType) {
            case REQUEST_TYPE_STUDENT_COUNT:
                return (int) this.studentRepository.count();
            case REQUEST_TYPE_TEACHER_COUNT:
                return (int) this.teacherRepository.count();
        }
        return super.getIntegerData(dataType);
    }
}
