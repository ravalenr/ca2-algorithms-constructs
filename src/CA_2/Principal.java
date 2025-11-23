package CA_2;

/**
 * Principal class represents the highest level of management in the school system.
 * This class extends Manager and includes specific attributes for school-wide leadership.
 *
 * Design Decision: Principal is a concrete implementation of the abstract Manager class,
 * representing the top leadership position with responsibilities for overall school vision and budget.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class Principal extends Manager {

    // Additional attributes specific to Principal
    private String schoolVision;
    private double budget;

    /**
     * Default constructor
     * Creates a Principal with default values
     */
    public Principal() {
        super();
        this.schoolVision = "";
        this.budget = 0.0;
        this.managerType = ManagerType.PRINCIPAL;
    }

    /**
     * Parameterized constructor
     * Creates a Principal with specified values
     *
     * @param firstName First name of the principal
     * @param lastName Last name of the principal
     * @param gender Gender of the principal
     * @param email Email address of the principal
     * @param salary Annual salary of the principal
     * @param position Position level
     * @param jobTitle Job title
     * @param company School/organization name
     */
    public Principal(String firstName, String lastName, String gender, String email,
                    double salary, String position, String jobTitle, String company) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company, ManagerType.PRINCIPAL);
        this.schoolVision = "Excellence in Education";
        this.budget = 0.0;
    }

    /**
     * Parameterized constructor with all attributes
     *
     * @param firstName First name of the principal
     * @param lastName Last name of the principal
     * @param gender Gender of the principal
     * @param email Email address of the principal
     * @param salary Annual salary of the principal
     * @param position Position level
     * @param jobTitle Job title
     * @param company School/organization name
     * @param schoolVision Vision statement for the school
     * @param budget Annual budget managed by principal
     */
    public Principal(String firstName, String lastName, String gender, String email,
                    double salary, String position, String jobTitle, String company,
                    String schoolVision, double budget) {
        super(firstName, lastName, gender, email, salary, position, jobTitle, company, ManagerType.PRINCIPAL);
        this.schoolVision = schoolVision;
        this.budget = budget;
    }

    /**
     * Returns the school vision statement
     * @return schoolVision as String
     */
    public String getSchoolVision() {
        return schoolVision;
    }

    /**
     * Sets the school vision statement
     * @param schoolVision The vision statement to set
     */
    public void setSchoolVision(String schoolVision) {
        this.schoolVision = schoolVision;
    }

    /**
     * Returns the annual budget managed by the principal
     * @return budget as double
     */
    public double getBudget() {
        return budget;
    }

    /**
     * Sets the annual budget
     * @param budget The budget amount to set
     */
    public void setBudget(double budget) {
        this.budget = budget;
    }

    /**
     * Approves budget allocations for school operations
     * This method simulates budget approval process
     *
     * @param amount The amount to approve
     * @return true if amount is within budget, false otherwise
     */
    public boolean approveBudget(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid budget amount.");
            return false;
        }

        if (amount <= budget) {
            budget -= amount;
            System.out.println("Budget approved: " + amount);
            System.out.println("Remaining budget: " + budget);
            return true;
        } else {
            System.out.println("Budget approval denied. Insufficient funds.");
            System.out.println("Requested: " + amount + ", Available: " + budget);
            return false;
        }
    }

    /**
     * Sets school-wide policies
     * This method simulates policy setting functionality
     *
     * @param policy The policy description to set
     */
    public void setSchoolPolicy(String policy) {
        if (policy == null || policy.trim().isEmpty()) {
            System.out.println("Invalid policy. Cannot be empty.");
            return;
        }

        System.out.println("New school policy set by Principal " + getFullName() + ":");
        System.out.println(policy);
    }

    /**
     * Implementation of abstract method from Manager class
     * Returns specific information about the Principal
     *
     * @return String containing principal-specific information
     */
    @Override
    public String getManagerInfo() {
        return "Principal Information:\n" +
               "  School Vision: " + schoolVision + "\n" +
               "  Budget Managed: " + budget;
    }

    /**
     * Returns a string representation of the Principal
     *
     * @return String containing principal details
     */
    @Override
    public String toString() {
        return "Principal{" +
                "Name='" + getFullName() + '\'' +
                ", ManagerID='" + managerId + '\'' +
                ", Email='" + email + '\'' +
                ", SchoolVision='" + schoolVision + '\'' +
                ", Budget=" + budget +
                ", EmployeesManaged=" + employeeCount +
                '}';
    }
}
