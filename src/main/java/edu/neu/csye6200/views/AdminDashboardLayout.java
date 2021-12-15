package edu.neu.csye6200.views;

import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.controllers.AdminDashboardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author SaiAkhil
 */
public class AdminDashboardLayout extends NavBarLayout implements MouseListener {

    private JPanel mainAppLayout;
    private JPanel leftSidePanel;
    private JLabel addTeachersLabel;
    private JLabel addStudentsLabel;
    private JLabel viewStudentsLabel;
    private JLabel viewTeachersLabel;
    private JLabel logoutLabel;

    public AdminDashboardLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
    }

    @Override
    protected String getNavBarTitle() {
        return "Admin Dashboard";
    }

    @Override
    protected void initComponents() {
        super.initComponents();
        JPanel mainAppLayout = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        mainAppLayout.setPreferredSize(new Dimension(Constants.APP_PREFERRED_WIDTH, Constants.APP_PREFERRED_HEIGHT - 480));
        mainAppLayout.setOpaque(false);
        this.mainPanel.add(mainAppLayout, BorderLayout.LINE_START);
        this.setUpLeftSideMenu(mainAppLayout);
        this.setLeftComponent(Constants.getIconPathFromName("list.png"), "Menu");
        this.addItemsToLeftMenu();
        this.mainAppLayout = mainAppLayout;
    }

    private void addItemsToLeftMenu() {
        this.leftSidePanel.add(this.getSpaceComponent());
        JLabel label = this.getMenuJLabels("Home");
        label.setFont(new Font("Nunito", Font.BOLD, 18));
        label.setForeground(Color.decode("#F33E5B"));
        label.addMouseListener(null);
        this.leftSidePanel.add(label);
        this.leftSidePanel.add(this.getSpaceComponent());
        this.addTeachersLabel = this.getMenuJLabels("Add Teachers");
        this.leftSidePanel.add(this.addTeachersLabel);
        this.leftSidePanel.add(this.getSpaceComponent());
        this.addStudentsLabel = this.getMenuJLabels("Add Students");
        this.leftSidePanel.add(this.addStudentsLabel);
        this.leftSidePanel.add(this.getSpaceComponent());
        this.viewTeachersLabel = this.getMenuJLabels("View Teachers");
        this.leftSidePanel.add(this.viewTeachersLabel);
        this.leftSidePanel.add(this.getSpaceComponent());
        this.viewStudentsLabel = this.getMenuJLabels("View Students");
        this.leftSidePanel.add(this.viewStudentsLabel);
        this.leftSidePanel.add(this.getSpaceComponent());
        this.logoutLabel = this.getMenuJLabels("Logout");
        this.leftSidePanel.add(this.logoutLabel);
    }

    private Component getSpaceComponent() {
        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(200, 20));
        sep.setForeground(Color.decode("#343A3F"));
        return sep;
    }

    private JLabel getMenuJLabels(String text) {
        JLabel jLabel = new JLabel(text.toUpperCase(), SwingConstants.CENTER);
        jLabel.setPreferredSize(new Dimension(200, 20));
        jLabel.setFont(new Font("Nunito", Font.PLAIN, 16));
        jLabel.setForeground(Color.WHITE);
        jLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        jLabel.addMouseListener(this);
        return jLabel;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
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
        this.leftSidePanel = new JPanel();
        leftSidePanel.setLayout(new BoxLayout(leftSidePanel, BoxLayout.Y_AXIS));
        leftSidePanel.setPreferredSize(new Dimension(200, Constants.APP_PREFERRED_HEIGHT - 64));
        leftSidePanel.setBackground(Color.decode("#343A3F"));
        currentLayout.add(leftSidePanel);
    }

    @Override
    protected void onLeftButtonClicked() {
        this.leftSidePanel.setVisible(!this.leftSidePanel.isVisible());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(this.logoutLabel)) this.onRightButtonClicked();
        if (e.getComponent().equals(this.addStudentsLabel)) {
            this.eventListener.onEvent(AdminDashboardController.EVENT_GOTO_ADD_STUDENT);
        } else if (e.getComponent().equals(this.addTeachersLabel)) {
            this.eventListener.onEvent(AdminDashboardController.EVENT_GOTO_ADD_TEACHER);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel jLabel = (JLabel) e.getComponent();
        jLabel.setFont(new Font("Nunito", Font.BOLD, 20));
        jLabel.setForeground(Color.ORANGE);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel jLabel = (JLabel) e.getComponent();
        jLabel.setFont(new Font("Nunito", Font.PLAIN, 16));
        jLabel.setForeground(Color.WHITE);
    }
}
