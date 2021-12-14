package edu.neu.csye6200.sessions;

import org.springframework.stereotype.Service;

/**
 * @author SaiAkhil
 */
@Service
public class SessionManager {


    private SessionManager(){

    }


    private static SessionManager mInstance;
    public static synchronized SessionManager getInstance(){
        if (mInstance==null) mInstance = new SessionManager();
        return mInstance;
    }

}
