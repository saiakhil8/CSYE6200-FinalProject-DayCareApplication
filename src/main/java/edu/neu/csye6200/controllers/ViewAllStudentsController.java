package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.Utils.FunctionalUtilities;
import edu.neu.csye6200.Utils.Utils;
import edu.neu.csye6200.models.ClassSections;
import edu.neu.csye6200.models.Student;
import edu.neu.csye6200.models.Teacher;
import edu.neu.csye6200.repositories.ClassRoomRepository;
import edu.neu.csye6200.repositories.StudentRepository;
import edu.neu.csye6200.views.ApplicationLayout;
import edu.neu.csye6200.views.ViewAllDataLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author SaiAkhil
 */
@Service
public class ViewAllStudentsController extends AppViewsController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Override
    public ApplicationLayout getAppPage() {
        return new ViewAllDataLayout("./src/main/resources/daycare_landing_background.jpg", ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }

    private String[][] getDataForTable(List<Student> list) {
        String[][] data = new String[list.size()][9];
        for (int i = 0; i < list.size(); i++) {
            int j = 0;
            Student student = list.get(i);
            data[i][j++] = Integer.toString(i + 1);
            data[i][j++] = Integer.toString(student.getId());
            data[i][j++] = student.getFirstName();
            data[i][j++] = student.getLastName();
            data[i][j++] = Integer.toString(student.getAge());
            data[i][j++] = student.getParentFullName();
            data[i][j++] = student.getEmailId();
            data[i][j] = Double.toString(student.getGpa());
        }
        return data;
    }

    private FunctionalUtilities.BiFunction<Object, Object> UPDATE_GPA_FUN = (studentId, gpa) -> {
        Optional<Student> studentOpt = studentRepository.findById((int) studentId);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.setGpa((double) gpa);
            studentRepository.save(student);
        }
    };

    @Override
    protected void onCreate(Listeners.AppControlEventListener appControlListener) {
        super.onCreate(appControlListener);
        String[] header = new String[]{"Sl.No", "Student Id", "First Name", "Last Name", "Age (Months)", "Parents Name", "Email Id", "GPA"};
        List<Student> studentList;
        if (this.getLoggedInUserType() == Constants.SESSION_ADMIN) studentList = studentRepository.findAll();
        else if (this.getLoggedInUserType() == Constants.SESSION_TEACHER) {
            studentList = this.getTeacherSpecificList();
        } else return;
        ((ViewAllDataLayout) this.getCurrentFrame()).setUpDataForTable(header, this.getDataForTable(studentList), "Students");
        ((ViewAllDataLayout) this.getCurrentFrame()).setUpBiFunction(UPDATE_GPA_FUN);
    }

    private List<Student> getTeacherSpecificList() {
        Teacher teacher = (Teacher) this.getCurrentLoggedInUser();
        Optional<ClassSections> classSections = this.classRoomRepository.findById(teacher.getAssignedClassRoomId());
        if (classSections.isEmpty()) return new ArrayList<>();
        ClassSections classSection = classSections.get();
        String[] teacherIds = classSection.getTeacherIds().replaceAll("#", "").split(",");
        String[] studentIds = classSection.getStudentIds().replaceAll("#", "").split(",");
        int teacherGroup = -1;
        for (int i = 0; i < teacherIds.length; i++) {
            if (teacherIds[i].trim().equalsIgnoreCase(Integer.toString(teacher.getId()))) {
                teacherGroup = i;
                break;
            }
        }
        if (teacherGroup == -1) return new ArrayList<>();
        List<Student> studentList = new ArrayList<>();
        for (int j = classSection.getGroupSize() * teacherGroup; j < classSection.getGroupSize() * (teacherGroup + 1) && j < classSection.getCurrentCapacity(); j++) {
            System.out.println(Utils.parseInteger(studentIds[j]));
            Optional<Student> student = studentRepository.findById(Utils.parseInteger(studentIds[j]));
            student.ifPresent(studentList::add);
        }
        return studentList;
    }

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {

    }


}
