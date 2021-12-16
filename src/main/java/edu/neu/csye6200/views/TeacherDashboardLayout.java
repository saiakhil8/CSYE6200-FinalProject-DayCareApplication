package edu.neu.csye6200.views;

import java.awt.event.MouseEvent;

/**
 * @author SaiAkhil
 */
public class TeacherDashboardLayout extends DashboardLayout {
    /**
     * Main Constructor of the class
     *
     * @param imagePathOrColor which needs to be used as background
     * @param backgroundType   is type of background {Example: Image or Color}
     */
    public TeacherDashboardLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
    }

    @Override
    protected String getNavBarTitle() {
        return "Teacher Dashboard";
    }

    @Override
    protected void setUpCards() {

    }

    @Override
    public void refreshCards() {

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
