package edu.neu.csye6200.models;

import javax.persistence.Entity;

/**
 * @author SaiAkhil
 */
@Entity(name = "table_admin")
public class Admin extends Person {
    public Admin() {
    }

    ;

    public Admin(String firstName, String lastName, String emailId, String password, String dateOfBirth, String parentFullName, String lastUpdated, String lastLogin, String address) {
        super(firstName, lastName, emailId, password, dateOfBirth, parentFullName, lastUpdated, lastLogin, address);
    }

}
