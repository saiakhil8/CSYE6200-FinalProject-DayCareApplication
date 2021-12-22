package edu.neu.csye6200;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author SaiAkhil
 */
public class ThreadManager {

    private static ThreadManager mInstance;

    private Executor mainThread;
    private Executor fixedPoolThread;

    private ThreadManager() {
    }

    public Executor getMainThreadExecutor() {
        if (mainThread == null) this.mainThread = Executors.newSingleThreadExecutor();
        return mainThread;
    }

    public Executor getFixedPoolThread() {
        if (fixedPoolThread == null) this.fixedPoolThread = Executors.newFixedThreadPool(3);
        return fixedPoolThread;
    }

    public static synchronized ThreadManager getInstance() {
        if (mInstance == null) {
            synchronized (ThreadManager.class) {
                mInstance = new ThreadManager();
            }
        }
        return mInstance;
    }

}
