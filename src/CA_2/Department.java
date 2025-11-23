package CA_2;

import java.util.ArrayList;

/**
 * Department class serves as the parent class for all department types in the school management system.
 * This abstract class manages department-specific information and staff members.
 *
 * Design Decision: Making this an abstract class ensures that only specific department types
 * can be instantiated, enforcing proper organizational structure.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public abstract class Department {

    // Instance variables
    protected String departmentId;
    protected String departmentName;
    protected DepartmentType departmentType;
    protected Manager departmentHead;
    protected ArrayList<Employee> staffMembers;
    protected int staffCount;

    /**
     * Default constructor
     * Initializes a Department with default values and empty staff list
     */
    public Department() {
        this.departmentId = "";
        this.departmentName = "";
        this.departmentType = null;
        this.departmentHead = null;
        this.staffMembers = new ArrayList<>();
        this.staffCount = 0;
    }

    /**
     * Parameterized constructor
     * Creates a Department object with specified values
     *
     * @param departmentName Name of the department
     * @param departmentType Type of department
     */
    public Department(String departmentName, DepartmentType departmentType) {
        this.departmentId = generateDepartmentId(departmentName);
        this.departmentName = departmentName;
        this.departmentType = departmentType;
        this.departmentHead = null;
        this.staffMembers = new ArrayList<>();
        this.staffCount = 0;
    }

    /**
     * Generates a unique department ID based on department name
     *
     * @param deptName Department name
     * @return Generated department ID
     */
    private String generateDepartmentId(String deptName) {
        if (deptName == null || deptName.isEmpty()) {
            return "DEPT" + (int)(Math.random() * 1000);
        }

        String prefix = deptName.length() >= 3 ?
                       deptName.substring(0, 3).toUpperCase() :
                       deptName.toUpperCase();

        return prefix + (int)(Math.random() * 1000);
    }

    /**
     * Adds a staff member to this department
     *
     * @param employee The employee to be added to the department
     * @return true if employee was successfully added, false otherwise
     */
    public boolean addStaff(Employee employee) {
        if (employee == null) {
            return false;
        }

        if (staffMembers.contains(employee)) {
            return false;
        }

        staffMembers.add(employee);
        employee.setDepartment(this);
        staffCount = staffMembers.size();

        return true;
    }

    /**
     * Returns the list of all staff members in this department
     *
     * @return ArrayList of Employee objects
     */
    public ArrayList<Employee> getStaffList() {
        return new ArrayList<>(staffMembers);
    }

    /**
     * Returns information specific to this department type
     * This method is abstract and must be implemented by subclasses
     *
     * @return String containing department-specific information
     */
    public abstract String getDepartmentInfo();

    // Getter methods

    /**
     * Returns the department's unique identifier
     * @return departmentId as String
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * Returns the department name
     * @return departmentName as String
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Returns the department type
     * @return DepartmentType enum value
     */
    public DepartmentType getDepartmentType() {
        return departmentType;
    }

    /**
     * Returns the department type as a string
     * @return String representation of department type
     */
    public String getDepartmentTypeString() {
        return departmentType != null ? departmentType.getDisplayName() : "Unknown";
    }

    /**
     * Returns the department head (manager)
     * @return Manager object or null if no head is assigned
     */
    public Manager getDepartmentHead() {
        return departmentHead;
    }

    /**
     * Returns the count of staff members in this department
     * @return staffCount as int
     */
    public int getStaffCount() {
        return staffCount;
    }

    // Setter methods

    /**
     * Sets the department name
     * @param departmentName The department name to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * Sets the department head (manager)
     *
     * @param departmentHead The Manager to set as department head
     */
    public void setDepartmentHead(Manager departmentHead) {
        this.departmentHead = departmentHead;
        if (departmentHead != null) {
            departmentHead.setDepartment(this);
        }
    }

    /**
     * Returns a string representation of the Department object
     *
     * @return String containing department details
     */
    @Override
    public String toString() {
        return "Department{" +
                "ID='" + departmentId + '\'' +
                ", Name='" + departmentName + '\'' +
                ", Type='" + getDepartmentTypeString() + '\'' +
                ", Head=" + (departmentHead != null ? departmentHead.getFullName() : "None") +
                ", StaffCount=" + staffCount +
                '}';
    }

    /**
     * Displays the department's complete information to the console
     */
    public void displayInfo() {
        System.out.println("========================================");
        System.out.println("Department Information:");
        System.out.println("Department ID: " + departmentId);
        System.out.println("Department Name: " + departmentName);
        System.out.println("Department Type: " + getDepartmentTypeString());
        System.out.println("Department Head: " + (departmentHead != null ? departmentHead.getFullName() : "None"));
        System.out.println("Staff Count: " + staffCount);
        System.out.println(getDepartmentInfo());
        System.out.println("========================================");
    }
}
