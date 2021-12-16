package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.Utils.FunctionalUtilities;
import edu.neu.csye6200.models.Teacher;
import edu.neu.csye6200.repositories.TeacherRepository;
import edu.neu.csye6200.views.AddPersonLayout;
import edu.neu.csye6200.views.AddTeacherLayout;
import edu.neu.csye6200.views.ApplicationLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SaiAkhil
 */
@Service
public class AddTeacherController extends AppViewsController {
    @Autowired
    private TeacherRepository teacherRepository;


    @Override
    public ApplicationLayout getAppPage() {
        return new AddTeacherLayout(Constants.getImageFromName("teacher.jpeg"), ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }

    @Override
    protected void onCreate(Listeners.AppControlEventListener appControlListener) {
        super.onCreate(appControlListener);
        ((AddPersonLayout) this.getCurrentFrame()).setDbCrudCallBack(dbCrudFunction);
    }

    protected FunctionalUtilities.BiFunctionWithReturnType<Object, Integer, Boolean> dbCrudFunction = (teacher, eventType) -> {
        teacherRepository.save((Teacher) teacher);
        return true;
    };

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {

    }
}
