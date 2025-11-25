package CA_2;

/**
 * DepartmentHead class represents a manager who leads a specific academic or administrative department.
 * This class extends Manager and includes specific attributes for department leadership.
 *
 * Design Decision: DepartmentHead manages curriculum, staff meetings, and department-specific operations,
 * focusing on a particular subject area or administrative function.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class DepartmentHead extends Manager {

    // Additional attributes specific to DepartmentHead
    private String subjectArea;

    /**
     * Default constructor
     * Creates a DepartmentHead with default values
     */
    public DepartmentHead() {
        super();
        this.subjectArea = "";
        this.managerType = ManagerType.DEPARTMENT_HEAD;
    }

    /**
     * Parameterized constructor
     * Creates a DepartmentHead with specified values
     *
     * @param firstName First name of the department head
     * @param lastName Last name of the department head
     * @param gender Gender of the department head
     * @param email Email address of the department head
     * @param salary Annual salary of the department head
     * @param position Position level
     * @param jobTitle Job title
     * @param company School/organization name
     */
    public DepartmentHead(String firstName, String lastName, String gender, String email,
                         double salary, String position, String jobTitle, String company) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company, ManagerType.DEPARTMENT_HEAD);
        this.subjectArea = "General";
    }

    /**
     * Parameterized constructor with subject area
     *
     * @param firstName First name of the department head
     * @param lastName Last name of the department head
     * @param gender Gender of the department head
     * @param email Email address of the department head
     * @param salary Annual salary of the department head
     * @param position Position level
     * @param jobTitle Job title
     * @param company School/organization name
     * @param subjectArea Subject area or specialization
     */
    public DepartmentHead(String firstName, String lastName, String gender, String email,
                         double salary, String position, String jobTitle, String company,
                         String subjectArea) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company, ManagerType.DEPARTMENT_HEAD);
        this.subjectArea = subjectArea;
    }

    /**
     * Returns the subject area
     * @return subjectArea as String
     */
    public String getSubjectArea() {
        return subjectArea;
    }

    /**
     * Sets the subject area
     * @param subjectArea The subject area to set
     */
    public void setSubjectArea(String subjectArea) {
        this.subjectArea = subjectArea;
    }

    /**
     * Manages curriculum for the department
     * This method simulates curriculum management functionality
     *
     * @param curriculumItem Description of curriculum item to manage
     */
    public void manageCurriculum(String curriculumItem) {
        if (curriculumItem == null || curriculumItem.trim().isEmpty()) {
            System.out.println("Curriculum item required.");
            return;
        }

        System.out.println("Curriculum Management by Department Head " + getFullName());
        System.out.println("Subject Area: " + subjectArea);
        System.out.println("Curriculum Item: " + curriculumItem);
        System.out.println("Status: Under review and development");
    }

    /**
     * Conducts staff meetings for the department
     * This method simulates staff meeting organization
     *
     * @param meetingTopic Topic of the staff meeting
     * @param meetingDate Date of the meeting
     */
    public void conductStaffMeeting(String meetingTopic, String meetingDate) {
        if (meetingTopic == null || meetingTopic.trim().isEmpty()) {
            System.out.println("Meeting topic required.");
            return;
        }

        System.out.println("Staff Meeting Scheduled by Department Head " + getFullName());
        System.out.println("Department: " + (department != null ? department.getDepartmentName() : "Not assigned"));
        System.out.println("Subject Area: " + subjectArea);
        System.out.println("Topic: " + meetingTopic);
        System.out.println("Date: " + (meetingDate != null ? meetingDate : "To be scheduled"));
        System.out.println("Attendees: " + employeeCount + " staff members");
    }

    /**
     * Evaluates department performance
     * This method provides a simple performance evaluation
     */
    public void evaluateDepartmentPerformance() {
        System.out.println("Department Performance Evaluation");
        System.out.println("Department Head: " + getFullName());
        System.out.println("Subject Area: " + subjectArea);
        System.out.println("Staff Members: " + employeeCount);
        System.out.println("Department: " + (department != null ? department.getDepartmentName() : "Not assigned"));
        System.out.println("Evaluation Status: In progress");
    }

    /**
     * Implementation of abstract method from Manager class
     * Returns specific information about the DepartmentHead
     *
     * @return String containing department head-specific information
     */
    @Override
    public String getManagerInfo() {
        return "Department Head Information:\n" +
               "  Subject Area: " + subjectArea +
               "\n  Department: " + (department != null ? department.getDepartmentName() : "Not assigned");
    }

    /**
     * Returns a string representation of the DepartmentHead
     *
     * @return String containing department head details
     */
    @Override
    public String toString() {
        return "DepartmentHead{" +
                "Name='" + getFullName() + '\'' +
                ", ManagerID='" + managerId + '\'' +
                ", Email='" + email + '\'' +
                ", SubjectArea='" + subjectArea + '\'' +
                ", Department='" + (department != null ? department.getDepartmentName() : "None") + '\'' +
                ", EmployeesManaged=" + employeeCount +
                '}';
    }
}
