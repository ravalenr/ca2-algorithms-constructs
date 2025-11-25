package CA_2;

/**
 * AdministrativeDepartment class represents an administrative department in the school management system.
 * This is a concrete implementation of the Department abstract class.
 *
 * Administrative departments handle non-teaching operations such as HR, Finance, IT,
 * Student Records, Admissions, and other administrative functions essential for school operations.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class AdministrativeDepartment extends Department {

    private String primaryFunction;
    private String officeLocation;

    /**
     * Default constructor
     * Creates an AdministrativeDepartment object with default values
     */
    public AdministrativeDepartment() {
        super();
        this.primaryFunction = "";
        this.officeLocation = "";
    }

    /**
     * Parameterized constructor
     * Creates an AdministrativeDepartment object with specified values
     *
     * @param departmentName Name of the administrative department
     * @param departmentType Type of department (from DepartmentType enum)
     */
    public AdministrativeDepartment(String departmentName, DepartmentType departmentType) {
        super(departmentName, departmentType);
        this.primaryFunction = "";
        this.officeLocation = "Main Office";
    }

    /**
     * Returns the primary function of this administrative department
     *
     * @return The primary function as a String
     */
    public String getPrimaryFunction() {
        return primaryFunction;
    }

    /**
     * Sets the primary function for this administrative department
     *
     * @param primaryFunction The primary function to set
     */
    public void setPrimaryFunction(String primaryFunction) {
        this.primaryFunction = primaryFunction;
    }

    /**
     * Returns the office location of this administrative department
     *
     * @return The office location as a String
     */
    public String getOfficeLocation() {
        return officeLocation;
    }

    /**
     * Sets the office location for this administrative department
     *
     * @param officeLocation The office location to set
     */
    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    /**
     * Returns information specific to this administrative department
     * This method provides details about the department's administrative function
     *
     * @return String containing administrative department-specific information
     */
    @Override
    public String getDepartmentInfo() {
        return "Administrative Department - Function: " +
               (primaryFunction.isEmpty() ? "General Administration" : primaryFunction) +
               ", Location: " + officeLocation +
               ", Staff: " + staffCount;
    }

    /**
     * Returns a string representation of the AdministrativeDepartment object
     *
     * @return String containing administrative department details
     */
    @Override
    public String toString() {
        return "AdministrativeDepartment{" +
                "Name='" + departmentName + '\'' +
                ", Type='" + getDepartmentTypeString() + '\'' +
                ", Function='" + primaryFunction + '\'' +
                ", Staff=" + staffCount +
                '}';
    }
}
