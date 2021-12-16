package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.Utils.FunctionalUtilities;
import edu.neu.csye6200.models.ClassRules;
import edu.neu.csye6200.repositories.ClassRulesRepository;
import edu.neu.csye6200.views.ApplicationLayout;
import edu.neu.csye6200.views.ClassRulesLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SaiAkhil
 */
@Service
public class ClassRulesCrudController extends AppViewsController {

    @Autowired
    private ClassRulesRepository classRulesRepository;

    @Override
    public ApplicationLayout getAppPage() {
        return new ClassRulesLayout("./src/main/resources/daycare_landing_background.jpg", ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }

    private String[][] generateData() {
        List<ClassRules> list = classRulesRepository.findAll();
        String[][] data = new String[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            int j = 0;
            ClassRules classRules = list.get(i);
            data[i][j++] = Integer.toString(classRules.getClassId());
            data[i][j++] = String.format("%d - %d", classRules.getMinAge(), classRules.getMaxAge());
            data[i][j++] = Integer.toString(classRules.getMaxGroupsPerClassRoom());
            data[i][j] = String.format("%d:1", classRules.getStudentTeacherRation());
        }
        return data;
    }

    @Override
    protected void onCreate(Listeners.AppControlEventListener appControlListener) {
        super.onCreate(appControlListener);
        this.setUpTableData();
        ((ClassRulesLayout) this.getCurrentFrame()).setDbCrudCallBack(dbCrudFunction);
    }

    private void setUpTableData() {
        String[] header = new String[]{"Class Id", "Age Group", "Max Groups Per Class", "Student Teacher Ration"};
        ((ClassRulesLayout) this.getCurrentFrame()).setUpDataForTable(this.generateData());
    }

    protected FunctionalUtilities.BiFunctionWithReturnType<Object, Integer, Boolean> dbCrudFunction = (classRules, eventType) -> {
        classRulesRepository.saveAll((List<ClassRules>) classRules);
        this.setUpTableData();
        return true;
    };

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {

    }
}
