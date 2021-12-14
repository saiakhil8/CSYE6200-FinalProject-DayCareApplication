package edu.neu.csye6200;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author SaiAkhil
 */
public class ThreadManager {

    private static ThreadManager mInstance;

    private Executor mainThread;

    private ThreadManager(){
        this.mainThread = Executors.newSingleThreadExecutor();
    }

    public Executor getMainThreadExecutor() {
        return mainThread;
    }

    public static synchronized ThreadManager getInstance(){
        if (mInstance==null){
            synchronized (ThreadManager.class){
                mInstance = new ThreadManager();
            }
        }
        return mInstance;
    }

}
