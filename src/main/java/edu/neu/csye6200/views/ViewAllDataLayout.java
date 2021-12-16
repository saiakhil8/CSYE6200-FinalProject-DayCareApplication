package edu.neu.csye6200.views;

import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.Utils.FunctionalUtilities;
import edu.neu.csye6200.Utils.Utils;
import edu.neu.csye6200.sessions.AuthenticationAndSessionManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * @author SaiAkhil
 */
public class ViewAllDataLayout extends NavBarLayout implements ListSelectionListener {

    private String title;
    private DefaultTableModel model;
    private JTable table;
    private String[][] data;

    /**
     * Main Constructor of the class
     *
     * @param imagePathOrColor which needs to be used as background
     * @param backgroundType   is type of background {Example: Image or Color}
     */
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
        this.data = data;
        this.navTitle.setText("View All " + title + "                    ");
        this.model = new DefaultTableModel(data, header);
        table = new JTable(model);
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(Color.yellow);
        table.setPreferredScrollableViewportSize(new Dimension(Constants.APP_PREFERRED_WIDTH - 120, Constants.APP_PREFERRED_HEIGHT - 480));
        table.setFillsViewportHeight(true);
        table.setOpaque(true);
        JScrollPane js = new JScrollPane(table);
        js.setVisible(true);
        table.getSelectionModel().addListSelectionListener(this);
        this.mainPanel.add(js);
    }


    @Override
    protected String getNavBarTitle() {
        return "View " + title;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (AuthenticationAndSessionManager.getInstance().getLoggedInUserType() == Constants.SESSION_TEACHER) {
            String[] buttons = {"Update GPA", "View Immunization", "Cancel"};

            int rc = JOptionPane.showOptionDialog(null, "What you want to do?", "Confirmation",
                    JOptionPane.INFORMATION_MESSAGE, 0, null, buttons, buttons[2]);
            int studentId = Integer.parseInt(this.data[table.getSelectedRow()][1]);
            if (rc == 0) {
                this.showDialogForUpdatingGpa(studentId, table.getSelectedRow());
            } else if (rc == 1) {

            }
        }
    }

    private FunctionalUtilities.BiFunction<Object, Object> biFunction;

    public void setUpBiFunction(FunctionalUtilities.BiFunction<Object, Object> biFunction) {
        this.biFunction = biFunction;
    }

    private void showDialogForUpdatingGpa(int studentId, int selectedRow) {
        String m = JOptionPane.showInputDialog("Enter GPA and Click Ok", this.data[selectedRow][7]);
        double gpa = Utils.parseDouble(m);
        if (gpa < 0.0 || gpa > 4.0) {
            JOptionPane.showMessageDialog(new JFrame(), "Enter valid gpa", "Error!!",
                    JOptionPane.ERROR_MESSAGE);
            this.showDialogForUpdatingGpa(studentId, selectedRow);
        } else {
            if (biFunction != null) {
                biFunction.accept(studentId, gpa);
                JOptionPane.showMessageDialog(new JFrame(), "Update success", "Success!!",
                        JOptionPane.INFORMATION_MESSAGE);
                this.data[selectedRow][7] = m;
                this.model.setValueAt(m, selectedRow, 7);
                this.model.fireTableCellUpdated(selectedRow, 7);

            }
        }
    }
}
