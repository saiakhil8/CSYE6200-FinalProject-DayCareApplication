package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.views.ApplicationLayout;

/**
 * @author SaiAkhil
 */
public abstract class AppViewsController implements Listeners.EventListener {

    private final ApplicationLayout currentFrame;
    private Listeners.AppControlEventListener appControlListener;

    public AppViewsController(){
        this.currentFrame = this.getAppPage();
        this.currentFrame.setEventListener(this);
    }

    public abstract ApplicationLayout getAppPage();

    protected void onPagePushedToBackground(){
        this.currentFrame.onLostFocus();
        this.appControlListener = null;
    }

    protected void onPagePushedToForeground(Listeners.AppControlEventListener appControlListener){
        this.appControlListener = appControlListener;
        this.currentFrame.onGainedFocus();
    }

    @Override
    public void onEvent(int eventType) {
        if (eventType== Constants.EVENT_NEXT_SCREEN &&
                this.appControlListener!=null) this.appControlListener.onGoToNextScreenEvent(LoginController.class);
    }
}
