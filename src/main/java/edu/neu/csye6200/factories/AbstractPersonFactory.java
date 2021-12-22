package edu.neu.csye6200.factories;

import edu.neu.csye6200.models.Person;

/**
 * @author SaiAkhil
 */
public abstract class AbstractPersonFactory {

    public abstract Person getObject(String firstName, String lastName, String emailId, String dateOfBirth, String parentFullName, String address);

    public abstract Person getObject();

    /**
     * Parses a given line and creates an object
     *
     * @param line of the csv
     * @return the object
     */
    public abstract Person getObject(String line);
}
