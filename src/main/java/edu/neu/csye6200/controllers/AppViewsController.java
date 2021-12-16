package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.sessions.AuthenticationAndSessionManager;
import edu.neu.csye6200.views.ApplicationLayout;

import javax.annotation.PostConstruct;

/**
 * @author SaiAkhil
 */
public abstract class AppViewsController implements Listeners.EventListener {

    private ApplicationLayout currentFrame;
    private Listeners.AppControlEventListener appControlListener;
    private AuthenticationAndSessionManager authenticationAndSessionManager;

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

    @PostConstruct
    public void init() {
        this.authenticationAndSessionManager = AuthenticationAndSessionManager.getInstance();
    }

    protected void processEvent(int eventType, Listeners.AppControlEventListener appControlListener) {
    }

    @Override
    public void onEvent(int eventType) {
        if (eventType == Constants.EVENT_NEXT_SCREEN &&
                this.appControlListener != null) this.goToNextScreen(this.appControlListener);
        else if (eventType == Constants.EVENT_GO_BACK) {
            this.onBackPressed();
        } else if (eventType == Constants.EVENT_LOGOUT) this.authenticationAndSessionManager.logOut();
        else this.processEvent(eventType, appControlListener);
    }

    protected abstract void goToNextScreen(Listeners.AppControlEventListener appControlListener);

    protected void onBackPressed() {
        this.appControlListener.onBackPressed();
    }

    protected void onDestroy() {
        this.onPagePushedToBackground();
        this.currentFrame.dispose();
        this.currentFrame = null;
    }

    protected int getLoggedInUserType() {
        return this.authenticationAndSessionManager.getLoggedInUserType();
    }

    @Override
    public int getIntegerData(int dataType) {
        return 0;
    }

    protected ApplicationLayout getCurrentFrameLayout() {
        return this.currentFrame;
    }

    protected ApplicationLayout getCurrentFrame() {
        return this.currentFrame;
    }
}
