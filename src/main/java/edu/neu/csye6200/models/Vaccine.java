package edu.neu.csye6200.models;

import edu.neu.csye6200.Utils.Utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author SaiAkhil
 */
@Entity(name = "table_vaccine")
public class Vaccine {

    @Id
    @GeneratedValue
    @Column(name = "vaccine_id")
    private int vaccineId;
    @Column(name = "vaccine_name")
    private String vaccineName;
    @Column(name = "no_of_doses")
    private int noOfDoses;
    @Column(name = "first_dose_gap")
    private int firstDoseGap;
    @Column(name = "second_dose_gap")
    private int secondDoseGap;
    @Column(name = "third_dose_gap")
    private int thirdDoseGap;
    @Column(name = "fourth_dose_gap")
    private int fourthDoseGap;
    @Column(name = "extra_doses")
    private String extraDoses;

    public Vaccine() {
    }

    public Vaccine(String[] cols) {
        this(cols[0],
                Utils.parseInteger(cols[1]),
                Utils.parseInteger(cols[2]),
                Utils.parseInteger(cols[3]),
                Utils.parseInteger(cols[4]),
                Utils.parseInteger(cols[5]),
                cols[5]);
    }

    public Vaccine(String line) {
        this(line.split(","));
    }

    public Vaccine(String vaccineName, int noOfDoses, int firstDoseGap, int secondDoseGap, int thirdDoseGap, int fourthDoseGap, String extraDoses) {
        this.vaccineName = vaccineName;
        this.noOfDoses = noOfDoses;
        this.firstDoseGap = firstDoseGap;
        this.secondDoseGap = secondDoseGap;
        this.thirdDoseGap = thirdDoseGap;
        this.fourthDoseGap = fourthDoseGap;
        this.extraDoses = extraDoses;
    }

    public int getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(int vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public int getNoOfDoses() {
        return noOfDoses;
    }

    public void setNoOfDoses(int noOfDoses) {
        this.noOfDoses = noOfDoses;
    }

    public int getFirstDoseGap() {
        return firstDoseGap;
    }

    public void setFirstDoseGap(int firstDoseGap) {
        this.firstDoseGap = firstDoseGap;
    }

    public int getSecondDoseGap() {
        return secondDoseGap;
    }

    public void setSecondDoseGap(int secondDoseGap) {
        this.secondDoseGap = secondDoseGap;
    }

    public int getThirdDoseGap() {
        return thirdDoseGap;
    }

    public void setThirdDoseGap(int thirdDoseGap) {
        this.thirdDoseGap = thirdDoseGap;
    }

    public int getFourthDoseGap() {
        return fourthDoseGap;
    }

    public void setFourthDoseGap(int fourthDoseGap) {
        this.fourthDoseGap = fourthDoseGap;
    }

    public String getExtraDoses() {
        return extraDoses;
    }

    public void setExtraDoses(String extraDoses) {
        this.extraDoses = extraDoses;
    }
}
