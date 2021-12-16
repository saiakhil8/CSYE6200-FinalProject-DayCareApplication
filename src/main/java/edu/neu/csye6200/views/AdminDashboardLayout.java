package edu.neu.csye6200.views;

import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.controllers.AdminDashboardController;
import edu.neu.csye6200.views.CustomViews.DashboardCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author SaiAkhil
 */
public class AdminDashboardLayout extends DashboardLayout {

    private JLabel addTeachersLabel;
    private JLabel addStudentsLabel;
    private JLabel addAdminsLabel;
    private JLabel viewStudentsLabel;
    private JLabel viewTeachersLabel;
    private JLabel viewAdminsLabel;
    private JLabel logoutLabel;
    private JLabel classRules;
    private JLabel classRooms;
    private DashboardCard teacherCountCard;
    private DashboardCard studentCountCard;
    private DashboardCard classRoomCountCard;
    private DashboardCard adminCountCard;
    private DashboardCard classRulesCard;

    public AdminDashboardLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
    }

    @Override
    protected String getNavBarTitle() {
        return "Admin Dashboard";
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

        this.studentCountCard = new DashboardCard("Students", 0, Color.GRAY, Color.BLACK, Color.ORANGE);
        currentPanel.add(this.studentCountCard, gridBagConstraints);

        gridBagConstraints.gridx++;
        this.teacherCountCard = new DashboardCard("Teachers", 0, Color.GRAY, Color.BLACK, Color.BLUE);
        currentPanel.add(this.teacherCountCard, gridBagConstraints);

        gridBagConstraints.gridx++;
        this.adminCountCard = new DashboardCard("Admins", 0, Color.GRAY, Color.BLACK, Color.BLUE);
        currentPanel.add(this.adminCountCard, gridBagConstraints);


        gridBagConstraints.gridy++;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weighty = 6;
        this.classRulesCard = new DashboardCard("Class Rules", 0, Color.GRAY, Color.BLACK, Color.BLUE);
        currentPanel.add(this.classRulesCard, gridBagConstraints);

        gridBagConstraints.gridx++;
        this.classRoomCountCard = new DashboardCard("Classrooms", 0, Color.GRAY, Color.BLACK, Color.BLUE);
        currentPanel.add(this.classRoomCountCard, gridBagConstraints);


        this.mainAppLayout.add(currentPanel);
    }

    @Override
    protected void addItemsToLeftMenu() {
        super.addItemsToLeftMenu();
        this.leftSidePanel.add(this.getSpaceComponent());
        this.addTeachersLabel = this.getMenuJLabels("Add Teachers");
        this.leftSidePanel.add(this.addTeachersLabel);
        this.leftSidePanel.add(this.getSpaceComponent());
        this.addStudentsLabel = this.getMenuJLabels("Add Students");
        this.leftSidePanel.add(this.addStudentsLabel);
        this.leftSidePanel.add(this.getSpaceComponent());
        this.addAdminsLabel = this.getMenuJLabels("Add Admins");
        this.leftSidePanel.add(this.addAdminsLabel);
        this.leftSidePanel.add(this.getSpaceComponent());
        this.viewTeachersLabel = this.getMenuJLabels("View Teachers");
        this.leftSidePanel.add(this.viewTeachersLabel);
        this.leftSidePanel.add(this.getSpaceComponent());
        this.viewStudentsLabel = this.getMenuJLabels("View Students");
        this.leftSidePanel.add(this.viewStudentsLabel);
        this.leftSidePanel.add(this.getSpaceComponent());
        this.viewAdminsLabel = this.getMenuJLabels("View Admins");
        this.leftSidePanel.add(this.viewAdminsLabel);
        this.leftSidePanel.add(this.getSpaceComponent());
        this.classRules = this.getMenuJLabels("Class Rules");
        this.leftSidePanel.add(this.classRules);
        this.leftSidePanel.add(this.getSpaceComponent());
        this.classRooms = this.getMenuJLabels("Class Rooms");
        this.leftSidePanel.add(this.classRooms);
        this.leftSidePanel.add(this.getSpaceComponent());
        this.logoutLabel = this.getMenuJLabels("Logout");
        this.leftSidePanel.add(this.logoutLabel);
    }

    @Override
    public void refreshCards() {
        this.updateTeacherCount();
        this.updateStudentCountCard();
        this.updateAdminCountCard();
        this.updateClassRulesCard();
        this.updateClassRoomCard();
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        JOptionPane.showMessageDialog(new JFrame(), "You are loggedIn as Admin", "Success!!",
                JOptionPane.INFORMATION_MESSAGE);
        this.refreshCards();
    }

    private void updateAdminCountCard() {
        this.adminCountCard.setCount(this.eventListener.getIntegerData(AdminDashboardController.REQUEST_TYPE_ADMIN_COUNT));
    }

    private void updateClassRoomCard() {
        this.classRoomCountCard.setCount(this.eventListener.getIntegerData(AdminDashboardController.REQUEST_TYPE_CLASSROOM_COUNT));
    }

    private void updateClassRulesCard() {
        this.classRulesCard.setCount(this.eventListener.getIntegerData(AdminDashboardController.REQUEST_TYPE_CLASS_RULES_COUNT));
    }

    private void updateStudentCountCard() {
        this.studentCountCard.setCount(this.eventListener.getIntegerData(AdminDashboardController.REQUEST_TYPE_STUDENT_COUNT));
    }

    private void updateTeacherCount() {
        this.teacherCountCard.setCount(this.eventListener.getIntegerData(AdminDashboardController.REQUEST_TYPE_TEACHER_COUNT));
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(this.logoutLabel)) this.onRightButtonClicked();
        if (e.getComponent().equals(this.addStudentsLabel)) {
            this.eventListener.onEvent(AdminDashboardController.EVENT_GOTO_ADD_STUDENT);
        } else if (e.getComponent().equals(this.addTeachersLabel)) {
            this.eventListener.onEvent(AdminDashboardController.EVENT_GOTO_ADD_TEACHER);
        } else if (e.getComponent().equals(this.viewTeachersLabel)) {
            this.eventListener.onEvent(AdminDashboardController.EVENT_GOTO_VIEW_TEACHER);
        } else if (e.getComponent().equals(this.viewStudentsLabel)) {
            this.eventListener.onEvent(AdminDashboardController.EVENT_GOTO_VIEW_STUDENT);
        } else if (e.getComponent().equals(this.viewAdminsLabel)) {
            this.eventListener.onEvent(AdminDashboardController.EVENT_GOTO_VIEW_ADMIN);
        } else if (e.getComponent().equals(this.addAdminsLabel)) {
            this.eventListener.onEvent(AdminDashboardController.EVENT_GOTO_ADD_ADMIN);
        } else if (e.getComponent().equals(this.classRules)) {
            this.eventListener.onEvent(AdminDashboardController.EVENT_GOTO_CLASS_RULES);
        } else if (e.getComponent().equals(this.classRooms)) {
            this.eventListener.onEvent(AdminDashboardController.EVENT_GOTO_CLASS_ROOMS);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }


}
