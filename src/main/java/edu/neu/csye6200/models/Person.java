package edu.neu.csye6200.models;


import edu.neu.csye6200.Utils.Utils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author SaiAkhil
 */
@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email_id", unique = true, nullable = false)
    private String emailId;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;
    @Column(name = "parent_full_name", nullable = false)
    private String parentFullName;
    @Column(name = "last_updated", nullable = false)
    private String lastUpdated;
    @Column(name = "created_on", nullable = false)
    private String createdOn;
    @Column(name = "last_login", nullable = true)
    private String lastLogin;
    @Column(name = "address")
    private String address;
    @Column(name = "age", nullable = false)
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getParentFullName() {
        return parentFullName;
    }

    public void setParentFullName(String parentFullName) {
        this.parentFullName = parentFullName;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Person() {
    }

    public Person(String firstName, String lastName, String emailId, String dateOfBirth, String parentFullName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.password = Utils.GENERATE_PASSWORD.apply(null);
        this.dateOfBirth = dateOfBirth;
        this.parentFullName = parentFullName;
        this.lastUpdated = Utils.GET_DATE_STRING.apply(new Date());
        this.createdOn = this.lastUpdated;
        this.address = address;
        this.age = Utils.GET_AGE_FROM_DOB.apply(dateOfBirth);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
