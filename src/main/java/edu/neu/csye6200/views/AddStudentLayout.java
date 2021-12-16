package edu.neu.csye6200.views;

import edu.neu.csye6200.factories.StudentFactory;
import edu.neu.csye6200.models.Person;

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
        Person person = StudentFactory.getInstance().getObject(this.firstNameTextField.getActualText(),
                this.lastNameTextField.getActualText(),
                this.emailTextField.getActualText(),
                this.dobTextField.getActualText(),
                this.parentsNameTextField.getActualText(),
                this.addressTextField.getActualText());
        this.addToDatabase(person);
    }

    @Override
    protected void initSpecificFields(JPanel jPanel, GridBagConstraints gridBagConstraints) {
        //do nothing
    }
}
