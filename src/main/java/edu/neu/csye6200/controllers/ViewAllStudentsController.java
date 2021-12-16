package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.models.Student;
import edu.neu.csye6200.repositories.StudentRepository;
import edu.neu.csye6200.views.ApplicationLayout;
import edu.neu.csye6200.views.ViewAllDataLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SaiAkhil
 */
@Service
public class ViewAllStudentsController extends AppViewsController {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public ApplicationLayout getAppPage() {
        return new ViewAllDataLayout("./src/main/resources/daycare_landing_background.jpg", ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }

    private String[][] getDataForTable() {
        List<Student> list = studentRepository.findAll();
        String[][] data = new String[list.size()][8];
        for (int i = 0; i < list.size(); i++) {
            int j = 0;
            Student student = list.get(i);
            data[i][j++] = Integer.toString(i + 1);
            data[i][j++] = student.getFirstName();
            data[i][j++] = student.getLastName();
            data[i][j++] = Integer.toString(student.getAge());
            data[i][j++] = student.getParentFullName();
            data[i][j++] = student.getEmailId();
            data[i][j] = Double.toString(student.getGpa());
        }
        return data;
    }

    @Override
    protected void onCreate(Listeners.AppControlEventListener appControlListener) {
        super.onCreate(appControlListener);
        String[] header = new String[]{"Sl.No", "First Name", "Last Name", "Age", "Parents Name", "Email Id", "GPA"};
        ((ViewAllDataLayout) this.getCurrentFrame()).setUpDataForTable(header, this.getDataForTable(), "Students");
    }

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {

    }
}
