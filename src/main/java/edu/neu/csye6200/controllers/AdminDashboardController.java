package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.repositories.*;
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
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ClassRulesRepository classRulesRepository;
    @Autowired
    private ClassRoomRepository classRoomRepository;

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
            case EVENT_GOTO_ADD_ADMIN: {
                appControlListener.onGoToNextScreenEvent(AddAdminController.class);
                break;
            }
            case EVENT_GOTO_VIEW_ADMIN: {
                appControlListener.onGoToNextScreenEvent(ViewAllAdminsController.class);
                break;
            }
            case EVENT_GOTO_CLASS_RULES: {
                appControlListener.onGoToNextScreenEvent(ClassRulesCrudController.class);
                break;
            }
            case EVENT_GOTO_CLASS_ROOMS: {
                appControlListener.onGoToNextScreenEvent(ViewAllClassRoomsController.class);
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
    public static final int REQUEST_TYPE_ADMIN_COUNT = 8004;
    public static final int REQUEST_TYPE_CLASS_RULES_COUNT = 8005;
    public static final int EVENT_GOTO_ADD_TEACHER = 7002;
    public static final int EVENT_GOTO_ADD_STUDENT = 7003;
    public static final int EVENT_GOTO_VIEW_STUDENT = 7004;
    public static final int EVENT_GOTO_VIEW_TEACHER = 7005;
    public static final int EVENT_GOTO_VIEW_ADMIN = 7006;
    public static final int EVENT_GOTO_ADD_ADMIN = 7007;
    public static final int EVENT_GOTO_CLASS_RULES = 7008;
    public static final int EVENT_GOTO_CLASS_ROOMS = 7009;

    @Override
    protected void onPagePushedToForeground(Listeners.AppControlEventListener appControlListener) {
        super.onPagePushedToForeground(appControlListener);
        ((AdminDashboardLayout) this.getCurrentFrame()).refreshCards();
    }

    @Override
    public int getIntegerData(int dataType) {
        switch (dataType) {
            case REQUEST_TYPE_STUDENT_COUNT:
                return (int) this.studentRepository.count();
            case REQUEST_TYPE_TEACHER_COUNT:
                return (int) this.teacherRepository.count();
            case REQUEST_TYPE_ADMIN_COUNT:
                return (int) this.adminRepository.count();
            case REQUEST_TYPE_CLASS_RULES_COUNT:
                return (int) this.classRulesRepository.count();
            case REQUEST_TYPE_CLASSROOM_COUNT:
                return (int) this.classRoomRepository.count();
        }
        return super.getIntegerData(dataType);
    }
}
