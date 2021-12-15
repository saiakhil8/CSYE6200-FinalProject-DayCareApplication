package edu.neu.csye6200.views;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.views.CustomViews.ImageView;
import org.springframework.lang.NonNull;

import javax.swing.*;
import java.awt.*;

/**
 * BaseLayout for this entire application
 *
 * @author SaiAkhil
 */
public abstract class ApplicationLayout extends JFrame {

    /**
     * Listener which calls parent controller informing changes or requesting data
     */
    protected Listeners.EventListener eventListener;

    /**
     * Main Constructor which will initialize components for the view {Everything will happen in initial thread}
     * Donot place any class variables since thread will be changed after this
     *
     * @param imagePathOrColor that needs to be set as background
     * @param backgroundType   is the type of background {{Example: IMAGE ; Color}}
     */
    public ApplicationLayout(@NonNull String imagePathOrColor, int backgroundType) {
        super();
        this.setTitle(Constants.APP_NAME);
        this.setPreferredSize(new Dimension(Constants.APP_PREFERRED_WIDTH, Constants.APP_PREFERRED_HEIGHT));
        this.setContentPane(new ImageView((backgroundType == BACKGROUND_TYPE_IMAGE)
                ? ImageView.IMAGE_PAINTER : ImageView.GRADIENT_PAINTER,
                imagePathOrColor));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Component currentRootLayout = this.getMainLayoutComponent();
        currentRootLayout.setPreferredSize(this.getPreferredSize());
        this.getContentPane().add(currentRootLayout);
        this.initComponents();
        this.pack();
    }

    /**
     * Component returned by this will be attached to the main frame
     *
     * @return component that needs to be attached to this layout
     */
    protected abstract Component getMainLayoutComponent();

    /**
     * Initialize Swing components here
     * Mark://Do not use any Event related things here
     */
    protected abstract void initComponents();

    /**
     * Called when view is pushed to foreground
     */
    public void onGainedFocus() {
        this.setVisible(true);
    }

    /**
     * Called when view is pushed to background
     */
    public void onLostFocus() {
        this.setVisible(false);
    }

    /**
     * Sets event listener for communication between Controller and this class
     * OnCreate will be called through this
     * Method will be called from Controller only once Layout is moved from initial to EventDisptachThread
     *
     * @param eventListener which will call controller
     */
    public void setEventListener(Listeners.EventListener eventListener) {
        this.eventListener = eventListener;
        this.onCreate();
    }

    /**
     * Will be called once UI is moved from initial thread to EventThread
     */
    protected void onCreate() {
    }

    ;

    /**
     * Will fire eventListener requesting next page navigation
     */
    protected void goToNextPage() {
        this.onNewEvent(Constants.EVENT_NEXT_SCREEN);
    }

    /**
     * Will call Parent Controller informing the event
     *
     * @param eventType as required {Example: Event Type Next Screen will request AppController to navigate to new layout}
     */
    protected void onNewEvent(int eventType) {
        if (this.eventListener != null) this.eventListener.onEvent(eventType);
    }

    public static int BACKGROUND_TYPE_IMAGE = 11051;
    public static int BACKGROUND_TYPE_COLOR = 11501;
}
