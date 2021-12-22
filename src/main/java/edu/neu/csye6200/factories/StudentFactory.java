package edu.neu.csye6200.factories;

import edu.neu.csye6200.Utils.Utils;
import edu.neu.csye6200.models.Person;
import edu.neu.csye6200.models.Student;

/**
 * @author SaiAkhil
 */
public class StudentFactory extends AbstractPersonFactory {
    private static StudentFactory mInstance;

    private StudentFactory() {
    }

    ;

    @Override
    public Person getObject(String firstName, String lastName, String emailId, String dateOfBirth, String parentFullName, String address) {
        return new Student(firstName, lastName, emailId, dateOfBirth, parentFullName, address, 0.0);
    }

    @Override
    public Person getObject() {
        return new Student();
    }

    @Override
    public Person getObject(String line) {
        String[] lines = line.split(",");
        int i = 0;
        Student student = new Student(lines[i++],
                lines[i++],
                lines[i++],
                lines[i++],
                lines[i++],
                lines[i++],
                Utils.parseDouble(lines[i]));
        return student;
    }

    public static StudentFactory getInstance() {
        if (mInstance == null) {
            synchronized (StudentFactory.class) {
                mInstance = new StudentFactory();
            }
        }
        return mInstance;
    }
}
