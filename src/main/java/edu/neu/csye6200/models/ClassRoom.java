package edu.neu.csye6200.models;

import java.util.List;

/**
 * @author SaiAkhil
 */
public class ClassRoom extends ClassSections {

    private List<Teacher> teacherList;
    private List<Student> studentList;
    private int credits;
    private double gpa;

    public ClassRoom() {

    }

    public ClassRoom(List<Teacher> teacherList, List<Student> studentList) {
        super();
        this.teacherList = teacherList;
        this.studentList = studentList;
    }

    public ClassRoom(int classRuleId, int minAge, int maxAge, int maxCapacity, int groupSize, String studentIds, String studentName, String teacherIds, String teacherName, List<Teacher> teacherList, List<Student> studentList) {
        super(classRuleId, minAge, maxAge, maxCapacity, groupSize, studentIds, studentName, teacherIds, teacherName);
        this.teacherList = teacherList;
        this.studentList = studentList;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
