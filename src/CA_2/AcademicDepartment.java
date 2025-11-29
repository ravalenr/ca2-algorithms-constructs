package CA_2;

/**
 * AcademicDepartment represents an academic department in the school.
 * Extends the Department class.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class AcademicDepartment extends Department {

    /**
     * Default constructor
     */
    public AcademicDepartment() {
        super();
    }

    /**
     * Constructor with parameters.
     *
     * @param departmentName Name of the department
     * @param departmentType Type of department
     */
    public AcademicDepartment(String departmentName, DepartmentType departmentType) {
        super(departmentName, departmentType);
    }

    @Override
    public String getDepartmentInfo() {
        return "Academic Department - Staff: " + staffCount;
    }

    @Override
    public String toString() {
        return "AcademicDepartment{" +
                "Name='" + departmentName + '\'' +
                ", Type='" + getDepartmentTypeString() + '\'' +
                ", Staff=" + staffCount +
                '}';
    }
}
