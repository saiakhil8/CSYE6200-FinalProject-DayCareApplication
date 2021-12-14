package edu.neu.csye6200.controllers;

import edu.neu.csye6200.views.ApplicationLayout;
import org.springframework.stereotype.Service;

/**
 * @author SaiAkhil
 */
@Service
public class LoginController extends AppViewsController {
    public LoginController(){
        System.out.println("This");
    }

    @Override
    public ApplicationLayout getAppPage() {
        return null;
    }
}
