package edu.neu.csye6200;

/**
 * @author SaiAkhil
 */
public class Listeners {
    public interface EventListener{
        void onEvent(int eventType);
    }

    public interface AppControlEventListener{
        <T> void onGoToNextScreenEvent(Class<T> appViewsController);

        void onBackPressed();
    }
}
