package CA_2;

/**
 * Principal class represents the school principal in the school management system.
 * This is a concrete implementation of the Manager abstract class.
 *
 * The Principal is the highest-ranking manager in the school, responsible for
 * overall school leadership, strategic planning, and final decision-making authority.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class Principal extends Manager {

    /**
     * Default constructor
     * Creates a Principal object with default values
     */
    public Principal() {
        super();
        this.managerType = ManagerType.PRINCIPAL;
    }

    /**
     * Parameterized constructor
     * Creates a Principal object with specified values
     *
     * @param firstName First name of the principal
     * @param lastName Last name of the principal
     * @param gender Gender of the principal
     * @param email Email address of the principal
     * @param salary Annual salary of the principal
     * @param position Position level
     * @param jobTitle Job title of the principal
     * @param company School name
     */
    public Principal(String firstName, String lastName, String gender, String email,
                    double salary, String position, String jobTitle, String company) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company, ManagerType.PRINCIPAL);
    }

    /**
     * Returns information specific to the Principal role
     * This method provides details about the principal's responsibilities
     *
     * @return String containing principal-specific information
     */
    @Override
    public String getManagerInfo() {
        return "Role: Principal - Overall school leadership and strategic planning. " +
               "Managing " + employeeCount + " staff members.";
    }
}
