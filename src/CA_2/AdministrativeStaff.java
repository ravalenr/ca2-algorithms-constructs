package CA_2;

/**
 * AdministrativeStaff class represents administrative employees in the school system.
 * This class extends Employee and includes specific attributes for administrative duties.
 *
 * Design Decision: AdministrativeStaff handles non-teaching responsibilities such as
 * processing applications, maintaining records, and providing administrative support.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class AdministrativeStaff extends Employee {

    // Additional attributes specific to AdministrativeStaff
    private String role;
    private String workstation;

    /**
     * Default constructor
     * Creates an AdministrativeStaff with default values
     */
    public AdministrativeStaff() {
        super();
        this.role = "";
        this.workstation = "";
    }

    /**
     * Parameterized constructor
     * Creates an AdministrativeStaff with specified values
     *
     * @param firstName First name of the staff member
     * @param lastName Last name of the staff member
     * @param gender Gender of the staff member
     * @param email Email address of the staff member
     * @param salary Annual salary of the staff member
     * @param position Position level
     * @param jobTitle Job title
     * @param company School/organization name
     */
    public AdministrativeStaff(String firstName, String lastName, String gender, String email,
                              double salary, String position, String jobTitle, String company) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company);
        this.role = "General Administration";
        this.workstation = "Main Office";
    }

    /**
     * Parameterized constructor with role and workstation
     *
     * @param firstName First name of the staff member
     * @param lastName Last name of the staff member
     * @param gender Gender of the staff member
     * @param email Email address of the staff member
     * @param salary Annual salary of the staff member
     * @param position Position level
     * @param jobTitle Job title
     * @param company School/organization name
     * @param role Specific role within administration
     * @param workstation Assigned workstation location
     */
    public AdministrativeStaff(String firstName, String lastName, String gender, String email,
                              double salary, String position, String jobTitle, String company,
                              String role, String workstation) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company);
        this.role = role;
        this.workstation = workstation;
    }

    /**
     * Returns the administrative role
     * @return role as String
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the administrative role
     * @param role The role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Returns the workstation location
     * @return workstation as String
     */
    public String getWorkstation() {
        return workstation;
    }

    /**
     * Sets the workstation location
     * @param workstation The workstation to set
     */
    public void setWorkstation(String workstation) {
        this.workstation = workstation;
    }

    /**
     * Processes applications (student, staff, etc.)
     * This method simulates application processing functionality
     *
     * @param applicationType Type of application
     * @param applicationId Application identifier
     */
    public void processApplications(String applicationType, String applicationId) {
        if (applicationType == null || applicationType.trim().isEmpty()) {
            System.out.println("Application type required.");
            return;
        }

        System.out.println("Processing Application:");
        System.out.println("Staff Member: " + getFullName());
        System.out.println("Role: " + role);
        System.out.println("Workstation: " + workstation);
        System.out.println("Application Type: " + applicationType);
        System.out.println("Application ID: " + (applicationId != null ? applicationId : "Not provided"));
        System.out.println("Status: Under review");
    }

    /**
     * Maintains various administrative records
     * This method simulates record maintenance functionality
     *
     * @param recordType Type of record to maintain
     */
    public void maintainRecords(String recordType) {
        if (recordType == null || recordType.trim().isEmpty()) {
            System.out.println("Record type required.");
            return;
        }

        System.out.println("Record Maintenance:");
        System.out.println("Staff Member: " + getFullName());
        System.out.println("Department: " + (department != null ? department.getDepartmentName() : "Not assigned"));
        System.out.println("Record Type: " + recordType);
        System.out.println("Workstation: " + workstation);
        System.out.println("Status: Records being updated");
    }

    /**
     * Provides administrative assistance
     * This method simulates general assistance functionality
     *
     * @param taskDescription Description of the task
     */
    public void provideAssistance(String taskDescription) {
        if (taskDescription == null || taskDescription.trim().isEmpty()) {
            System.out.println("Task description required.");
            return;
        }

        System.out.println("Administrative Assistance:");
        System.out.println("Staff Member: " + getFullName());
        System.out.println("Role: " + role);
        System.out.println("Task: " + taskDescription);
        System.out.println("Manager: " + (manager != null ? manager.getFullName() : "None"));
    }

    /**
     * Displays staff member's profile
     */
    public void displayStaffProfile() {
        System.out.println("Administrative Staff Profile:");
        System.out.println("Name: " + getFullName());
        System.out.println("Email: " + email);
        System.out.println("Role: " + role);
        System.out.println("Workstation: " + workstation);
        System.out.println("Job Title: " + jobTitle);
        System.out.println("Department: " + (department != null ? department.getDepartmentName() : "Not assigned"));
        System.out.println("Manager: " + (manager != null ? manager.getFullName() : "None"));
    }

    /**
     * Returns a string representation of the AdministrativeStaff
     *
     * @return String containing administrative staff details
     */
    @Override
    public String toString() {
        return "AdministrativeStaff{" +
                "Name='" + getFullName() + '\'' +
                ", ID='" + employeeId + '\'' +
                ", Role='" + role + '\'' +
                ", Workstation='" + workstation + '\'' +
                ", JobTitle='" + jobTitle + '\'' +
                ", Department='" + (department != null ? department.getDepartmentName() : "None") + '\'' +
                '}';
    }
}
