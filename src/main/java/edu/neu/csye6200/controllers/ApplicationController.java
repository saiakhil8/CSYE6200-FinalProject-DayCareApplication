package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.models.Person;
import edu.neu.csye6200.repositories.AdminRepository;
import edu.neu.csye6200.repositories.StudentRepository;
import edu.neu.csye6200.repositories.TeacherRepository;
import edu.neu.csye6200.sessions.AuthenticationAndSessionManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.Stack;

/**
 * @author SaiAkhil
 */
@Service
@Scope("singleton")
public class ApplicationController implements ApplicationContextAware, Listeners.AppControlEventListener, Listeners.SessionManager {

    @Value("${application.name}")
    private String appName;
    @Value("${application.appBackground}")
    protected String defaultAppBackground;
    @Value("${application.backgroundType}")
    protected int defaultBackgroundType;
    @Value("${application.preferredWidth}")
    private int preferredWidth;
    @Value("${application.preferredHeight}")
    private int preferredHeight;

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;

    private final AuthenticationAndSessionManager authenticationAndSessionManager;

    {
        this.authenticationAndSessionManager = AuthenticationAndSessionManager.getInstance();
    }

    /**
     * Used to maintain UI Frames Stack
     * OnBackPressed currentStack will be removed
     * New Will be added onNextScreen
     */
    private Stack<AppViewsController> applicationStack;

    /**
     * Application context used to get respective beans onDemand
     */
    private ApplicationContext applicationContext;


    /**
     * Used to initialize stack and push first most ViewFrame
     * Method will be called post construct (On Created)
     */
    @PostConstruct
    public void onControllerCreated(){
        this.authenticationAndSessionManager.setSessionManagementListener(this);
        Constants.APP_PREFERRED_HEIGHT = this.preferredHeight;
        Constants.APP_PREFERRED_WIDTH = this.preferredWidth;
        Constants.APP_NAME = this.appName;
        applicationStack = new Stack<>();
        pushAndShowPage(applicationContext.getBean(AdminDashboardController.class));
    }

    private void pushAndShowPage(AppViewsController controller){
        if (!this.applicationStack.isEmpty()) this.applicationStack.peek().onPagePushedToBackground();
        this.applicationStack.push(controller);
        this.applicationStack.peek().onCreate(this);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public <T> void onGoToNextScreenEvent(Class<T> appViewsController) {
        this.pushAndShowPage((AppViewsController) this.applicationContext.getBean(appViewsController));
    }

    @Override
    public void onBackPressed() {
        this.applicationStack.pop().onDestroy();
        this.applicationStack.peek().onPagePushedToForeground(this);
    }

    private void onLogin() {
        this.applicationStack.pop().onDestroy();
        switch (authenticationAndSessionManager.getLoggedInUserType()) {
            case Constants.SESSION_ADMIN:
                this.onGoToNextScreenEvent(AdminDashboardController.class);
                break;
            case Constants.SESSION_TEACHER:
                this.onGoToNextScreenEvent(TeacherDashboardController.class);
                break;
            case Constants.SESSION_PARENT:
                this.onGoToNextScreenEvent(StudentDashboardController.class);
                break;
            default:
                this.onGoToNextScreenEvent(LandingPageController.class);
        }
    }

    @Override
    public Person validateAdmin(String userName, String password) throws SQLException {
        return adminRepository.getByEmailIdAndPassword(userName, password);
    }

    @Override
    public Person validateTeacher(String userName, String password) throws SQLException {
        return teacherRepository.getByEmailIdAndPassword(userName, password);
    }

    @Override
    public Person validateParent(String userName, String password) throws SQLException {
        return studentRepository.getByEmailIdAndPassword(userName, password);
    }

    private void logoutUser() {
        for (int i = 0; i < this.applicationStack.size(); i++) {
            this.applicationStack.pop().onDestroy();
        }
        this.pushAndShowPage(applicationContext.getBean(LandingPageController.class));
    }

    @Override
    public void onNewSessionEvent(int eventType) {
        //do something
        if (eventType == Constants.SESSION_INVALID || eventType == Constants.EVENT_LOGOUT) {
            this.logoutUser();
        } else if (eventType > 5010 && eventType < 5999) {
            this.onLogin();
        }
    }
}
