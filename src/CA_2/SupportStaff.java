package CA_2;

/**
 * SupportStaff class represents support service employees in the school system.
 * This class extends Employee and includes specific attributes for support duties.
 *
 * Design Decision: SupportStaff handles facilities, maintenance, IT support,
 * and other essential support services that keep the school operational.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class SupportStaff extends Employee {

    // Additional attributes specific to SupportStaff
    private String specialization;
    private String shift;

    /**
     * Default constructor
     * Creates a SupportStaff with default values
     */
    public SupportStaff() {
        super();
        this.specialization = "";
        this.shift = "";
    }

    /**
     * Parameterized constructor
     * Creates a SupportStaff with specified values
     *
     * @param firstName First name of the support staff
     * @param lastName Last name of the support staff
     * @param gender Gender of the support staff
     * @param email Email address of the support staff
     * @param salary Annual salary of the support staff
     * @param position Position level
     * @param jobTitle Job title
     * @param company School/organization name
     */
    public SupportStaff(String firstName, String lastName, String gender, String email,
                       double salary, String position, String jobTitle, String company) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company);
        this.specialization = "General Support";
        this.shift = "Day Shift";
    }

    /**
     * Parameterized constructor with specialization and shift
     *
     * @param firstName First name of the support staff
     * @param lastName Last name of the support staff
     * @param gender Gender of the support staff
     * @param email Email address of the support staff
     * @param salary Annual salary of the support staff
     * @param position Position level
     * @param jobTitle Job title
     * @param company School/organization name
     * @param specialization Area of specialization
     * @param shift Work shift assignment
     */
    public SupportStaff(String firstName, String lastName, String gender, String email,
                       double salary, String position, String jobTitle, String company,
                       String specialization, String shift) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company);
        this.specialization = specialization;
        this.shift = shift;
    }

    /**
     * Returns the specialization
     * @return specialization as String
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Sets the specialization
     * @param specialization The specialization to set
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * Returns the work shift
     * @return shift as String
     */
    public String getShift() {
        return shift;
    }

    /**
     * Sets the work shift
     * @param shift The shift to set
     */
    public void setShift(String shift) {
        this.shift = shift;
    }

    /**
     * Provides support services based on specialization
     * This method simulates support service provision
     *
     * @param serviceType Type of support service needed
     * @param location Location where service is needed
     */
    public void provideSupportService(String serviceType, String location) {
        if (serviceType == null || serviceType.trim().isEmpty()) {
            System.out.println("Service type required.");
            return;
        }

        System.out.println("Support Service:");
        System.out.println("Staff Member: " + getFullName());
        System.out.println("Specialization: " + specialization);
        System.out.println("Service Type: " + serviceType);
        System.out.println("Location: " + (location != null ? location : "Not specified"));
        System.out.println("Shift: " + shift);
        System.out.println("Status: Service in progress");
    }

    /**
     * Reports issues that need attention
     * This method simulates issue reporting functionality
     *
     * @param issueDescription Description of the issue
     * @param priority Priority level (High, Medium, Low)
     */
    public void reportIssues(String issueDescription, String priority) {
        if (issueDescription == null || issueDescription.trim().isEmpty()) {
            System.out.println("Issue description required.");
            return;
        }

        System.out.println("Issue Report:");
        System.out.println("Reported by: " + getFullName());
        System.out.println("Specialization: " + specialization);
        System.out.println("Issue: " + issueDescription);
        System.out.println("Priority: " + (priority != null ? priority : "Medium"));
        System.out.println("Department: " + (department != null ? department.getDepartmentName() : "Not assigned"));
        System.out.println("Manager: " + (manager != null ? manager.getFullName() : "None"));
        System.out.println("Status: Reported and logged");
    }

    /**
     * Performs maintenance tasks
     * This method simulates maintenance work
     *
     * @param maintenanceType Type of maintenance
     * @param equipmentOrArea Equipment or area being maintained
     */
    public void performMaintenance(String maintenanceType, String equipmentOrArea) {
        if (maintenanceType == null || maintenanceType.trim().isEmpty()) {
            System.out.println("Maintenance type required.");
            return;
        }

        System.out.println("Maintenance Task:");
        System.out.println("Staff Member: " + getFullName());
        System.out.println("Specialization: " + specialization);
        System.out.println("Maintenance Type: " + maintenanceType);
        System.out.println("Equipment/Area: " + (equipmentOrArea != null ? equipmentOrArea : "Not specified"));
        System.out.println("Shift: " + shift);
        System.out.println("Status: Maintenance in progress");
    }

    /**
     * Displays support staff profile
     */
    public void displaySupportProfile() {
        System.out.println("Support Staff Profile:");
        System.out.println("Name: " + getFullName());
        System.out.println("Email: " + email);
        System.out.println("Specialization: " + specialization);
        System.out.println("Shift: " + shift);
        System.out.println("Job Title: " + jobTitle);
        System.out.println("Department: " + (department != null ? department.getDepartmentName() : "Not assigned"));
        System.out.println("Manager: " + (manager != null ? manager.getFullName() : "None"));
    }

    /**
     * Returns a string representation of the SupportStaff
     *
     * @return String containing support staff details
     */
    @Override
    public String toString() {
        return "SupportStaff{" +
                "Name='" + getFullName() + '\'' +
                ", ID='" + employeeId + '\'' +
                ", Specialization='" + specialization + '\'' +
                ", Shift='" + shift + '\'' +
                ", JobTitle='" + jobTitle + '\'' +
                ", Department='" + (department != null ? department.getDepartmentName() : "None") + '\'' +
                '}';
    }
}
