package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.models.Teacher;
import edu.neu.csye6200.repositories.TeacherRepository;
import edu.neu.csye6200.views.ApplicationLayout;
import edu.neu.csye6200.views.ViewAllDataLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SaiAkhil
 */
@Service
public class ViewAllTeachersController extends AppViewsController {
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public ApplicationLayout getAppPage() {
        return new ViewAllDataLayout("./src/main/resources/daycare_landing_background.jpg", ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }

    private String[][] getDataForTable() {
        List<Teacher> list = teacherRepository.findAll();
        String[][] data = new String[list.size()][8];
        for (int i = 0; i < list.size(); i++) {
            int j = 0;
            Teacher teacher = list.get(i);
            data[i][j++] = Integer.toString(i + 1);
            data[i][j++] = teacher.getFirstName();
            data[i][j++] = teacher.getLastName();
            data[i][j++] = Integer.toString(teacher.getAge());
            data[i][j++] = teacher.getParentFullName();
            data[i][j++] = teacher.getEmailId();
            data[i][j] = Integer.toString(teacher.getHourlyWage());
        }
        return data;
    }

    @Override
    protected void onCreate(Listeners.AppControlEventListener appControlListener) {
        super.onCreate(appControlListener);
        String[] header = new String[]{"Sl.No", "First Name", "Last Name", "Age", "Parents Name", "Email Id", "Hourly Wage"};
        ((ViewAllDataLayout) this.getCurrentFrame()).setUpDataForTable(header, this.getDataForTable(), "Teachers");
    }

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {

    }
}
