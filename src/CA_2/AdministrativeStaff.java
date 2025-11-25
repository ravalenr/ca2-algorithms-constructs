package CA_2;

/**
 * AdministrativeStaff class represents administrative personnel in the school management system.
 * This is a concrete implementation of the Employee class.
 *
 * Administrative staff handle office operations, record keeping, student registration,
 * communications, and general administrative support for the school.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class AdministrativeStaff extends Employee {

    private String officeLocation;
    private String responsibilities;

    /**
     * Default constructor
     * Creates an AdministrativeStaff object with default values
     */
    public AdministrativeStaff() {
        super();
        this.officeLocation = "";
        this.responsibilities = "";
    }

    /**
     * Parameterized constructor
     * Creates an AdministrativeStaff object with specified values
     *
     * @param firstName First name of the staff member
     * @param lastName Last name of the staff member
     * @param gender Gender of the staff member
     * @param email Email address of the staff member
     * @param salary Annual salary of the staff member
     * @param position Position level (senior, middle, junior)
     * @param jobTitle Job title of the staff member
     * @param company School name
     */
    public AdministrativeStaff(String firstName, String lastName, String gender, String email,
                              double salary, String position, String jobTitle, String company) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company);
        this.officeLocation = "";
        this.responsibilities = "";
    }

    /**
     * Returns the office location of this administrative staff member
     *
     * @return The office location as a String
     */
    public String getOfficeLocation() {
        return officeLocation;
    }

    /**
     * Sets the office location for this administrative staff member
     *
     * @param officeLocation The office location to set
     */
    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    /**
     * Returns the responsibilities of this administrative staff member
     *
     * @return The responsibilities as a String
     */
    public String getResponsibilities() {
        return responsibilities;
    }

    /**
     * Sets the responsibilities for this administrative staff member
     *
     * @param responsibilities The responsibilities to set
     */
    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    /**
     * Returns a string representation of the AdministrativeStaff object
     *
     * @return String containing administrative staff details
     */
    @Override
    public String toString() {
        return "AdministrativeStaff{" +
                "ID='" + employeeId + '\'' +
                ", Name='" + getFullName() + '\'' +
                ", Office='" + officeLocation + '\'' +
                ", Department=" + (department != null ? department.getDepartmentName() : "None") +
                '}';
    }
}
