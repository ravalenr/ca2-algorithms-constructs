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

    public String getDepartmentName() { return departmentName; }
    public String getDepartmentTypeString() { return departmentType != null ? departmentType.getDisplayName() : "Unknown"; }

    public void setDepartmentHead(Manager departmentHead) { this.departmentHead = departmentHead; }
}
