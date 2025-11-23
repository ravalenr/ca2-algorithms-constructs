package CA_2;

/**
 * Employee class serves as the parent class for all employee types in the school management system.
 * This class contains common attributes and methods that all employees share regardless of their role.
 *
 * Design Decision: Using a parent class allows for code reusability and polymorphism,
 * enabling different employee types to be treated uniformly while maintaining their unique characteristics.
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

    /**
     * Default constructor
     * Creates an Employee object with default values
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
    }

    /**
     * Parameterized constructor
     * Creates an Employee object with specified values
     *
     * @param firstName First name of the employee
     * @param lastName Last name of the employee
     * @param gender Gender of the employee
     * @param email Email address of the employee
     * @param salary Annual salary of the employee
     * @param position Position level (senior, middle, junior, etc.)
     * @param jobTitle Job title of the employee
     * @param company Company name
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
    }

    /**
     * Generates a unique employee ID based on first and last name
     * Format: First letter of first name + last name + random 3 digits
     *
     * @param firstName Employee's first name
     * @param lastName Employee's last name
     * @return Generated employee ID
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

    // Getter methods

    /**
     * Returns the employee's unique identifier
     * @return employeeId as String
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Returns the employee's first name
     * @return firstName as String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the employee's last name
     * @return lastName as String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the employee's full name (First Last)
     * @return Full name as String
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Returns the employee's gender
     * @return gender as String
     */
    public String getGender() {
        return gender;
    }

    /**
     * Returns the employee's email address
     * @return email as String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the employee's salary
     * @return salary as double
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Returns the employee's position level
     * @return position as String
     */
    public String getPosition() {
        return position;
    }

    /**
     * Returns the employee's job title
     * @return jobTitle as String
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Returns the company name
     * @return company as String
     */
    public String getCompany() {
        return company;
    }

    /**
     * Returns the employee's assigned manager
     * @return manager as Manager object
     */
    public Manager getManager() {
        return manager;
    }

    /**
     * Returns the employee's department
     * @return department as Department object
     */
    public Department getDepartment() {
        return department;
    }

    // Setter methods

    /**
     * Sets the employee's unique identifier
     * @param employeeId The unique ID to set
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Sets the employee's first name
     * @param firstName The first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the employee's last name
     * @param lastName The last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the employee's gender
     * @param gender The gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Sets the employee's email address
     * @param email The email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the employee's salary
     * @param salary The salary to set
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Sets the employee's position level
     * @param position The position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Sets the employee's job title
     * @param jobTitle The job title to set
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * Sets the company name
     * @param company The company name to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Sets the employee's manager
     * @param manager The manager to assign
     */
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    /**
     * Sets the employee's department
     * @param department The department to assign
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Returns a string representation of the Employee object
     * Useful for displaying employee information in a readable format
     *
     * @return String containing employee details
     */
    @Override
    public String toString() {
        return "Employee{" +
                "ID='" + employeeId + '\'' +
                ", Name='" + getFullName() + '\'' +
                ", Email='" + email + '\'' +
                ", Salary=" + salary +
                ", JobTitle='" + jobTitle + '\'' +
                ", Manager=" + (manager != null ? manager.getFullName() : "None") +
                ", Department=" + (department != null ? department.getDepartmentName() : "None") +
                '}';
    }

    /**
     * Displays the employee's complete information to the console
     * This method provides a formatted output for better readability
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
