package edu.neu.csye6200.models;

import edu.neu.csye6200.Utils.Utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author SaiAkhil
 */
@Entity(name = "table_class_rules")
public class ClassRules {
    @Id
    @Column(name = "class_id")
    private int classId;
    @Column(name = "min_age", nullable = false)
    private int minAge;
    @Column(name = "max_age", nullable = false)
    private int maxAge;
    @Column(name = "max_groups_per_class", nullable = false)
    private int maxGroupsPerClassRoom;
    @Column(name = "student_teacher_ratio", nullable = false)
    private int studentTeacherRation;

    public ClassRules() {
    }

    public ClassRules(String line) {
        this();
        String[] cols = line.split(",");
        int i = 0;
        this.classId = Utils.parseInteger(cols[i++]);
        String[] minMax = cols[i++].split("-");
        this.minAge = Utils.parseInteger(minMax[0]);
        this.maxAge = Utils.parseInteger(minMax[1]);
        this.maxGroupsPerClassRoom = Utils.parseInteger(cols[i++]);
        this.studentTeacherRation = Utils.parseInteger(cols[i].split(":")[0]);
    }

    public ClassRules(int classId, int minAge, int maxAge, int maxGroupsPerClassRoom, int studentTeacherRation) {
        this.classId = classId;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.maxGroupsPerClassRoom = maxGroupsPerClassRoom;
        this.studentTeacherRation = studentTeacherRation;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getMaxGroupsPerClassRoom() {
        return maxGroupsPerClassRoom;
    }

    public void setMaxGroupsPerClassRoom(int maxGroupsPerClassRoom) {
        this.maxGroupsPerClassRoom = maxGroupsPerClassRoom;
    }

    public int getStudentTeacherRation() {
        return studentTeacherRation;
    }

    public void setStudentTeacherRation(int studentTeacherRation) {
        this.studentTeacherRation = studentTeacherRation;
    }
}
