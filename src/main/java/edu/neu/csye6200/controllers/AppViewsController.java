package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.views.ApplicationLayout;

/**
 * @author SaiAkhil
 */
public abstract class AppViewsController implements Listeners.EventListener {

    private ApplicationLayout currentFrame;
    private Listeners.AppControlEventListener appControlListener;

    public abstract ApplicationLayout getAppPage();

    protected void onPagePushedToBackground() {
        this.currentFrame.onLostFocus();
        this.appControlListener = null;
    }

    protected void onPagePushedToForeground(Listeners.AppControlEventListener appControlListener) {
        this.appControlListener = appControlListener;
        this.currentFrame.onGainedFocus();
    }

    protected void onCreate(Listeners.AppControlEventListener appControlListener) {
        this.currentFrame = this.getAppPage();
        this.currentFrame.setEventListener(this);
        this.onPagePushedToForeground(appControlListener);
    }

    @Override
    public void onEvent(int eventType) {
        if (eventType == Constants.EVENT_NEXT_SCREEN &&
                this.appControlListener != null) this.goToNextScreen(this.appControlListener);
        else if (eventType == Constants.EVENT_LOGOUT) this.onBackPressed();
    }

    protected abstract void goToNextScreen(Listeners.AppControlEventListener appControlListener);

    protected void onBackPressed() {
        this.appControlListener.onBackPressed();
    }

    protected void onDestroy() {
        this.onPagePushedToBackground();
        this.currentFrame = null;
    }
}
