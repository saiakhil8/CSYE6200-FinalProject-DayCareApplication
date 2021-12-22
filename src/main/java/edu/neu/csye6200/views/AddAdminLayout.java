package edu.neu.csye6200.views;

import edu.neu.csye6200.factories.AdminFactory;

/**
 * @author SaiAkhil
 */
public class AddAdminLayout extends AddPersonLayout {
    public AddAdminLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
    }

    @Override
    protected String getSuffixForAdd() {
        return "Admin";
    }

    @Override
    protected void setUpPersonFactory() {
        this.abstractPersonFactory = AdminFactory.getInstance();
    }
}
