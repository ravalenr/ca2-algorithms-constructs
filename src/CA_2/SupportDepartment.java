package CA_2;

/**
 * SupportDepartment class represents a support services department in the school management system.
 * This is a concrete implementation of the Department abstract class.
 *
 * Support departments provide essential services such as facilities management, maintenance,
 * security, cafeteria services, transportation, and other operational support functions.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class SupportDepartment extends Department {

    private String serviceType;
    private String operatingHours;

    /**
     * Default constructor
     * Creates a SupportDepartment object with default values
     */
    public SupportDepartment() {
        super();
        this.serviceType = "";
        this.operatingHours = "";
    }

    /**
     * Parameterized constructor
     * Creates a SupportDepartment object with specified values
     *
     * @param departmentName Name of the support department
     * @param departmentType Type of department (from DepartmentType enum)
     */
    public SupportDepartment(String departmentName, DepartmentType departmentType) {
        super(departmentName, departmentType);
        this.serviceType = "";
        this.operatingHours = "24/7";
    }

    /**
     * Returns the type of service provided by this support department
     *
     * @return The service type as a String
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets the type of service provided by this support department
     *
     * @param serviceType The service type to set
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * Returns the operating hours of this support department
     *
     * @return The operating hours as a String
     */
    public String getOperatingHours() {
        return operatingHours;
    }

    /**
     * Sets the operating hours for this support department
     *
     * @param operatingHours The operating hours to set
     */
    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    /**
     * Returns information specific to this support department
     * This method provides details about the department's support services
     *
     * @return String containing support department-specific information
     */
    @Override
    public String getDepartmentInfo() {
        return "Support Department - Service: " +
               (serviceType.isEmpty() ? "General Support" : serviceType) +
               ", Hours: " + operatingHours +
               ", Staff: " + staffCount;
    }

    /**
     * Returns a string representation of the SupportDepartment object
     *
     * @return String containing support department details
     */
    @Override
    public String toString() {
        return "SupportDepartment{" +
                "Name='" + departmentName + '\'' +
                ", Type='" + getDepartmentTypeString() + '\'' +
                ", Service='" + serviceType + '\'' +
                ", Staff=" + staffCount +
                '}';
    }
}
