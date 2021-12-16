package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.views.ApplicationLayout;
import edu.neu.csye6200.views.TeacherDashboardLayout;
import org.springframework.stereotype.Service;

/**
 * @author SaiAkhil
 */
@Service
public class TeacherDashboardController extends AppViewsController {
    public static final int EVENT_GOTO_VIEW_STUDENT = 71801;

    @Override
    public ApplicationLayout getAppPage() {
        return new TeacherDashboardLayout(Constants.getImageFromName("teacher.jpeg"), ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }

    @Override
    protected void processEvent(int eventType, Listeners.AppControlEventListener appControlListener) {
        super.processEvent(eventType, appControlListener);
        switch (eventType) {
            case EVENT_GOTO_VIEW_STUDENT: {
                appControlListener.onGoToNextScreenEvent(ViewAllStudentsController.class);
                break;
            }
        }
    }

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {

    }
}
