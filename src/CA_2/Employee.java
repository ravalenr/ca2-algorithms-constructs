package CA_2;

/**
 * Employee class is the parent class for all employee types.
 * Contains common attributes and methods shared by all employees.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class Employee {

    // Instance variables - protected to allow subclass access
    protected String employeeId;
    protected String firstName;
    protected String lastName;
    protected String gender;
    protected String email;
    protected double salary;
    protected String position;
    protected String jobTitle;
    protected String company;
    protected Manager manager;
    protected Department department;
    protected boolean isRandomlyGenerated;

    /**
     * Default constructor
     */
    public Employee() {
        this.employeeId = "";
        this.firstName = "";
        this.lastName = "";
        this.gender = "";
        this.email = "";
        this.salary = 0.0;
        this.position = "";
        this.jobTitle = "";
        this.company = "";
        this.manager = null;
        this.department = null;
        this.isRandomlyGenerated = false;
    }

    /**
     * Constructor with parameters
     */
    public Employee(String firstName, String lastName, String gender, String email,
                   double salary, String position, String jobTitle, String company) {
        this.employeeId = generateEmployeeId(firstName, lastName);
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.salary = salary;
        this.position = position;
        this.jobTitle = jobTitle;
        this.company = company;
        this.manager = null;
        this.department = null;
        this.isRandomlyGenerated = false;
    }

    /**
     * Generates a unique employee ID.
     * Format: First letter of first name + last name + random 3 digits
     */
    private String generateEmployeeId(String firstName, String lastName) {
        if (firstName == null || lastName == null || firstName.isEmpty() || lastName.isEmpty()) {
            return "EMP" + (int)(Math.random() * 10000);
        }
        String id = firstName.substring(0, 1).toUpperCase() +
                   lastName.toUpperCase() +
                   (int)(Math.random() * 1000);
        return id;
    }

    public String getEmployeeId() { return employeeId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getFullName() { return firstName + " " + lastName; }
    public String getEmail() { return email; }
    public double getSalary() { return salary; }
    public String getJobTitle() { return jobTitle; }
    public Manager getManager() { return manager; }
    public Department getDepartment() { return department; }
    public boolean isRandomlyGenerated() { return isRandomlyGenerated; }

    public void setManager(Manager manager) { this.manager = manager; }
    public void setDepartment(Department department) { this.department = department; }

    /**
     * Displays employee information
     */
    public void displayInfo() {
        System.out.println("========================================");
        System.out.println("Employee Information:");
        System.out.println("ID: " + employeeId);
        System.out.println("Name: " + getFullName());
        System.out.println("Gender: " + gender);
        System.out.println("Email: " + email);
        System.out.println("Salary: " + salary);
        System.out.println("Position: " + position);
        System.out.println("Job Title: " + jobTitle);
        System.out.println("Company: " + company);
        System.out.println("Manager: " + (manager != null ? manager.getFullName() : "None"));
        System.out.println("Department: " + (department != null ? department.getDepartmentName() : "None"));
        System.out.println("========================================");
    }
}
