package edu.neu.csye6200.controllers;


import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.views.ApplicationView;
import edu.neu.csye6200.views.LandingPageFrame;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author SaiAkhil
 */
@Service
@Lazy
public class LandingPageController extends AppViewsController {

    @Override
    public ApplicationView getAppPage() {
        return new LandingPageFrame("./src/main/resources/daycare_landing_background.jpg",ApplicationView.BACKGROUND_TYPE_IMAGE);
    }
}
