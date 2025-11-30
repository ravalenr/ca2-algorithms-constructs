package CA_2;

/**
 * Teacher class represents teaching staff in the school.
 * Extends the Employee class.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class Teacher extends Employee {

    public Teacher() {
        super();
    }

    public Teacher(String firstName, String lastName, String gender, String email,
                  double salary, String position, String jobTitle, String company) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company);
    }
}
