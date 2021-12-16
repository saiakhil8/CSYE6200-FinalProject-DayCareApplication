package edu.neu.csye6200.views;

import edu.neu.csye6200.Utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author SaiAkhil
 */
public abstract class DashboardLayout extends NavBarLayout implements MouseListener {

    protected JPanel leftSidePanel;
    public JPanel mainAppLayout;
    protected JLabel homeLabel;

    /**
     * Main Constructor of the class
     *
     * @param imagePathOrColor which needs to be used as background
     * @param backgroundType   is type of background {Example: Image or Color}
     */
    public DashboardLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
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
        this.addItemsToLeftMenu(this.leftSidePanel);
        this.mainAppLayout = mainAppLayout;
        this.setUpCards();
    }

    @Override
    protected void onLeftButtonClicked() {
        this.leftSidePanel.setVisible(!this.leftSidePanel.isVisible());
    }


    @Override
    protected void onCreate() {
        super.onCreate();
        this.refreshCards();
    }

    protected abstract void setUpCards();

    protected void addItemsToLeftMenu(JPanel leftSidePanel) {
        leftSidePanel.add(this.getSpaceComponent());
        this.homeLabel = this.getMenuJLabels("Home");
        homeLabel.setFont(new Font("Nunito", Font.BOLD, 18));
        homeLabel.setForeground(Color.decode("#F33E5B"));
        homeLabel.addMouseListener(null);
        leftSidePanel.add(this.homeLabel);
    }

    public abstract void refreshCards();

    protected Component getSpaceComponent() {
        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(200, 20));
        sep.setForeground(Color.decode("#343A3F"));
        return sep;
    }

    protected JLabel getMenuJLabels(String text) {
        JLabel jLabel = new JLabel(text.toUpperCase(), SwingConstants.CENTER);
        jLabel.setPreferredSize(new Dimension(200, 20));
        jLabel.setFont(new Font("Nunito", Font.PLAIN, 16));
        jLabel.setForeground(Color.WHITE);
        jLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        jLabel.addMouseListener(this);
        return jLabel;
    }

    private void setUpLeftSideMenu(JPanel currentLayout) {
        this.leftSidePanel = new JPanel();
        leftSidePanel.setLayout(new BoxLayout(leftSidePanel, BoxLayout.Y_AXIS));
        leftSidePanel.setPreferredSize(new Dimension(200, Constants.APP_PREFERRED_HEIGHT - 64));
        leftSidePanel.setBackground(Color.decode("#343A3F"));
        currentLayout.add(leftSidePanel);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getComponent().equals(this.homeLabel)) return;
        JLabel jLabel = (JLabel) e.getComponent();
        jLabel.setFont(new Font("Nunito", Font.BOLD, 20));
        jLabel.setForeground(Color.ORANGE);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getComponent().equals(this.homeLabel)) return;
        JLabel jLabel = (JLabel) e.getComponent();
        jLabel.setFont(new Font("Nunito", Font.PLAIN, 16));
        jLabel.setForeground(Color.WHITE);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
