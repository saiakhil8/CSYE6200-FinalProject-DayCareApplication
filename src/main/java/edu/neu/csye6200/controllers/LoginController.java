package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.views.ApplicationLayout;
import edu.neu.csye6200.views.LoginPageLayout;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author SaiAkhil
 */
@Service
@Lazy
public class LoginController extends AppViewsController {
    LoginPageLayout loginPageLayout;

    @Override
    public ApplicationLayout getAppPage() {
        this.loginPageLayout = new LoginPageLayout("./src/main/resources/daycare_landing_background.jpg", ApplicationLayout.BACKGROUND_TYPE_IMAGE);
        this.loginPageLayout.setLoginListener((username, password, result) -> {
            //validate session details;
        });
        return this.loginPageLayout;
    }

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {

    }


}
