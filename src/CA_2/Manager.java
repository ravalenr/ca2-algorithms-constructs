package CA_2;

import java.util.ArrayList;

/**
 * Manager class is the parent class for all manager types.
 * Abstract class that extends Employee.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public abstract class Manager extends Employee {

    protected String managerId;
    protected ArrayList<Employee> managedEmployees;
    protected int employeeCount;
    protected ManagerType managerType;

    /**
     * Default constructor
     */
    public Manager() {
        super();
        this.managerId = "";
        this.managedEmployees = new ArrayList<>();
        this.employeeCount = 0;
        this.managerType = null;
    }

    /**
     * Constructor with parameters
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
     */
    public boolean addEmployee(Employee employee) {
        if (employee == null) {
            return false;
        }

        if (managedEmployees.contains(employee)) {
            return false;
        }

        managedEmployees.add(employee);
        employee.setManager(this);
        employeeCount = managedEmployees.size();

        return true;
    }

    /**
     * Removes an employee from this manager's team
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
     * Returns manager-specific information (implemented by subclasses)
     */
    public abstract String getManagerInfo();

    // Getters and Setters
    public String getManagerId() { return managerId; }
    public ArrayList<Employee> getManagedEmployees() { return managedEmployees; }
    public int getEmployeeCount() { return employeeCount; }
    public ManagerType getManagerType() { return managerType; }
    public String getManagerTypeString() { return managerType != null ? managerType.getDisplayName() : "Unknown"; }

    public void setManagerId(String managerId) { this.managerId = managerId; }
    public void setManagerType(ManagerType managerType) { this.managerType = managerType; }

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
