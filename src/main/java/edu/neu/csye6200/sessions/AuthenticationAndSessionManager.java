package edu.neu.csye6200.sessions;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.Utils.FunctionalUtilities;
import edu.neu.csye6200.controllers.ApplicationController;
import edu.neu.csye6200.models.Admin;
import edu.neu.csye6200.models.Person;
import edu.neu.csye6200.models.Student;
import edu.neu.csye6200.models.Teacher;

import java.sql.SQLException;

/**
 * @author SaiAkhil
 */
public class AuthenticationAndSessionManager {

    public static AuthenticationAndSessionManager mInstance;
    private Person currentPerson;
    private Listeners.SessionManager sessionManagementListener;

    private AuthenticationAndSessionManager() {
    }

    ;

    public void authenticate(String userName, String password, FunctionalUtilities.BiFunction<Boolean, String> result) {
        assert sessionManagementListener != null;
        try {
            this.currentPerson = sessionManagementListener.validateAdmin(userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (this.currentPerson != null) {
            result.accept(true, "Login Success");
            sessionManagementListener.onNewSessionEvent(this.getLoggedInUserType());
        } else {
            result.accept(false, "Invalid UserName/Password");
            sessionManagementListener.onNewSessionEvent(Constants.SESSION_FAILED_AUTH);
        }
    }

    public void setSessionManagementListener(Listeners.SessionManager sessionManagementListener) {
        assert sessionManagementListener instanceof ApplicationController;
        this.sessionManagementListener = sessionManagementListener;
    }

    public void logOut() {
        this.currentPerson = null;
        this.sessionManagementListener.onNewSessionEvent(Constants.EVENT_LOGOUT);
    }

    public int getLoggedInUserType() {
        if (currentPerson == null) return Constants.SESSION_INVALID;
        else if (currentPerson instanceof Admin) return Constants.SESSION_ADMIN;
        else if (currentPerson instanceof Teacher) return Constants.SESSION_TEACHER;
        else if (currentPerson instanceof Student) return Constants.SESSION_PARENT;
        else return Constants.SESSION_INVALID;
    }

    public static AuthenticationAndSessionManager getInstance() {
        if (mInstance == null) {
            synchronized (AuthenticationAndSessionManager.class) {
                mInstance = new AuthenticationAndSessionManager();
            }
        }
        return mInstance;
    }
}
