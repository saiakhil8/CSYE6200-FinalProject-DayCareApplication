package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.views.ApplicationLayout;
import org.springframework.stereotype.Service;

/**
 * @author SaiAkhil
 */
@Service
public class AdminDashboardController extends AppViewsController {

    @Override
    public ApplicationLayout getAppPage() {
        return null;
    }

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {

    }
}
