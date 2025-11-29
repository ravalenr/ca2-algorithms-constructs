package CA_2;

/**
 * Teacher class represents teaching staff in the school.
 * Extends the Employee class.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class Teacher extends Employee {

    /**
     * Default constructor
     */
    public Teacher() {
        super();
    }

    /**
     * Constructor with parameters.
     *
     * @param firstName First name
     * @param lastName Last name
     * @param gender Gender
     * @param email Email address
     * @param salary Annual salary
     * @param position Position level
     * @param jobTitle Job title
     * @param company School name
     */
    public Teacher(String firstName, String lastName, String gender, String email,
                  double salary, String position, String jobTitle, String company) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "ID='" + employeeId + '\'' +
                ", Name='" + getFullName() + '\'' +
                ", Department=" + (department != null ? department.getDepartmentName() : "None") +
                '}';
    }
}
