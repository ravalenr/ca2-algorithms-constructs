package CA_2;

/**
 * Teacher class represents teaching staff in the school management system.
 * This is a concrete implementation of the Employee class.
 *
 * Teachers are responsible for delivering instruction, assessing student performance,
 * and managing classroom activities in their subject area.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class Teacher extends Employee {

    private String subject;
    private String gradeLevel;

    /**
     * Default constructor
     * Creates a Teacher object with default values
     */
    public Teacher() {
        super();
        this.subject = "";
        this.gradeLevel = "";
    }

    /**
     * Parameterized constructor
     * Creates a Teacher object with specified values
     *
     * @param firstName First name of the teacher
     * @param lastName Last name of the teacher
     * @param gender Gender of the teacher
     * @param email Email address of the teacher
     * @param salary Annual salary of the teacher
     * @param position Position level (senior, middle, junior)
     * @param jobTitle Job title of the teacher
     * @param company School name
     */
    public Teacher(String firstName, String lastName, String gender, String email,
                  double salary, String position, String jobTitle, String company) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company);
        this.subject = "";
        this.gradeLevel = "";
    }

    /**
     * Returns the subject taught by this teacher
     *
     * @return The subject as a String
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject taught by this teacher
     *
     * @param subject The subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Returns the grade level taught by this teacher
     *
     * @return The grade level as a String
     */
    public String getGradeLevel() {
        return gradeLevel;
    }

    /**
     * Sets the grade level taught by this teacher
     *
     * @param gradeLevel The grade level to set
     */
    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    /**
     * Returns a string representation of the Teacher object
     *
     * @return String containing teacher details
     */
    @Override
    public String toString() {
        return "Teacher{" +
                "ID='" + employeeId + '\'' +
                ", Name='" + getFullName() + '\'' +
                ", Subject='" + subject + '\'' +
                ", Grade='" + gradeLevel + '\'' +
                ", Department=" + (department != null ? department.getDepartmentName() : "None") +
                '}';
    }
}
