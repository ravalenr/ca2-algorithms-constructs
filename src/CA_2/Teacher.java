package CA_2;

import java.util.ArrayList;

/**
 * Teacher class represents teaching staff in the school system.
 * This class extends Employee and includes specific attributes for teaching responsibilities.
 *
 * Design Decision: Teacher is a concrete implementation of Employee representing
 * academic staff who teach subjects and grade student work.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class Teacher extends Employee {

    // Additional attributes specific to Teacher
    private ArrayList<String> subjects;
    private String qualifications;

    /**
     * Default constructor
     * Creates a Teacher with default values
     */
    public Teacher() {
        super();
        this.subjects = new ArrayList<>();
        this.qualifications = "";
    }

    /**
     * Parameterized constructor
     * Creates a Teacher with specified values
     *
     * @param firstName First name of the teacher
     * @param lastName Last name of the teacher
     * @param gender Gender of the teacher
     * @param email Email address of the teacher
     * @param salary Annual salary of the teacher
     * @param position Position level
     * @param jobTitle Job title
     * @param company School/organization name
     */
    public Teacher(String firstName, String lastName, String gender, String email,
                  double salary, String position, String jobTitle, String company) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company);
        this.subjects = new ArrayList<>();
        this.qualifications = "";
    }

    /**
     * Parameterized constructor with qualifications
     *
     * @param firstName First name of the teacher
     * @param lastName Last name of the teacher
     * @param gender Gender of the teacher
     * @param email Email address of the teacher
     * @param salary Annual salary of the teacher
     * @param position Position level
     * @param jobTitle Job title
     * @param company School/organization name
     * @param qualifications Academic qualifications
     */
    public Teacher(String firstName, String lastName, String gender, String email,
                  double salary, String position, String jobTitle, String company,
                  String qualifications) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company);
        this.subjects = new ArrayList<>();
        this.qualifications = qualifications;
    }

    /**
     * Returns the list of subjects taught
     * @return ArrayList of subjects
     */
    public ArrayList<String> getSubjects() {
        return new ArrayList<>(subjects);
    }

    /**
     * Adds a subject to the teacher's teaching load
     * @param subject The subject to add
     * @return true if successfully added, false otherwise
     */
    public boolean addSubject(String subject) {
        if (subject == null || subject.trim().isEmpty()) {
            return false;
        }

        if (subjects.contains(subject)) {
            return false;
        }

        subjects.add(subject);
        return true;
    }

    /**
     * Removes a subject from the teacher's teaching load
     * @param subject The subject to remove
     * @return true if successfully removed, false otherwise
     */
    public boolean removeSubject(String subject) {
        return subjects.remove(subject);
    }

    /**
     * Returns the qualifications
     * @return qualifications as String
     */
    public String getQualifications() {
        return qualifications;
    }

    /**
     * Sets the qualifications
     * @param qualifications The qualifications to set
     */
    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    /**
     * Simulates teaching a class
     * This method represents the teacher conducting a lesson
     *
     * @param subject Subject being taught
     * @param className Name of the class
     */
    public void teachClass(String subject, String className) {
        if (subject == null || subject.trim().isEmpty()) {
            System.out.println("Subject required for teaching.");
            return;
        }

        if (!subjects.contains(subject)) {
            System.out.println("Teacher " + getFullName() + " is not qualified to teach " + subject);
            return;
        }

        System.out.println("Teaching Session:");
        System.out.println("Teacher: " + getFullName());
        System.out.println("Subject: " + subject);
        System.out.println("Class: " + (className != null ? className : "Not specified"));
        System.out.println("Department: " + (department != null ? department.getDepartmentName() : "Not assigned"));
    }

    /**
     * Simulates grading assignments
     * This method represents the teacher evaluating student work
     *
     * @param assignmentName Name of the assignment
     * @param studentCount Number of students
     */
    public void gradeAssignments(String assignmentName, int studentCount) {
        if (assignmentName == null || assignmentName.trim().isEmpty()) {
            System.out.println("Assignment name required.");
            return;
        }

        System.out.println("Grading Assignments:");
        System.out.println("Teacher: " + getFullName());
        System.out.println("Assignment: " + assignmentName);
        System.out.println("Student Count: " + studentCount);
        System.out.println("Status: Grading in progress");
    }

    /**
     * Displays teacher's profile and teaching information
     */
    public void displayTeacherProfile() {
        System.out.println("Teacher Profile:");
        System.out.println("Name: " + getFullName());
        System.out.println("Email: " + email);
        System.out.println("Qualifications: " + qualifications);
        System.out.println("Subjects (" + subjects.size() + "): " + String.join(", ", subjects));
        System.out.println("Department: " + (department != null ? department.getDepartmentName() : "Not assigned"));
        System.out.println("Manager: " + (manager != null ? manager.getFullName() : "None"));
    }

    /**
     * Returns a string representation of the Teacher
     *
     * @return String containing teacher details
     */
    @Override
    public String toString() {
        return "Teacher{" +
                "Name='" + getFullName() + '\'' +
                ", ID='" + employeeId + '\'' +
                ", Qualifications='" + qualifications + '\'' +
                ", Subjects=" + subjects.size() +
                ", Department='" + (department != null ? department.getDepartmentName() : "None") + '\'' +
                '}';
    }
}
