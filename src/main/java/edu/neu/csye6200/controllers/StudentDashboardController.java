package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.views.ApplicationLayout;
import edu.neu.csye6200.views.StudentDashboardLayout;
import org.springframework.stereotype.Service;

/**
 * @author SaiAkhil
 */
@Service
public class StudentDashboardController extends AppViewsController {
    @Override
    public ApplicationLayout getAppPage() {
        return new StudentDashboardLayout(Constants.getImageFromName("teacher.jpeg"), ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {

    }
}
