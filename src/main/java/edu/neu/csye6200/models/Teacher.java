package edu.neu.csye6200.models;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author SaiAkhil
 */
@Entity(name = "table_teacher")
public class Teacher extends Person {
    @Column(name = "credits")
    private int credits;
    @Column(name = "hourly_wage")
    private int hourlyWage;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String emailId, String dateOfBirth, String parentFullName, String address, int credits, int hourlyWage) {
        super(firstName, lastName, emailId, dateOfBirth, parentFullName, address);
        this.credits = credits;
        this.hourlyWage = hourlyWage;
    }


    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(int hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
}
