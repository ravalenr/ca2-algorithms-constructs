package CA_2;

import java.util.ArrayList;

/**
 * Department class is the parent class for all department types.
 * Abstract class for managing department information and staff.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public abstract class Department {

    protected String departmentId;
    protected String departmentName;
    protected DepartmentType departmentType;
    protected Manager departmentHead;
    protected ArrayList<Employee> staffMembers;
    protected int staffCount;

    /**
     * Default constructor
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
     * Constructor with parameters
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
     * Generates a unique department ID
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
     * Returns list of all staff members
     */
    public ArrayList<Employee> getStaffList() {
        return new ArrayList<>(staffMembers);
    }

    /**
     * Returns department-specific information (implemented by subclasses)
     */
    public abstract String getDepartmentInfo();

    // Getters and Setters
    public String getDepartmentId() { return departmentId; }
    public String getDepartmentName() { return departmentName; }
    public DepartmentType getDepartmentType() { return departmentType; }
    public String getDepartmentTypeString() { return departmentType != null ? departmentType.getDisplayName() : "Unknown"; }
    public Manager getDepartmentHead() { return departmentHead; }
    public int getStaffCount() { return staffCount; }

    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
    public void setDepartmentType(DepartmentType departmentType) { this.departmentType = departmentType; }
    public void setDepartmentHead(Manager departmentHead) { this.departmentHead = departmentHead; }

    @Override
    public String toString() {
        return "Department{" +
                "ID='" + departmentId + '\'' +
                ", Name='" + departmentName + '\'' +
                ", Type='" + getDepartmentTypeString() + '\'' +
                ", Staff=" + staffCount +
                ", Head=" + (departmentHead != null ? departmentHead.getFullName() : "None") +
                '}';
    }
}
