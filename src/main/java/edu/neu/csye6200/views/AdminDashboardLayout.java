package edu.neu.csye6200.views;

import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.controllers.AdminDashboardController;

import javax.swing.*;
import java.awt.*;

/**
 * @author SaiAkhil
 */
public class AdminDashboardLayout extends NavBarLayout {

    private JPanel mainAppLayout;

    public AdminDashboardLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
    }

    @Override
    protected String getNavBarTitle() {
        return "Admin Dashboard";
    }

    @Override
    protected void initComponents() {
        JPanel mainAppLayout = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        mainAppLayout.setPreferredSize(new Dimension(Constants.APP_PREFERRED_WIDTH, Constants.APP_PREFERRED_HEIGHT - 480));
        mainAppLayout.setOpaque(false);
        this.mainPanel.add(mainAppLayout, BorderLayout.LINE_START);
        this.setUpLeftSideMenu(mainAppLayout);
        this.mainAppLayout = mainAppLayout;
    }

    @Override
    protected void onCreate() {
        JPanel currentPanel = new JPanel(new GridLayout(1, 3, 24, 24));
        currentPanel.setPreferredSize(new Dimension(500, 120));
        currentPanel.add(this.getDashBoardCard("Students", this.eventListener.getIntegerData(AdminDashboardController.REQUEST_TYPE_STUDENT_COUNT)));
        currentPanel.add(this.getDashBoardCard("Teacher", this.eventListener.getIntegerData(AdminDashboardController.REQUEST_TYPE_TEACHER_COUNT)));
        currentPanel.add(this.getDashBoardCard("Classrooms", this.eventListener.getIntegerData(AdminDashboardController.REQUEST_TYPE_CLASSROOM_COUNT)));
        this.mainAppLayout.add(currentPanel);
    }

    private Component getDashBoardCard(String title, int count) {
        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.setPreferredSize(new Dimension(120, 120));
        jPanel.setBackground(Color.BLUE);
        jPanel.add(new JLabel(title), BorderLayout.LINE_START);
        jPanel.add(new JLabel(Integer.toString(count)), BorderLayout.CENTER);
        return jPanel;
    }

    private void setUpLeftSideMenu(JPanel currentLayout) {
        JPanel leftSideMenu = new JPanel();
        leftSideMenu.setLayout(new BoxLayout(leftSideMenu, BoxLayout.LINE_AXIS));
        leftSideMenu.setPreferredSize(new Dimension(200, Constants.APP_PREFERRED_HEIGHT - 64));
        leftSideMenu.setBackground(Color.decode("#343A3F"));
        currentLayout.add(leftSideMenu);
    }
}
