package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.Utils.FunctionalUtilities;
import edu.neu.csye6200.models.Vaccine;
import edu.neu.csye6200.repositories.VaccineRepository;
import edu.neu.csye6200.views.ApplicationLayout;
import edu.neu.csye6200.views.ViewAndReImportLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SaiAkhil
 */
@Service
public class ViewAllVaccines extends AppViewsController {

    @Autowired
    private VaccineRepository vaccineRepository;

    @Override
    public ApplicationLayout getAppPage() {
        return new ViewAndReImportLayout("./src/main/resources/daycare_landing_background.jpg", ApplicationLayout.BACKGROUND_TYPE_IMAGE);
    }

    @Override
    protected void onCreate(Listeners.AppControlEventListener appControlListener) {
        super.onCreate(appControlListener);
        this.setUpTableData();
        ((ViewAndReImportLayout) this.getCurrentFrame()).setDbCrudCallBack(dbCrudFunction);
    }

    private void setUpTableData() {
        String[] header = new String[]{"Id", "Vaccine Name", "Doses", "Gap1", "Gap2", "Gap3", "Gap4", "Extras"};
        ((ViewAndReImportLayout) this.getCurrentFrame()).setUpDataForTable(header, this.generateData(), "Class Rules");
    }

    private String[][] generateData() {
        List<Vaccine> list = vaccineRepository.findAll();
        String[][] data = new String[list.size()][8];
        for (int i = 0; i < list.size(); i++) {
            int j = 0;
            Vaccine vaccine = list.get(i);
            data[i][j++] = Integer.toString(vaccine.getVaccineId());
            data[i][j++] = vaccine.getVaccineName();
            data[i][j++] = Integer.toString(vaccine.getFirstDoseGap());
            data[i][j++] = Integer.toString(vaccine.getSecondDoseGap());
            data[i][j++] = Integer.toString(vaccine.getThirdDoseGap());
            data[i][j++] = Integer.toString(vaccine.getFourthDoseGap());
            data[i][j] = vaccine.getExtraDoses();
        }
        return data;
    }

    protected FunctionalUtilities.BiFunctionWithReturnType<Object, Integer, Boolean> dbCrudFunction = (vaccines, eventType) -> {
        vaccineRepository.deleteAll();
        ((List<String>) vaccines).forEach(line -> {
            vaccineRepository.save(new Vaccine(line));
        });
        this.setUpTableData();
        return true;
    };

    @Override
    protected void goToNextScreen(Listeners.AppControlEventListener appControlListener) {

    }
}
