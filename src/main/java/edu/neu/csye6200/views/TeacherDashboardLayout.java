package edu.neu.csye6200.views;

import javax.swing.*;
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
    protected void addItemsToLeftMenu(JPanel leftSidePanel) {
        super.addItemsToLeftMenu(leftSidePanel);
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


}
