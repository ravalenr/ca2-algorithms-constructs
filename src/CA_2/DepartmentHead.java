package CA_2;

/**
 * DepartmentHead class represents a department head.
 * Extends Manager class.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class DepartmentHead extends Manager {

    public DepartmentHead() {
        super();
        this.managerType = ManagerType.DEPARTMENT_HEAD;
    }

    public DepartmentHead(String firstName, String lastName, String gender, String email,
                         double salary, String position, String jobTitle, String company) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company, ManagerType.DEPARTMENT_HEAD);
    }
}
