package edu.neu.csye6200.views;

/**
 * @author SaiAkhil
 */
public class TeacherDashboardLayout extends NavBarLayout {
    /**
     * Main Constructor of the class
     *
     * @param imagePathOrColor which needs to be used as background
     * @param backgroundType   is type of background {Example: Image or Color}
     */
    public TeacherDashboardLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
    }

    @Override
    protected String getNavBarTitle() {
        return "Teacher Dashboard";
    }
}
