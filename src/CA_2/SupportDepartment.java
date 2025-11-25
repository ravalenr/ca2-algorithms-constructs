package CA_2;

import java.util.ArrayList;

/**
 * SupportDepartment class represents a support services department in the school.
 * This class extends Department and includes specific attributes for support operations.
 *
 * Design Decision: SupportDepartment handles facilities, equipment, maintenance,
 * IT support, and other support services essential for school operations.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class SupportDepartment extends Department {

    // Additional attributes specific to SupportDepartment
    private ArrayList<String> facilities;
    private ArrayList<String> equipmentList;

    /**
     * Default constructor
     * Creates a SupportDepartment with default values
     */
    public SupportDepartment() {
        super();
        this.facilities = new ArrayList<>();
        this.equipmentList = new ArrayList<>();
    }

    /**
     * Parameterized constructor
     * Creates a SupportDepartment with specified values
     *
     * @param departmentName Name of the support department
     * @param departmentType Type of department
     */
    public SupportDepartment(String departmentName, DepartmentType departmentType) {
        super(departmentName, departmentType);
        this.facilities = new ArrayList<>();
        this.equipmentList = new ArrayList<>();
    }

    /**
     * Parameterized constructor with department head
     *
     * @param departmentName Name of the support department
     * @param departmentType Type of department
     * @param departmentHead Manager who heads this department
     */
    public SupportDepartment(String departmentName, DepartmentType departmentType, Manager departmentHead) {
        super(departmentName, departmentType);
        this.facilities = new ArrayList<>();
        this.equipmentList = new ArrayList<>();
        setDepartmentHead(departmentHead);
    }

    /**
     * Returns the list of facilities
     * @return ArrayList of facilities
     */
    public ArrayList<String> getFacilities() {
        return new ArrayList<>(facilities);
    }

    /**
     * Adds a facility to the department
     * @param facility The facility to add
     * @return true if successfully added, false otherwise
     */
    public boolean addFacility(String facility) {
        if (facility == null || facility.trim().isEmpty()) {
            return false;
        }

        if (facilities.contains(facility)) {
            return false;
        }

        facilities.add(facility);
        return true;
    }

    /**
     * Removes a facility from the department
     * @param facility The facility to remove
     * @return true if successfully removed, false otherwise
     */
    public boolean removeFacility(String facility) {
        return facilities.remove(facility);
    }

    /**
     * Returns the equipment list
     * @return ArrayList of equipment
     */
    public ArrayList<String> getEquipmentList() {
        return new ArrayList<>(equipmentList);
    }

    /**
     * Adds equipment to the list
     * @param equipment The equipment to add
     * @return true if successfully added, false otherwise
     */
    public boolean addEquipment(String equipment) {
        if (equipment == null || equipment.trim().isEmpty()) {
            return false;
        }

        if (equipmentList.contains(equipment)) {
            return false;
        }

        equipmentList.add(equipment);
        return true;
    }

    /**
     * Removes equipment from the list
     * @param equipment The equipment to remove
     * @return true if successfully removed, false otherwise
     */
    public boolean removeEquipment(String equipment) {
        return equipmentList.remove(equipment);
    }

    /**
     * Maintains facilities under this department's responsibility
     * This method simulates facility maintenance functionality
     *
     * @param facilityName Name of the facility to maintain
     * @param maintenanceType Type of maintenance required
     */
    public void maintainFacilities(String facilityName, String maintenanceType) {
        if (facilityName == null || facilityName.trim().isEmpty()) {
            System.out.println("Facility name required.");
            return;
        }

        System.out.println("Facility Maintenance - " + departmentName);
        System.out.println("Facility: " + facilityName);
        System.out.println("Maintenance Type: " + (maintenanceType != null ? maintenanceType : "General"));
        System.out.println("Department Head: " + (departmentHead != null ? departmentHead.getFullName() : "Not assigned"));
        System.out.println("Status: Maintenance scheduled");
    }

    /**
     * Requests resources for the department
     * This method simulates resource request functionality
     *
     * @param resourceType Type of resource requested
     * @param quantity Quantity of resource needed
     */
    public void requestResources(String resourceType, int quantity) {
        if (resourceType == null || resourceType.trim().isEmpty()) {
            System.out.println("Resource type required.");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }

        System.out.println("Resource Request - " + departmentName);
        System.out.println("Resource Type: " + resourceType);
        System.out.println("Quantity: " + quantity);
        System.out.println("Requested by: " + (departmentHead != null ? departmentHead.getFullName() : "Department"));
        System.out.println("Status: Request submitted for approval");
    }

    /**
     * Displays all facilities and equipment managed by this department
     */
    public void displayInventory() {
        System.out.println("Support Department Inventory - " + departmentName);
        System.out.println("Facilities (" + facilities.size() + "):");

        if (facilities.isEmpty()) {
            System.out.println("  None");
        } else {
            for (int i = 0; i < facilities.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + facilities.get(i));
            }
        }

        System.out.println("Equipment (" + equipmentList.size() + "):");

        if (equipmentList.isEmpty()) {
            System.out.println("  None");
        } else {
            for (int i = 0; i < equipmentList.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + equipmentList.get(i));
            }
        }
    }

    /**
     * Implementation of abstract method from Department class
     * Returns specific information about the SupportDepartment
     *
     * @return String containing support department-specific information
     */
    @Override
    public String getDepartmentInfo() {
        return "Support Department Information:\n" +
               "  Facilities: " + facilities.size() + "\n" +
               "  Equipment Items: " + equipmentList.size();
    }

    /**
     * Returns a string representation of the SupportDepartment
     *
     * @return String containing support department details
     */
    @Override
    public String toString() {
        return "SupportDepartment{" +
                "Name='" + departmentName + '\'' +
                ", ID='" + departmentId + '\'' +
                ", Type='" + getDepartmentTypeString() + '\'' +
                ", Facilities=" + facilities.size() +
                ", Equipment=" + equipmentList.size() +
                ", StaffCount=" + staffCount +
                '}';
    }
}
