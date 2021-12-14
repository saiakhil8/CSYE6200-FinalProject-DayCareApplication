package edu.neu.csye6200.controllers;


import edu.neu.csye6200.views.ApplicationLayout;
import edu.neu.csye6200.views.LandingPageLayout;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author SaiAkhil
 */
@Service
@Lazy
public class LandingPageController extends AppViewsController {

    @Override
    public ApplicationLayout getAppPage() {
        return new LandingPageLayout("./src/main/resources/daycare_landing_background.jpg", ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }
}
