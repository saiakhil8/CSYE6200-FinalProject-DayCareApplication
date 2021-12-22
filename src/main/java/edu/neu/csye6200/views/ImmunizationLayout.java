package edu.neu.csye6200.views;

import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.Utils.FunctionalUtilities;
import edu.neu.csye6200.Utils.Utils;
import edu.neu.csye6200.views.CustomViews.RoundedTextField;
import org.springframework.lang.NonNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SaiAkhil
 */
public class ImmunizationLayout extends NavBarLayout implements MouseListener {

    private RoundedTextField hibDose1;
    private RoundedTextField hibDose2;
    private RoundedTextField hibDose3;
    private RoundedTextField hibDose4;

    private RoundedTextField dtap1;
    private RoundedTextField dtap2;
    private RoundedTextField dtap3;
    private RoundedTextField dtap4;

    private RoundedTextField heptatisB1;
    private RoundedTextField heptatisB2;
    private RoundedTextField heptatisB3;

    private RoundedTextField mmr;
    private RoundedTextField varicella;

    private JButton updateButton;
    private JButton csvImport;


    /**
     * Main Constructor of the class
     *
     * @param imagePathOrColor which needs to be used as background
     * @param backgroundType   is type of background {Example: Image or Color}
     */
    public ImmunizationLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
    }

    @Override
    protected String getNavBarTitle() {
        return "Immunization Report";
    }

    @Override
    protected void initComponents() {
        super.initComponents();
        this.setLeftComponent(Constants.getIconPathFromName("back-button.png"), "Back");
        JPanel jPanel = new JPanel(new GridBagLayout());
        jPanel.setOpaque(false);
        // jPanel.setPreferredSize(new Dimension(Constants.APP_PREFERRED_WIDTH-100, 300));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(48, 32, 32, 32);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.fill = GridBagConstraints.NONE;


        jPanel.add(this.setPlaceHolderJLabel("Hib Doses"), gridBagConstraints);
        //Hib 1 TextField
        this.hibDose1 = this.getInputFields("Hib Dose 1 (MM/dd/yyyy)");
        gridBagConstraints.gridx++;
        jPanel.add(hibDose1, gridBagConstraints);

        //Hib 2 TextField
        this.hibDose2 = this.getInputFields("Hib Dose 2 (MM/dd/yyyy)");
        gridBagConstraints.gridx++;
        jPanel.add(hibDose2, gridBagConstraints);

        //Hib 3 TextField
        this.hibDose3 = this.getInputFields("Hib Dose 3 (MM/dd/yyyy)");
        gridBagConstraints.gridx++;
        jPanel.add(hibDose3, gridBagConstraints);

        //Hib 4 TextField
        this.hibDose4 = this.getInputFields("Hib Dose 4 (MM/dd/yyyy)");
        gridBagConstraints.gridx++;
        jPanel.add(hibDose4, gridBagConstraints);


        gridBagConstraints.gridy++;
        gridBagConstraints.gridx = 0;
        jPanel.add(this.setPlaceHolderJLabel("Dtap Doses"), gridBagConstraints);
        this.dtap1 = this.getInputFields("Dtap Dose 1 (MM/dd/yyyy)");
        gridBagConstraints.gridx++;
        jPanel.add(dtap1, gridBagConstraints);

        this.dtap2 = this.getInputFields("Dtap Dose 2 (MM/dd/yyyy)");
        gridBagConstraints.gridx++;
        jPanel.add(dtap2, gridBagConstraints);


        this.dtap3 = this.getInputFields("Dtap Dose 3 (MM/dd/yyyy)");
        gridBagConstraints.gridx++;
        jPanel.add(dtap3, gridBagConstraints);


        this.dtap4 = this.getInputFields("Dtap Dose 4 (MM/dd/yyyy)");
        gridBagConstraints.gridx++;
        jPanel.add(dtap4, gridBagConstraints);

        gridBagConstraints.gridy++;
        gridBagConstraints.gridx = 0;
        jPanel.add(this.setPlaceHolderJLabel("Hepatitis B Doses"), gridBagConstraints);
        this.heptatisB1 = this.getInputFields("Hepatitis B Dose 1 (MM/dd/yyyy)");
        gridBagConstraints.gridx++;
        jPanel.add(heptatisB1, gridBagConstraints);

        this.heptatisB2 = this.getInputFields("Hepatitis B Dose 2 (MM/dd/yyyy)");
        gridBagConstraints.gridx++;
        jPanel.add(heptatisB2, gridBagConstraints);

        this.heptatisB3 = this.getInputFields("Hepatitis B Dose 3 (MM/dd/yyyy)");
        gridBagConstraints.gridx++;
        jPanel.add(heptatisB3, gridBagConstraints);

        gridBagConstraints.gridy++;
        gridBagConstraints.gridx = 0;
        jPanel.add(this.setPlaceHolderJLabel("MMR Doses"), gridBagConstraints);
        this.mmr = this.getInputFields("MMR Dose 1 (MM/dd/yyyy)");
        gridBagConstraints.gridx++;
        jPanel.add(mmr, gridBagConstraints);

        gridBagConstraints.gridx++;
        jPanel.add(this.setPlaceHolderJLabel("Varicella Doses"), gridBagConstraints);
        this.varicella = this.getInputFields("Varicella (MM/dd/yyyy)");
        gridBagConstraints.gridx++;
        jPanel.add(varicella, gridBagConstraints);

        this.updateButton = this.getButtons("Update Immunization", Color.RED);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy++;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        jPanel.add(this.updateButton, gridBagConstraints);

        this.csvImport = this.getButtons("CSV Import", Color.BLUE);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        jPanel.add(this.csvImport, gridBagConstraints);
        this.csvImport.setVisible(false);

        this.mainPanel.add(jPanel, BorderLayout.LINE_START);
    }

    private JLabel setPlaceHolderJLabel(String text) {
        JLabel jLabel = new JLabel(text + ":", SwingConstants.CENTER);
        jLabel.setForeground(Color.BLACK);
        jLabel.setFont(new Font("amarnath", Font.BOLD, 16));
        return jLabel;
    }

    private JButton getButtons(String placeHolder, Color foregroundColor) {
        JButton button = new JButton(placeHolder.toUpperCase());
        button.setPreferredSize(new Dimension(200, 60));
        button.setForeground(foregroundColor);
        return button;
    }

    private RoundedTextField getInputFields(@NonNull String placeHolder) {
        RoundedTextField roundedTextField = new RoundedTextField(16);
        roundedTextField.setPreferredSize(new Dimension(14, 35));
        roundedTextField.setPlaceHolder(placeHolder);
        return roundedTextField;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        this.mainPanel.requestFocus();
        this.updateButton.addMouseListener(this);
        this.csvImport.addMouseListener(this);
    }

    public void refreshHibData(String h[]) {
        int i = 0;
        if (h[i] != null) this.hibDose1.setText(h[i++]);
        if (h[i] != null) this.hibDose2.setText(h[i++]);
        if (h[i] != null) this.hibDose3.setText(h[i++]);
        if (h[i] != null) this.hibDose4.setText(h[i]);
    }

    public void refreshHibData(String str) {
        this.hibDose1.setText(str);
    }

    public void refreshDtapData(String str) {
        this.dtap1.setText(str);
    }

    public void refreshHepetatisData(String str) {
        this.heptatisB1.setText(str);
    }

    public void refreshMMRData(String m) {
        this.mmr.setText(m);
    }

    public void refreshVaricellaData(String m) {
        this.varicella.setText(m);
    }

    public void refreshDtapData(String h[]) {
        if (h[0] != null) this.dtap1.setText(h[0]);
        if (h[1] != null) this.dtap2.setText(h[1]);
        if (h[2] != null) this.dtap3.setText(h[2]);
        if (h[3] != null) this.dtap4.setText(h[3]);
    }

    public void refreshHepetatisData(String h[]) {
        if (h[0] != null) this.heptatisB1.setText(h[0]);
        if (h[1] != null) this.heptatisB2.setText(h[1]);
        if (h[2] != null) this.heptatisB3.setText(h[2]);
    }

    public void refreshMMRVaricellaData(String m, String v) {
        if (m != null) this.mmr.setText(m);
        if (v != null) this.varicella.setText(v);
    }

    private FunctionalUtilities.BiFunctionWithReturnType<Object, Integer, Boolean> dbCrudCallBack;

    public void setDbCrudCallBack(FunctionalUtilities.BiFunctionWithReturnType<Object, Integer, Boolean> dbCrudCallBack) {
        this.dbCrudCallBack = dbCrudCallBack;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(updateButton)) {
            boolean result = this.dbCrudCallBack.accept(this.getVaccinationData(), 0);
            JOptionPane.showMessageDialog(new JFrame(), (result) ? "Update success" : "Update failed", (result) ? "Success" : "Failed",
                    (result) ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
        }
    }

    private Map<String, Object> getVaccinationData() {
        Map<String, Object> master = new HashMap<>();
        //Add Hib here;
        Map<String, String> vMap = new HashMap<>();
        this.validateAndPut(this.hibDose1.getText(), vMap, "1");
        this.validateAndPut(this.hibDose2.getText(), vMap, "2");
        this.validateAndPut(this.hibDose3.getText(), vMap, "3");
        this.validateAndPut(this.hibDose4.getText(), vMap, "4");
        master.put("hib", vMap);

        vMap = new HashMap<>();
        //dtap here
        this.validateAndPut(this.dtap1.getText(), vMap, "1");
        this.validateAndPut(this.dtap2.getText(), vMap, "2");
        this.validateAndPut(this.dtap3.getText(), vMap, "3");
        this.validateAndPut(this.dtap4.getText(), vMap, "4");
        master.put("dtap", vMap);

        vMap = new HashMap<>();
        //heptatis here
        this.validateAndPut(this.heptatisB1.getText(), vMap, "1");
        this.validateAndPut(this.heptatisB2.getText(), vMap, "2");
        this.validateAndPut(this.heptatisB3.getText(), vMap, "3");
        master.put("hepatitisb", vMap);

        if (Utils.isDateValid(this.mmr.getText(), false)) master.put("mmr", this.mmr.getText());
        if (Utils.isDateValid(this.varicella.getText(), false)) master.put("varicella", this.varicella.getText());
        return master;
    }

    private void validateAndPut(String text, Map<String, String> map, String number) {
        if (Utils.isDateValid(text, false)) map.put(number, text);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
