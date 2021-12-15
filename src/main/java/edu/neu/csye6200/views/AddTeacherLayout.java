package edu.neu.csye6200.views;

import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.Utils.FunctionalUtilities;
import edu.neu.csye6200.Utils.Utils;
import edu.neu.csye6200.models.Person;
import edu.neu.csye6200.models.Teacher;
import edu.neu.csye6200.views.CustomViews.RoundedTextField;
import org.springframework.lang.NonNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author SaiAkhil
 */
public class AddTeacherLayout extends NavBarLayout {

    protected RoundedTextField firstNameTextField;
    protected RoundedTextField lastNameTextField;
    protected RoundedTextField hourlyWageTextField;
    protected RoundedTextField parentsNameTextField;
    protected RoundedTextField addressTextField;
    protected RoundedTextField creditsTextField;
    protected RoundedTextField emailTextField;
    protected RoundedTextField dobTextField;
    private JButton viewAllButton;
    private JButton addButton;
    private JButton importButton;
    private FunctionalUtilities.BiFunctionWithReturnType<Object, Integer, Boolean> dbCrudCallBack;

    public AddTeacherLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
    }

    @Override
    protected void initComponents() {
        super.initComponents();
        this.setLeftComponent(Constants.getIconPathFromName("back-button.png"), "Back");
        JPanel jPanel = new JPanel(new GridBagLayout());
        jPanel.setOpaque(false);
        jPanel.setPreferredSize(new Dimension(Constants.APP_PREFERRED_WIDTH - 600, 300));
        //jPanel.setBackground(Color.BLUE);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(48, 8, 32, 32);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 0.1;

        //First Name TextField
        this.firstNameTextField = this.getInputFields("Enter First Name");
        jPanel.add(firstNameTextField, gridBagConstraints);

        //Last Name TextField
        this.lastNameTextField = this.getInputFields("Enter Last Name");
        gridBagConstraints.gridx++;
        jPanel.add(lastNameTextField, gridBagConstraints);

        //Email TextField
        this.emailTextField = this.getInputFields("Enter Email-Id");
        gridBagConstraints.gridx++;
        jPanel.add(emailTextField, gridBagConstraints);

        //Email TextField
        this.parentsNameTextField = this.getInputFields("Enter Parent Full Name");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        jPanel.add(parentsNameTextField, gridBagConstraints);

        //DOB TextField
        this.dobTextField = this.getInputFields("Enter Date of Birth (MM/DD/YYYY)");
        gridBagConstraints.gridx++;
        jPanel.add(dobTextField, gridBagConstraints);

        //Address TextField
        this.addressTextField = this.getInputFields("Enter Address");
        gridBagConstraints.gridx++;
        jPanel.add(addressTextField, gridBagConstraints);

        if (this.getSuffixForAdd().equalsIgnoreCase("Teacher")) {
            this.initSpecificFields(jPanel, gridBagConstraints);
        }

        this.addButton = this.getButtons("Add " + this.getSuffixForAdd(), Color.RED);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy++;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        jPanel.add(this.addButton, gridBagConstraints);

        this.viewAllButton = this.getButtons("VIEW ALL", Color.MAGENTA);
        gridBagConstraints.gridy++;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTHEAST;
        jPanel.add(this.viewAllButton, gridBagConstraints);

        this.importButton = this.getButtons("CSV IMPORT", Color.BLUE);
        gridBagConstraints.gridx += 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        jPanel.add(this.importButton, gridBagConstraints);

        this.mainPanel.add(jPanel, BorderLayout.LINE_START);
    }

    protected void initSpecificFields(JPanel jPanel, GridBagConstraints gridBagConstraints) {
        //Wage TextField
        this.hourlyWageTextField = this.getInputFields("Enter HourlyWage");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        jPanel.add(hourlyWageTextField, gridBagConstraints);

        //Credits TextField
        this.creditsTextField = this.getInputFields("Enter Credits");
        gridBagConstraints.gridx++;
        jPanel.add(creditsTextField, gridBagConstraints);
    }

    protected String getSuffixForAdd() {
        return "Teacher";
    }

    private JButton getButtons(String placeHolder, Color foregroundColor) {
        JButton button = new JButton(placeHolder.toUpperCase());
        button.setPreferredSize(new Dimension(200, 60));
        button.setForeground(foregroundColor);
        return button;
    }

    private RoundedTextField getInputFields(@NonNull String placeHolder) {
        RoundedTextField roundedTextField = new RoundedTextField(24);
        roundedTextField.setPreferredSize(new Dimension(30, 48));
        roundedTextField.setPlaceHolder(placeHolder);
        return roundedTextField;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        this.mainPanel.requestFocus();
        this.addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                validateUserInput();
            }
        });
    }

    public void setDbCrudCallBack(FunctionalUtilities.BiFunctionWithReturnType<Object, Integer, Boolean> dbCrudCallBack) {
        this.dbCrudCallBack = dbCrudCallBack;
    }

    private void validateUserInput() {

        if (this.firstNameTextField.getActualText().isEmpty()) {
            this.showErrorOnWrongInput(this.firstNameTextField, "First Name shouldn't be empty");
            return;
        }

        if (this.lastNameTextField.getActualText().isEmpty()) {
            this.showErrorOnWrongInput(this.lastNameTextField, "Last Name shouldn't be empty");
            return;
        }

        if (this.addressTextField.getActualText().isEmpty()) {
            this.showErrorOnWrongInput(this.addressTextField, "Address shouldn't be empty");
            return;
        }

        if (this.parentsNameTextField.getActualText().isEmpty()) {
            this.showErrorOnWrongInput(this.parentsNameTextField, "Parents Name shouldn't be empty");
            return;
        }

        if (!Utils.VALIDATE_EMAIL_ADDRESS.apply(this.emailTextField.getActualText())) {
            this.showErrorOnWrongInput(this.emailTextField, "Enter valid Email Id");
            return;
        }

        if (!Utils.isDateValid(this.dobTextField.getActualText(), true)) {
            this.showErrorOnWrongInput(this.dobTextField, "Enter valid Date of Birth");
            return;
        }


        if (this.getSuffixForAdd().equalsIgnoreCase("Teacher")) {
            //do specific validation
            if (this.hourlyWageTextField.getActualText().isEmpty() && Utils.parseInteger(this.hourlyWageTextField.getActualText()) > 0) {
                this.showErrorOnWrongInput(this.hourlyWageTextField, "Enter valid hourly wage");
                return;
            }

            if (this.creditsTextField.getActualText().isEmpty() && Utils.parseInteger(this.creditsTextField.getActualText()) > 0) {
                this.showErrorOnWrongInput(this.creditsTextField, "Enter valid credits");
                return;
            }
        }
        this.addToDatabase();
    }


    protected void showErrorOnWrongInput(RoundedTextField roundedTextField, String message) {
        JOptionPane.showMessageDialog(new JFrame(), message.toUpperCase(), "Error!!",
                JOptionPane.ERROR_MESSAGE);
        roundedTextField.requestFocus();
    }

    protected void addToDatabase() {
        this.addToDatabase(new Teacher(this.firstNameTextField.getActualText(),
                this.lastNameTextField.getActualText(),
                this.emailTextField.getActualText(),
                this.dobTextField.getActualText(),
                this.parentsNameTextField.getActualText(),
                this.addressTextField.getActualText(),
                Utils.parseInteger(this.creditsTextField.getActualText()),
                Utils.parseInteger(this.hourlyWageTextField.getActualText())));
    }

    protected void addToDatabase(Person person) {
        if (this.dbCrudCallBack.accept(person, 0)) {
            JOptionPane.showMessageDialog(new JFrame(), "Added " + this.getSuffixForAdd() + " Successfully", "Success!!",
                    JOptionPane.INFORMATION_MESSAGE);
            this.clearInputs();
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Could'nt Add " + this.getSuffixForAdd() + ". Try Again Later!", "Error!!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearInputs() {
        this.emailTextField.reset();
        this.firstNameTextField.reset();
        this.lastNameTextField.reset();
        this.addressTextField.reset();
        if (this.getSuffixForAdd().equalsIgnoreCase("Teacher")) {
            this.creditsTextField.reset();
            ;
            this.hourlyWageTextField.reset();
            ;
        }
        this.dobTextField.reset();
        ;
        this.parentsNameTextField.reset();
        ;
    }

    @Override
    protected String getNavBarTitle() {
        return "Add " + this.getSuffixForAdd();
    }

    @Override
    protected void onLeftButtonClicked() {
        super.onLeftButtonClicked();
        System.out.println("Clicked");
    }

}
