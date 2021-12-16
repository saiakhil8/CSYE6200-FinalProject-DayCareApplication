package edu.neu.csye6200.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author SaiAkhil
 */
@Entity(name = "table_immunization_tracker")
public class ImmunizationTracker {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "student_id")
    private int studentId;
    @Column(name = "immunatization_details")
    private String immunizationDetails;
    @Column(name = "upcoming_due_date")
    private String upcomingDueDate;
    @Column(name = "due_date_message")
    private String upcoming_due_date_message;

    public ImmunizationTracker() {
    }

    public ImmunizationTracker(int id, int studentId, String immunizationDetails, String upcomingDueDate, String upcoming_due_date_message) {
        this.id = id;
        this.studentId = studentId;
        this.immunizationDetails = immunizationDetails;
        this.upcomingDueDate = upcomingDueDate;
        this.upcoming_due_date_message = upcoming_due_date_message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getImmunizationDetails() {
        return immunizationDetails;
    }

    public void setImmunizationDetails(String immunizationDetails) {
        this.immunizationDetails = immunizationDetails;
    }

    public String getUpcomingDueDate() {
        return upcomingDueDate;
    }

    public void setUpcomingDueDate(String upcomingDueDate) {
        this.upcomingDueDate = upcomingDueDate;
    }

    public String getUpcoming_due_date_message() {
        return upcoming_due_date_message;
    }

    public void setUpcoming_due_date_message(String upcoming_due_date_message) {
        this.upcoming_due_date_message = upcoming_due_date_message;
    }
}
