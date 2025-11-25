package CA_2;

/**
 * SupportStaff class represents support services personnel in the school management system.
 * This is a concrete implementation of the Employee class.
 *
 * Support staff include maintenance workers, custodians, cafeteria workers, security personnel,
 * and other essential support roles that keep the school running smoothly.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class SupportStaff extends Employee {

    private String serviceArea;
    private String shift;

    /**
     * Default constructor
     * Creates a SupportStaff object with default values
     */
    public SupportStaff() {
        super();
        this.serviceArea = "";
        this.shift = "";
    }

    /**
     * Parameterized constructor
     * Creates a SupportStaff object with specified values
     *
     * @param firstName First name of the support staff member
     * @param lastName Last name of the support staff member
     * @param gender Gender of the support staff member
     * @param email Email address of the support staff member
     * @param salary Annual salary of the support staff member
     * @param position Position level (senior, middle, junior)
     * @param jobTitle Job title of the support staff member
     * @param company School name
     */
    public SupportStaff(String firstName, String lastName, String gender, String email,
                       double salary, String position, String jobTitle, String company) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company);
        this.serviceArea = "";
        this.shift = "Day";
    }

    /**
     * Returns the service area assigned to this support staff member
     *
     * @return The service area as a String
     */
    public String getServiceArea() {
        return serviceArea;
    }

    /**
     * Sets the service area for this support staff member
     *
     * @param serviceArea The service area to set
     */
    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    /**
     * Returns the work shift for this support staff member
     *
     * @return The shift as a String
     */
    public String getShift() {
        return shift;
    }

    /**
     * Sets the work shift for this support staff member
     *
     * @param shift The shift to set (e.g., Day, Evening, Night)
     */
    public void setShift(String shift) {
        this.shift = shift;
    }

    /**
     * Returns a string representation of the SupportStaff object
     *
     * @return String containing support staff details
     */
    @Override
    public String toString() {
        return "SupportStaff{" +
                "ID='" + employeeId + '\'' +
                ", Name='" + getFullName() + '\'' +
                ", ServiceArea='" + serviceArea + '\'' +
                ", Shift='" + shift + '\'' +
                ", Department=" + (department != null ? department.getDepartmentName() : "None") +
                '}';
    }
}
