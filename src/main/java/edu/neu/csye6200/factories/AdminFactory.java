package edu.neu.csye6200.factories;

import edu.neu.csye6200.models.Admin;
import edu.neu.csye6200.models.Person;

/**
 * @author SaiAkhil
 */
public class AdminFactory extends AbstractPersonFactory {
    private static AdminFactory mInstance;

    private AdminFactory() {
    }

    @Override
    public Person getObject(String firstName, String lastName, String emailId, String dateOfBirth, String parentFullName, String address) {
        return new Admin(firstName, lastName, emailId, dateOfBirth, parentFullName, address);
    }

    @Override
    public Person getObject() {
        return new Admin();
    }

    public static AdminFactory getInstance() {
        if (mInstance == null) {
            synchronized (AdminFactory.class) {
                mInstance = new AdminFactory();
            }
        }
        return mInstance;
    }


}
