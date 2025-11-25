package CA_2;

/**
 * DepartmentHead class represents a department head in the school management system.
 * This is a concrete implementation of the Manager abstract class.
 *
 * A Department Head is responsible for managing a specific department,
 * overseeing curriculum development, staff performance, and departmental operations.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class DepartmentHead extends Manager {

    /**
     * Default constructor
     * Creates a DepartmentHead object with default values
     */
    public DepartmentHead() {
        super();
        this.managerType = ManagerType.DEPARTMENT_HEAD;
    }

    /**
     * Parameterized constructor
     * Creates a DepartmentHead object with specified values
     *
     * @param firstName First name of the department head
     * @param lastName Last name of the department head
     * @param gender Gender of the department head
     * @param email Email address of the department head
     * @param salary Annual salary of the department head
     * @param position Position level
     * @param jobTitle Job title of the department head
     * @param company School name
     */
    public DepartmentHead(String firstName, String lastName, String gender, String email,
                         double salary, String position, String jobTitle, String company) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company, ManagerType.DEPARTMENT_HEAD);
    }

    /**
     * Returns information specific to the Department Head role
     * This method provides details about the department head's responsibilities
     *
     * @return String containing department head-specific information
     */
    @Override
    public String getManagerInfo() {
        return "Role: Department Head - Manages department operations and curriculum. " +
               "Managing " + employeeCount + " staff members.";
    }
}
