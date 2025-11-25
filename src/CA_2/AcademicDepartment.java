package CA_2;

/**
 * AcademicDepartment class represents an academic department in the school management system.
 * This is a concrete implementation of the Department abstract class.
 *
 * Academic departments focus on curriculum delivery, teaching, and student learning outcomes.
 * Examples include Mathematics, Science, English, History, and other subject-specific departments.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class AcademicDepartment extends Department {

    private String curriculumFocus;
    private int numberOfCourses;

    /**
     * Default constructor
     * Creates an AcademicDepartment object with default values
     */
    public AcademicDepartment() {
        super();
        this.curriculumFocus = "";
        this.numberOfCourses = 0;
    }

    /**
     * Parameterized constructor
     * Creates an AcademicDepartment object with specified values
     *
     * @param departmentName Name of the academic department
     * @param departmentType Type of department (from DepartmentType enum)
     */
    public AcademicDepartment(String departmentName, DepartmentType departmentType) {
        super(departmentName, departmentType);
        this.curriculumFocus = "";
        this.numberOfCourses = 0;
    }

    /**
     * Returns the curriculum focus of this academic department
     *
     * @return The curriculum focus as a String
     */
    public String getCurriculumFocus() {
        return curriculumFocus;
    }

    /**
     * Sets the curriculum focus for this academic department
     *
     * @param curriculumFocus The curriculum focus to set
     */
    public void setCurriculumFocus(String curriculumFocus) {
        this.curriculumFocus = curriculumFocus;
    }

    /**
     * Returns the number of courses offered by this academic department
     *
     * @return The number of courses as an integer
     */
    public int getNumberOfCourses() {
        return numberOfCourses;
    }

    /**
     * Sets the number of courses offered by this academic department
     *
     * @param numberOfCourses The number of courses to set
     */
    public void setNumberOfCourses(int numberOfCourses) {
        this.numberOfCourses = numberOfCourses;
    }

    /**
     * Returns information specific to this academic department
     * This method provides details about the department's academic focus
     *
     * @return String containing academic department-specific information
     */
    @Override
    public String getDepartmentInfo() {
        return "Academic Department - Focus: " +
               (curriculumFocus.isEmpty() ? "General Education" : curriculumFocus) +
               ", Courses Offered: " + numberOfCourses +
               ", Staff: " + staffCount;
    }

    /**
     * Returns a string representation of the AcademicDepartment object
     *
     * @return String containing academic department details
     */
    @Override
    public String toString() {
        return "AcademicDepartment{" +
                "Name='" + departmentName + '\'' +
                ", Type='" + getDepartmentTypeString() + '\'' +
                ", Staff=" + staffCount +
                ", Courses=" + numberOfCourses +
                '}';
    }
}
