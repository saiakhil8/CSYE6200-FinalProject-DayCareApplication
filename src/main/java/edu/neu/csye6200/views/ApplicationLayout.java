package edu.neu.csye6200.views;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.views.CustomViews.ImageView;
import org.springframework.lang.NonNull;

import javax.swing.*;
import java.awt.*;

/**
 * @author SaiAkhil
 */
public abstract class ApplicationLayout extends JFrame {

    private Listeners.EventListener eventListener;

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

    protected abstract Component getMainLayoutComponent();
    protected abstract void initComponents();

    public void onGainedFocus(){
        this.setVisible(true);
    }

    public void onLostFocus(){
        this.setVisible(false);
    }

    public void setEventListener(Listeners.EventListener eventListener) {
        this.eventListener = eventListener;
    }

    protected void goToNextPage() {
        this.onNewEvent(Constants.EVENT_NEXT_SCREEN);
    }

    protected void onNewEvent(int eventType) {
        if (this.eventListener != null) this.eventListener.onEvent(eventType);
    }

    public static int BACKGROUND_TYPE_IMAGE = 11051;
    public static int BACKGROUND_TYPE_COLOR = 11501;
}
