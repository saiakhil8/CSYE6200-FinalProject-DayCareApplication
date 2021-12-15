package edu.neu.csye6200.views;

import edu.neu.csye6200.models.Student;

import javax.swing.*;
import java.awt.*;

/**
 * @author SaiAkhil
 */
public class AddStudentLayout extends AddTeacherLayout {
    public AddStudentLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
    }

    @Override
    protected String getSuffixForAdd() {
        return "Student";
    }

    @Override
    protected void addToDatabase() {
        this.addToDatabase(new Student(this.firstNameTextField.getActualText(),
                this.lastNameTextField.getActualText(),
                this.emailTextField.getActualText(),
                this.dobTextField.getActualText(),
                this.parentsNameTextField.getActualText(),
                this.addressTextField.getActualText(),
                0.0));
    }

    @Override
    protected void initSpecificFields(JPanel jPanel, GridBagConstraints gridBagConstraints) {
        //do nothing
    }
}
