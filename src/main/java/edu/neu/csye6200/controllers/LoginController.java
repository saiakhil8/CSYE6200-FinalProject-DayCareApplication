package edu.neu.csye6200.controllers;

import edu.neu.csye6200.views.ApplicationLayout;
import edu.neu.csye6200.views.LoginPageLayout;
import org.springframework.stereotype.Service;

/**
 * @author SaiAkhil
 */
@Service
public class LoginController extends AppViewsController {
    @Override
    public ApplicationLayout getAppPage() {
        return new LoginPageLayout("./src/main/resources/daycare_landing_background.jpg", ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }
}
