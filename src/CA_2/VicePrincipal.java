package CA_2;

/**
 * VicePrincipal class represents the vice principal in the school management system.
 * This is a concrete implementation of the Manager abstract class.
 *
 * The Vice Principal assists the principal in school operations, handles student discipline,
 * manages school events, and acts as principal in their absence.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class VicePrincipal extends Manager {

    /**
     * Default constructor
     * Creates a VicePrincipal object with default values
     */
    public VicePrincipal() {
        super();
        this.managerType = ManagerType.VICE_PRINCIPAL;
    }

    /**
     * Parameterized constructor
     * Creates a VicePrincipal object with specified values
     *
     * @param firstName First name of the vice principal
     * @param lastName Last name of the vice principal
     * @param gender Gender of the vice principal
     * @param email Email address of the vice principal
     * @param salary Annual salary of the vice principal
     * @param position Position level
     * @param jobTitle Job title of the vice principal
     * @param company School name
     */
    public VicePrincipal(String firstName, String lastName, String gender, String email,
                        double salary, String position, String jobTitle, String company) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company, ManagerType.VICE_PRINCIPAL);
    }

    /**
     * Returns information specific to the Vice Principal role
     * This method provides details about the vice principal's responsibilities
     *
     * @return String containing vice principal-specific information
     */
    @Override
    public String getManagerInfo() {
        return "Role: Vice Principal - Assists principal, manages discipline and events. " +
               "Managing " + employeeCount + " staff members.";
    }
}
