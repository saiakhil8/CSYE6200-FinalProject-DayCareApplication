package edu.neu.csye6200.views;

import edu.neu.csye6200.controllers.TeacherDashboardController;

import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * @author SaiAkhil
 */
public class TeacherDashboardLayout extends DashboardLayout {

    private JLabel viewStudentsLabel;
    private JLabel logoutLabel;

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
    protected void addItemsToLeftMenu(JPanel leftSidePanel) {
        super.addItemsToLeftMenu(leftSidePanel);
        leftSidePanel.add(this.getSpaceComponent());
        this.viewStudentsLabel = this.getMenuJLabels("View Students");
        this.leftSidePanel.add(this.viewStudentsLabel);
        leftSidePanel.add(this.getSpaceComponent());
        this.logoutLabel = this.getMenuJLabels("Logout");
        leftSidePanel.add(this.logoutLabel);
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
        if (e.getComponent().equals(this.logoutLabel)) this.onRightButtonClicked();
        if (e.getComponent().equals(this.viewStudentsLabel)) {
            this.eventListener.onEvent(TeacherDashboardController.EVENT_GOTO_VIEW_STUDENT);
        }
    }


}
