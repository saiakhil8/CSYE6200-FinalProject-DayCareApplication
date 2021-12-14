package edu.neu.csye6200;

/**
 * @author SaiAkhil
 */
public class Listeners {
    public interface EventListener{
        void onEvent(int eventType);
    }

    public interface AppControlEventListener {
        /**
         * Will be called when user wants to navigate to next page
         *
         * @param appViewsController -=
         * @param <T>
         */
        <T> void onGoToNextScreenEvent(Class<T> appViewsController);

        /**
         * Fired when for removing current view from the stack
         */
        void onBackPressed();
    }
}
