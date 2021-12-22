package edu.neu.csye6200.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author SaiAkhil
 */
@Entity(name = "table_classrooms")
public class ClassSections {
    @Id
    @GeneratedValue
    @Column(name = "class_room_id")
    private int classRoomId;
    @Column(name = "class_rule_id")
    private int classRuleId;
    @Column(name = "min_age")
    private int minAge;
    @Column(name = "max_age")
    private int maxAge;
    @Column(name = "max_class_size")
    private int maxCapacity;
    @Column(name = "group_size")
    private int groupSize;
    @Column(name = "student_ids")
    private String studentIds;
    @Column(name = "teacher_ids")
    private String teacherIds;
    @Column(name = "current_capacity", columnDefinition = "int default 1")
    private int currentCapacity;
    @Column(name = "student_first_names", nullable = false)
    private String studentFirstNames;
    @Column(name = "teacher_last_name", nullable = false)
    private String teacherFirstNames;

    public ClassSections() {
    }

    public ClassSections(int classRuleId, int minAge, int maxAge, int maxCapacity, int groupSize, String studentIds, String studentName, String teacherIds, String teacherName) {
        this.classRuleId = classRuleId;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.maxCapacity = maxCapacity;
        this.groupSize = groupSize;
        this.studentIds = studentIds;
        this.teacherIds = teacherIds;
        this.currentCapacity = 1;
        this.studentFirstNames = studentName;
        this.teacherFirstNames = teacherName;
    }

    public int getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(int classRoomId) {
        this.classRoomId = classRoomId;
    }

    public int getClassRuleId() {
        return classRuleId;
    }

    public void setClassRuleId(int classRuleId) {
        this.classRuleId = classRuleId;
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

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxClassSize) {
        this.maxCapacity = maxClassSize;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public String getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(String studentIds) {
        this.studentIds = studentIds;
    }

    public void addStudent(int studentId, String studentName) {
        this.studentIds += String.format(",#%d#", studentId);
        this.studentFirstNames += ", " + studentName;
        this.currentCapacity++;
    }

    public void addStudent(int studentId, String studentName, int teacherId, String teacherName) {
        this.teacherIds += String.format(",#%d#", teacherId);
        this.teacherFirstNames += ", " + teacherName;
        this.addStudent(studentId, studentName);
    }

    public String getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public String getStudentFirstNames() {
        return studentFirstNames;
    }

    public void setStudentFirstNames(String studentFirstNames) {
        this.studentFirstNames = studentFirstNames;
    }

    public String getTeacherFirstNames() {
        return teacherFirstNames;
    }

    public void setTeacherFirstNames(String teacherFirstNames) {
        this.teacherFirstNames = teacherFirstNames;
    }
}
