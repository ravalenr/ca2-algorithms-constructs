package CA_2;

import java.util.ArrayList;

/**
 * AcademicDepartment class represents an academic department in the school.
 * This class extends Department and includes specific attributes for academic operations.
 *
 * Design Decision: AcademicDepartment handles subject-specific teaching,
 * curriculum development, and student performance tracking.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class AcademicDepartment extends Department {

    // Additional attributes specific to AcademicDepartment
    private ArrayList<String> subjectAreas;
    private int studentCount;

    /**
     * Default constructor
     * Creates an AcademicDepartment with default values
     */
    public AcademicDepartment() {
        super();
        this.subjectAreas = new ArrayList<>();
        this.studentCount = 0;
    }

    /**
     * Parameterized constructor
     * Creates an AcademicDepartment with specified values
     *
     * @param departmentName Name of the academic department
     * @param departmentType Type of department
     */
    public AcademicDepartment(String departmentName, DepartmentType departmentType) {
        super(departmentName, departmentType);
        this.subjectAreas = new ArrayList<>();
        this.studentCount = 0;
    }

    /**
     * Parameterized constructor with department head
     *
     * @param departmentName Name of the academic department
     * @param departmentType Type of department
     * @param departmentHead Manager who heads this department
     */
    public AcademicDepartment(String departmentName, DepartmentType departmentType, Manager departmentHead) {
        super(departmentName, departmentType);
        this.subjectAreas = new ArrayList<>();
        this.studentCount = 0;
        setDepartmentHead(departmentHead);
    }

    /**
     * Returns the list of subject areas
     * @return ArrayList of subject areas
     */
    public ArrayList<String> getSubjectAreas() {
        return new ArrayList<>(subjectAreas);
    }

    /**
     * Adds a subject area to the department
     * @param subject The subject to add
     * @return true if successfully added, false otherwise
     */
    public boolean addSubjectArea(String subject) {
        if (subject == null || subject.trim().isEmpty()) {
            return false;
        }

        if (subjectAreas.contains(subject)) {
            return false;
        }

        subjectAreas.add(subject);
        return true;
    }

    /**
     * Removes a subject area from the department
     * @param subject The subject to remove
     * @return true if successfully removed, false otherwise
     */
    public boolean removeSubjectArea(String subject) {
        return subjectAreas.remove(subject);
    }

    /**
     * Returns the student count
     * @return studentCount as int
     */
    public int getStudentCount() {
        return studentCount;
    }

    /**
     * Sets the student count
     * @param studentCount The number of students to set
     */
    public void setStudentCount(int studentCount) {
        if (studentCount >= 0) {
            this.studentCount = studentCount;
        }
    }

    /**
     * Schedules lessons for the department
     * This method simulates lesson scheduling functionality
     *
     * @param subject Subject for which to schedule lessons
     * @param schedule Schedule description
     */
    public void scheduleLessons(String subject, String schedule) {
        if (subject == null || subject.trim().isEmpty()) {
            System.out.println("Subject required for lesson scheduling.");
            return;
        }

        System.out.println("Lesson Scheduling - " + departmentName);
        System.out.println("Subject: " + subject);
        System.out.println("Schedule: " + (schedule != null ? schedule : "To be determined"));
        System.out.println("Department Head: " + (departmentHead != null ? departmentHead.getFullName() : "Not assigned"));
    }

    /**
     * Tracks student performance in the department
     * This method simulates performance tracking functionality
     */
    public void trackPerformance() {
        System.out.println("Performance Tracking - " + departmentName);
        System.out.println("Total Students: " + studentCount);
        System.out.println("Subject Areas: " + subjectAreas.size());
        System.out.println("Teaching Staff: " + staffCount);
        System.out.println("Subjects Offered: " + String.join(", ", subjectAreas));
    }

    /**
     * Implementation of abstract method from Department class
     * Returns specific information about the AcademicDepartment
     *
     * @return String containing academic department-specific information
     */
    @Override
    public String getDepartmentInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Academic Department Information:\n");
        info.append("  Subject Areas (").append(subjectAreas.size()).append("): ");

        if (subjectAreas.isEmpty()) {
            info.append("None");
        } else {
            info.append(String.join(", ", subjectAreas));
        }

        info.append("\n  Student Count: ").append(studentCount);

        return info.toString();
    }

    /**
     * Returns a string representation of the AcademicDepartment
     *
     * @return String containing academic department details
     */
    @Override
    public String toString() {
        return "AcademicDepartment{" +
                "Name='" + departmentName + '\'' +
                ", ID='" + departmentId + '\'' +
                ", Type='" + getDepartmentTypeString() + '\'' +
                ", SubjectAreas=" + subjectAreas.size() +
                ", StudentCount=" + studentCount +
                ", StaffCount=" + staffCount +
                '}';
    }
}
