package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.models.ClassSections;
import edu.neu.csye6200.repositories.ClassRoomRepository;
import edu.neu.csye6200.views.ApplicationLayout;
import edu.neu.csye6200.views.ViewAllDataLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SaiAkhil
 */
@Service
public class ViewAllClassRoomsController extends AppViewsController {

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Override
    public ApplicationLayout getAppPage() {
        return new ViewAllDataLayout("./src/main/resources/daycare_landing_background.jpg", ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }

    private String[][] getDataForTable() {
        List<ClassSections> list = classRoomRepository.findAll();
        String[][] data = new String[list.size()][8];
        for (int i = 0; i < list.size(); i++) {
            int j = 0;
            ClassSections classSections = list.get(i);
            data[i][j++] = Integer.toString(i + 1);
            data[i][j++] = Integer.toString(classSections.getClassRoomId());
            data[i][j++] = classSections.getStudentFirstNames();
            data[i][j++] = classSections.getStudentIds().replaceAll("#", "");
            data[i][j++] = classSections.getTeacherFirstNames();
            data[i][j++] = classSections.getTeacherIds().replaceAll("#", "");
            data[i][j++] = Integer.toString(classSections.getMaxAge());
            data[i][j] = Integer.toString(classSections.getCurrentCapacity());
        }
        return data;
    }

    @Override
    protected void onCreate(Listeners.AppControlEventListener appControlListener) {
        super.onCreate(appControlListener);
        String[] header = new String[]{"Sl.No", "Class Room Id", "StudentNames", "StudentIds", "TeacherNames", "TeacherIds", "Max Capacity", "Current Capacity"};
        ((ViewAllDataLayout) this.getCurrentFrame()).setUpDataForTable(header, this.getDataForTable(), "Classrooms");
    }

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {

    }
}
