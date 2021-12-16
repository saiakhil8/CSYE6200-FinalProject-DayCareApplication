package edu.neu.csye6200.views;

import edu.neu.csye6200.factories.TeacherFactory;

/**
 * @author SaiAkhil
 */
public class AddTeacherLayout extends AddPersonLayout {

    public AddTeacherLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
    }

    @Override
    protected String getSuffixForAdd() {
        return "Teacher";
    }

    @Override
    protected void setUpPersonFactory() {
        this.abstractPersonFactory = TeacherFactory.getInstance();
    }
}
