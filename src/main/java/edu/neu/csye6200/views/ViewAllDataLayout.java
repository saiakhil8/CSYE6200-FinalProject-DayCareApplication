package edu.neu.csye6200.views;

import edu.neu.csye6200.Utils.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * @author SaiAkhil
 */
public class ViewAllDataLayout extends NavBarLayout {
    /**
     * Main Constructor of the class
     *
     * @param imagePathOrColor which needs to be used as background
     * @param backgroundType   is type of background {Example: Image or Color}
     */
    private String title;

    public ViewAllDataLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
    }

    @Override
    protected void initComponents() {
        super.initComponents();
        this.setLeftComponent(Constants.getIconPathFromName("back-button.png"), "Back");
    }

    public void setUpDataForTable(String[] header, String[][] data, String title) {
        this.title = title;
        this.navTitle.setText("View All " + title + "                    ");
        DefaultTableModel model = new DefaultTableModel(data, header);
        JTable table = new JTable(model);
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(Color.yellow);
        table.setPreferredScrollableViewportSize(new Dimension(Constants.APP_PREFERRED_WIDTH - 120, Constants.APP_PREFERRED_HEIGHT - 480));
        table.setFillsViewportHeight(true);
        table.setOpaque(true);
        JScrollPane js = new JScrollPane(table);
        js.setVisible(true);
        this.mainPanel.add(js);
    }


    @Override
    protected String getNavBarTitle() {
        return "View " + title;
    }
}
