package CA_2;

/**
 * VicePrincipal class represents the second level of school management.
 * This class extends Manager and includes specific attributes for vice principal responsibilities.
 *
 * Design Decision: VicePrincipal assists the Principal and handles specific areas of responsibility
 * such as student discipline, event organization, and daily operations.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class VicePrincipal extends Manager {

    // Additional attributes specific to VicePrincipal
    private String areaOfResponsibility;

    /**
     * Default constructor
     * Creates a VicePrincipal with default values
     */
    public VicePrincipal() {
        super();
        this.areaOfResponsibility = "";
        this.managerType = ManagerType.VICE_PRINCIPAL;
    }

    /**
     * Parameterized constructor
     * Creates a VicePrincipal with specified values
     *
     * @param firstName First name of the vice principal
     * @param lastName Last name of the vice principal
     * @param gender Gender of the vice principal
     * @param email Email address of the vice principal
     * @param salary Annual salary of the vice principal
     * @param position Position level
     * @param jobTitle Job title
     * @param company School/organization name
     */
    public VicePrincipal(String firstName, String lastName, String gender, String email,
                        double salary, String position, String jobTitle, String company) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company, ManagerType.VICE_PRINCIPAL);
        this.areaOfResponsibility = "General Administration";
    }

    /**
     * Parameterized constructor with area of responsibility
     *
     * @param firstName First name of the vice principal
     * @param lastName Last name of the vice principal
     * @param gender Gender of the vice principal
     * @param email Email address of the vice principal
     * @param salary Annual salary of the vice principal
     * @param position Position level
     * @param jobTitle Job title
     * @param company School/organization name
     * @param areaOfResponsibility Specific area of responsibility
     */
    public VicePrincipal(String firstName, String lastName, String gender, String email,
                        double salary, String position, String jobTitle, String company,
                        String areaOfResponsibility) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company, ManagerType.VICE_PRINCIPAL);
        this.areaOfResponsibility = areaOfResponsibility;
    }

    /**
     * Returns the area of responsibility
     * @return areaOfResponsibility as String
     */
    public String getAreaOfResponsibility() {
        return areaOfResponsibility;
    }

    /**
     * Sets the area of responsibility
     * @param areaOfResponsibility The area of responsibility to set
     */
    public void setAreaOfResponsibility(String areaOfResponsibility) {
        this.areaOfResponsibility = areaOfResponsibility;
    }

    /**
     * Handles disciplinary matters for students
     * This method simulates disciplinary action process
     *
     * @param studentName Name of the student
     * @param issue Description of the disciplinary issue
     */
    public void handleDiscipline(String studentName, String issue) {
        if (studentName == null || studentName.trim().isEmpty()) {
            System.out.println("Invalid student name.");
            return;
        }

        if (issue == null || issue.trim().isEmpty()) {
            System.out.println("Issue description required.");
            return;
        }

        System.out.println("Disciplinary Action Handled by Vice Principal " + getFullName());
        System.out.println("Student: " + studentName);
        System.out.println("Issue: " + issue);
        System.out.println("Action: Meeting scheduled with student and parents.");
    }

    /**
     * Organizes school events
     * This method simulates event organization functionality
     *
     * @param eventName Name of the event
     * @param eventDate Date of the event
     */
    public void organizeEvents(String eventName, String eventDate) {
        if (eventName == null || eventName.trim().isEmpty()) {
            System.out.println("Event name required.");
            return;
        }

        System.out.println("Event Organization by Vice Principal " + getFullName());
        System.out.println("Event: " + eventName);
        System.out.println("Date: " + (eventDate != null ? eventDate : "To be determined"));
        System.out.println("Status: Planning in progress");
    }

    /**
     * Implementation of abstract method from Manager class
     * Returns specific information about the VicePrincipal
     *
     * @return String containing vice principal-specific information
     */
    @Override
    public String getManagerInfo() {
        return "Vice Principal Information:\n" +
               "  Area of Responsibility: " + areaOfResponsibility;
    }

    /**
     * Returns a string representation of the VicePrincipal
     *
     * @return String containing vice principal details
     */
    @Override
    public String toString() {
        return "VicePrincipal{" +
                "Name='" + getFullName() + '\'' +
                ", ManagerID='" + managerId + '\'' +
                ", Email='" + email + '\'' +
                ", AreaOfResponsibility='" + areaOfResponsibility + '\'' +
                ", EmployeesManaged=" + employeeCount +
                '}';
    }
}
