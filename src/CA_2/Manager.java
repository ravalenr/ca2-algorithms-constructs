package CA_2;

import java.util.ArrayList;

/**
 * Manager class serves as the parent class for all manager types in the school management system.
 * This abstract class extends Employee and adds management-specific functionality.
 *
 * Design Decision: Making this an abstract class ensures that only specific manager types
 * (Principal, VicePrincipal, DepartmentHead) can be instantiated, enforcing proper organizational structure.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public abstract class Manager extends Employee {

    // Additional attributes specific to managers
    protected String managerId;
    protected ArrayList<Employee> managedEmployees;
    protected int employeeCount;
    protected ManagerType managerType;

    /**
     * Default constructor
     * Initializes a Manager with default values and empty employee list
     */
    public Manager() {
        super();
        this.managerId = "";
        this.managedEmployees = new ArrayList<>();
        this.employeeCount = 0;
        this.managerType = null;
    }

    /**
     * Parameterized constructor
     * Creates a Manager object with specified values
     *
     * @param firstName First name of the manager
     * @param lastName Last name of the manager
     * @param gender Gender of the manager
     * @param email Email address of the manager
     * @param salary Annual salary of the manager
     * @param position Position level
     * @param jobTitle Job title of the manager
     * @param company Company name
     * @param managerType Type of manager (Principal, VicePrincipal, DepartmentHead)
     */
    public Manager(String firstName, String lastName, String gender, String email,
                  double salary, String position, String jobTitle, String company,
                  ManagerType managerType) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company);
        this.managerId = "MGR" + this.employeeId;
        this.managedEmployees = new ArrayList<>();
        this.employeeCount = 0;
        this.managerType = managerType;
    }

    /**
     * Adds an employee to this manager's team
     * This method maintains the bidirectional relationship between manager and employee
     *
     * @param employee The employee to be added to the managed team
     * @return true if employee was successfully added, false otherwise
     */
    public boolean addEmployee(Employee employee) {
        if (employee == null) {
            return false;
        }

        // Check if employee is already managed by this manager
        if (managedEmployees.contains(employee)) {
            return false;
        }

        // Add employee to managed list
        managedEmployees.add(employee);
        employee.setManager(this);
        employeeCount = managedEmployees.size();

        return true;
    }

    /**
     * Removes an employee from this manager's team
     * This method maintains the bidirectional relationship between manager and employee
     *
     * @param employee The employee to be removed from the managed team
     * @return true if employee was successfully removed, false otherwise
     */
    public boolean removeEmployee(Employee employee) {
        if (employee == null) {
            return false;
        }

        boolean removed = managedEmployees.remove(employee);

        if (removed) {
            employee.setManager(null);
            employeeCount = managedEmployees.size();
        }

        return removed;
    }

    /**
     * Returns information about this manager
     * This method is abstract and must be implemented by subclasses
     *
     * @return String containing manager-specific information
     */
    public abstract String getManagerInfo();

    // Getter methods

    /**
     * Returns the manager's unique identifier
     * @return managerId as String
     */
    public String getManagerId() {
        return managerId;
    }

    /**
     * Returns the list of employees managed by this manager
     * @return ArrayList of Employee objects
     */
    public ArrayList<Employee> getManagedEmployees() {
        return managedEmployees;
    }

    /**
     * Returns the count of employees managed by this manager
     * @return employeeCount as int
     */
    public int getEmployeeCount() {
        return employeeCount;
    }

    /**
     * Returns the type of this manager
     * @return ManagerType enum value
     */
    public ManagerType getManagerType() {
        return managerType;
    }

    /**
     * Returns the manager type as a string
     * @return String representation of manager type
     */
    public String getManagerTypeString() {
        return managerType != null ? managerType.getDisplayName() : "Unknown";
    }

    // Setter methods

    /**
     * Sets the manager's unique identifier
     * @param managerId The manager ID to set
     */
    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    /**
     * Sets the manager type
     * @param managerType The ManagerType enum value to set
     */
    public void setManagerType(ManagerType managerType) {
        this.managerType = managerType;
    }

    /**
     * Returns a string representation of the Manager object
     *
     * @return String containing manager details
     */
    @Override
    public String toString() {
        return "Manager{" +
                "ManagerID='" + managerId + '\'' +
                ", Name='" + getFullName() + '\'' +
                ", Type='" + getManagerTypeString() + '\'' +
                ", EmployeeCount=" + employeeCount +
                ", Department=" + (department != null ? department.getDepartmentName() : "None") +
                '}';
    }

    /**
     * Displays the manager's complete information to the console
     */
    @Override
    public void displayInfo() {
        System.out.println("========================================");
        System.out.println("Manager Information:");
        System.out.println("Manager ID: " + managerId);
        System.out.println("Name: " + getFullName());
        System.out.println("Manager Type: " + getManagerTypeString());
        System.out.println("Email: " + email);
        System.out.println("Salary: " + salary);
        System.out.println("Job Title: " + jobTitle);
        System.out.println("Employees Managed: " + employeeCount);
        System.out.println("Department: " + (department != null ? department.getDepartmentName() : "None"));
        System.out.println(getManagerInfo());
        System.out.println("========================================");
    }
}
