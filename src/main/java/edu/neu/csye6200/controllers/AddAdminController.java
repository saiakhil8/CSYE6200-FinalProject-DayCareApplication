package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.views.AddAdminLayout;
import edu.neu.csye6200.views.ApplicationLayout;

/**
 * @author SaiAkhil
 */
public class AddAdminController extends AppViewsController {
    @Override
    public ApplicationLayout getAppPage() {
        return new AddAdminLayout(Constants.getImageFromName("teacher.jpeg"), ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {

    }
}
