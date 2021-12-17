package edu.neu.csye6200.models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Map;

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
    private String upcomingDueDateMessage;

    public ImmunizationTracker() {
    }

    public ImmunizationTracker(int studentId, String immunizationDetails, String upcomingDueDate, String upcoming_due_date_message) {
        this.studentId = studentId;
        this.immunizationDetails = immunizationDetails;
        this.upcomingDueDate = upcomingDueDate;
        this.upcomingDueDateMessage = upcoming_due_date_message;
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

    public String getUpcomingDueDateMessage() {
        return upcomingDueDateMessage;
    }

    public void setUpcomingDueDateMessage(String upcoming_due_date_message) {
        this.upcomingDueDateMessage = upcoming_due_date_message;
    }

    public Map<String, Object> getImmunizationDetailsMap() {
        return new Gson().fromJson(this.immunizationDetails, new TypeToken<Map<String, Object>>() {
        }.getType());
    }
}
