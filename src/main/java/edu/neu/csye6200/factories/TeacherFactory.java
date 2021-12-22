package edu.neu.csye6200.factories;

import edu.neu.csye6200.Utils.Utils;
import edu.neu.csye6200.models.Person;
import edu.neu.csye6200.models.Teacher;

/**
 * @author SaiAkhil
 */
public class TeacherFactory extends AbstractPersonFactory {
    private static TeacherFactory mInstance;

    private TeacherFactory() {
    }

    @Override
    public Person getObject(String firstName, String lastName, String emailId, String dateOfBirth, String parentFullName, String address) {
        return new Teacher(firstName, lastName, emailId, dateOfBirth, parentFullName, address, 0, 0);
    }

    @Override
    public Person getObject() {
        return new Teacher();
    }

    @Override
    public Person getObject(String line) {
        String[] lines = line.split(",");
        int i = 0;
        Teacher teacher = new Teacher(lines[i++],
                lines[i++],
                lines[i++],
                lines[i++],
                lines[i++],
                lines[i++],
                Utils.parseInteger(lines[i++]),
                Utils.parseInteger(lines[i++]));
        return teacher;
    }

    public static TeacherFactory getInstance() {
        if (mInstance == null) {
            synchronized (TeacherFactory.class) {
                mInstance = new TeacherFactory();
            }
        }
        return mInstance;
    }
}
