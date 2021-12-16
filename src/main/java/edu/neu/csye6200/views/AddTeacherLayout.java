package edu.neu.csye6200.views;

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
}
