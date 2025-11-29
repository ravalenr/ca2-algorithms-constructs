package CA_2;

/**
 * Principal class represents the school principal.
 * Extends Manager class.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class Principal extends Manager {

    public Principal() {
        super();
        this.managerType = ManagerType.PRINCIPAL;
    }

    public Principal(String firstName, String lastName, String gender, String email,
                    double salary, String position, String jobTitle, String company) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company, ManagerType.PRINCIPAL);
    }

    @Override
    public String getManagerInfo() {
        return "Role: Principal - Overall school leadership and strategic planning. " +
               "Managing " + employeeCount + " staff members.";
    }
}
