package edu.neu.csye6200.views;

import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.controllers.TeacherDashboardController;
import edu.neu.csye6200.models.Teacher;
import edu.neu.csye6200.sessions.AuthenticationAndSessionManager;
import edu.neu.csye6200.views.CustomViews.DashboardCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author SaiAkhil
 */
public class TeacherDashboardLayout extends DashboardLayout {

    private JLabel viewStudentsLabel;
    private JLabel logoutLabel;
    private DashboardCard creditsCard;
    private DashboardCard hourlyWageCard;
    private DashboardCard studentsCountCard;
    private DashboardCard classRoomIdCard;

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
        JPanel currentPanel = new JPanel(new GridBagLayout());
        currentPanel.setOpaque(false);
        currentPanel.setPreferredSize(new Dimension(Constants.APP_PREFERRED_WIDTH - 800, Constants.APP_PREFERRED_HEIGHT - 200));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(8, 100, 32, 100);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 1;

        this.classRoomIdCard = new DashboardCard("Class Room Id", 0, Color.GRAY, Color.BLACK, Color.ORANGE);
        currentPanel.add(this.classRoomIdCard, gridBagConstraints);

        gridBagConstraints.gridx++;
        this.studentsCountCard = new DashboardCard("Students", 0, Color.GRAY, Color.BLACK, Color.BLUE);
        currentPanel.add(this.studentsCountCard, gridBagConstraints);

        gridBagConstraints.gridx++;
        this.hourlyWageCard = new DashboardCard("Hourly Wage", 0, Color.GRAY, Color.BLACK, Color.BLUE);
        currentPanel.add(this.hourlyWageCard, gridBagConstraints);

        gridBagConstraints.gridy++;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weighty = 6;
        this.creditsCard = new DashboardCard("Credits", 0, Color.GRAY, Color.BLACK, Color.BLUE);
        currentPanel.add(this.creditsCard, gridBagConstraints);

        this.mainAppLayout.add(currentPanel);
    }

    @Override
    public void refreshCards() {
        setUpClassIdCard();
        setUpHourlyWage();
        setUpCredits();
        this.setUpStudentCount();
    }

    private void setUpClassIdCard() {
        this.classRoomIdCard.setCount(((Teacher) AuthenticationAndSessionManager.getInstance().getCurrentPerson()).getAssignedClassRoomId());
    }

    private void setUpHourlyWage() {
        this.hourlyWageCard.setCount(((Teacher) AuthenticationAndSessionManager.getInstance().getCurrentPerson()).getHourlyWage());
    }

    private void setUpCredits() {
        this.creditsCard.setCount(((Teacher) AuthenticationAndSessionManager.getInstance().getCurrentPerson()).getCredits());
    }

    private void setUpStudentCount() {
        this.studentsCountCard.setCount(this.eventListener.getIntegerData(TeacherDashboardController.EVENT_GET_STUDENT_COUNT));
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(this.logoutLabel)) this.onRightButtonClicked();
        if (e.getComponent().equals(this.viewStudentsLabel)) {
            this.eventListener.onEvent(TeacherDashboardController.EVENT_GOTO_VIEW_STUDENT);
        }
    }


}
