package CA_2;

/**
 * VicePrincipal class represents the vice principal.
 * Extends Manager class.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class VicePrincipal extends Manager {

    public VicePrincipal() {
        super();
        this.managerType = ManagerType.VICE_PRINCIPAL;
    }

    public VicePrincipal(String firstName, String lastName, String gender, String email,
                        double salary, String position, String jobTitle, String company) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company, ManagerType.VICE_PRINCIPAL);
    }

    @Override
    public String getManagerInfo() {
        return "Role: Vice Principal - Assists principal, manages discipline and events. " +
               "Managing " + employeeCount + " staff members.";
    }
}
