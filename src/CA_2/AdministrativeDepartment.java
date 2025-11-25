package CA_2;

/**
 * AdministrativeDepartment class represents an administrative department in the school.
 * This class extends Department and includes specific attributes for administrative operations.
 *
 * Design Decision: AdministrativeDepartment handles non-academic operations such as
 * student records, enrollment, facilities management, and general administration.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class AdministrativeDepartment extends Department {

    // Additional attributes specific to AdministrativeDepartment
    private String serviceType;
    private String operatingHours;

    /**
     * Default constructor
     * Creates an AdministrativeDepartment with default values
     */
    public AdministrativeDepartment() {
        super();
        this.serviceType = "";
        this.operatingHours = "";
    }

    /**
     * Parameterized constructor
     * Creates an AdministrativeDepartment with specified values
     *
     * @param departmentName Name of the administrative department
     * @param departmentType Type of department
     */
    public AdministrativeDepartment(String departmentName, DepartmentType departmentType) {
        super(departmentName, departmentType);
        this.serviceType = "General Administration";
        this.operatingHours = "8:00 AM - 5:00 PM";
    }

    /**
     * Parameterized constructor with service type and hours
     *
     * @param departmentName Name of the administrative department
     * @param departmentType Type of department
     * @param serviceType Type of service provided
     * @param operatingHours Operating hours of the department
     */
    public AdministrativeDepartment(String departmentName, DepartmentType departmentType,
                                   String serviceType, String operatingHours) {
        super(departmentName, departmentType);
        this.serviceType = serviceType;
        this.operatingHours = operatingHours;
    }

    /**
     * Returns the service type
     * @return serviceType as String
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets the service type
     * @param serviceType The service type to set
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * Returns the operating hours
     * @return operatingHours as String
     */
    public String getOperatingHours() {
        return operatingHours;
    }

    /**
     * Sets the operating hours
     * @param operatingHours The operating hours to set
     */
    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    /**
     * Processes various administrative documents
     * This method simulates document processing functionality
     *
     * @param documentType Type of document to process
     * @param documentId Document identifier
     */
    public void processDocuments(String documentType, String documentId) {
        if (documentType == null || documentType.trim().isEmpty()) {
            System.out.println("Document type required.");
            return;
        }

        System.out.println("Document Processing - " + departmentName);
        System.out.println("Document Type: " + documentType);
        System.out.println("Document ID: " + (documentId != null ? documentId : "Not provided"));
        System.out.println("Service Type: " + serviceType);
        System.out.println("Status: Processing");
    }

    /**
     * Manages administrative records
     * This method simulates record management functionality
     *
     * @param recordType Type of record to manage
     */
    public void manageRecords(String recordType) {
        if (recordType == null || recordType.trim().isEmpty()) {
            System.out.println("Record type required.");
            return;
        }

        System.out.println("Record Management - " + departmentName);
        System.out.println("Record Type: " + recordType);
        System.out.println("Department Staff: " + staffCount);
        System.out.println("Operating Hours: " + operatingHours);
        System.out.println("Status: Records being updated");
    }

    /**
     * Provides information about department services
     */
    public void provideServiceInfo() {
        System.out.println("Administrative Department Services");
        System.out.println("Department: " + departmentName);
        System.out.println("Service Type: " + serviceType);
        System.out.println("Operating Hours: " + operatingHours);
        System.out.println("Staff Available: " + staffCount);
        System.out.println("Head: " + (departmentHead != null ? departmentHead.getFullName() : "Not assigned"));
    }

    /**
     * Implementation of abstract method from Department class
     * Returns specific information about the AdministrativeDepartment
     *
     * @return String containing administrative department-specific information
     */
    @Override
    public String getDepartmentInfo() {
        return "Administrative Department Information:\n" +
               "  Service Type: " + serviceType + "\n" +
               "  Operating Hours: " + operatingHours;
    }

    /**
     * Returns a string representation of the AdministrativeDepartment
     *
     * @return String containing administrative department details
     */
    @Override
    public String toString() {
        return "AdministrativeDepartment{" +
                "Name='" + departmentName + '\'' +
                ", ID='" + departmentId + '\'' +
                ", Type='" + getDepartmentTypeString() + '\'' +
                ", ServiceType='" + serviceType + '\'' +
                ", OperatingHours='" + operatingHours + '\'' +
                ", StaffCount=" + staffCount +
                '}';
    }
}
