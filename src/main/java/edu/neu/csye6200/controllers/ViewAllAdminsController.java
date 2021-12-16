package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.models.Admin;
import edu.neu.csye6200.repositories.AdminRepository;
import edu.neu.csye6200.views.ApplicationLayout;
import edu.neu.csye6200.views.ViewAllDataLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author SaiAkhil
 */
public class ViewAllAdminsController extends AppViewsController {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public ApplicationLayout getAppPage() {
        return new ViewAllDataLayout("./src/main/resources/daycare_landing_background.jpg", ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }

    private String[][] getDataForTable() {
        List<Admin> list = adminRepository.findAll();
        String[][] data = new String[list.size()][7];
        for (int i = 0; i < list.size(); i++) {
            int j = 0;
            Admin admin = list.get(i);
            data[i][j++] = Integer.toString(i + 1);
            data[i][j++] = admin.getFirstName();
            data[i][j++] = admin.getLastName();
            data[i][j++] = Integer.toString(admin.getAge());
            data[i][j++] = admin.getParentFullName();
            data[i][j] = admin.getEmailId();
        }
        return data;
    }

    @Override
    protected void onCreate(Listeners.AppControlEventListener appControlListener) {
        super.onCreate(appControlListener);
        String[] header = new String[]{"Sl.No", "First Name", "Last Name", "Age (Years)", "Parents Name", "Email Id"};
        ((ViewAllDataLayout) this.getCurrentFrame()).setUpDataForTable(header, this.getDataForTable(), "Admins");
    }

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {

    }
}
