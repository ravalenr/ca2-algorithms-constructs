package CA_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * SchoolManagementSystem is the main application for managing school employee records.
 * Provides a menu-based interface for sorting, searching, and managing employees.
 *
 * Uses helper classes to keep code organized:
 * - ManagerCreator: handles manager creation
 * - RandomEmployeeGenerator: generates random employees
 * - DepartmentReporter: displays department statistics
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class SchoolManagementSystem {

    private ArrayList<Employee> employeeList;
    private ArrayList<Manager> managerList;
    private ArrayList<Department> departmentList;
    private Scanner scanner;

    // helper classes to organize code
    private ManagerCreator managerCreator;
    private RandomEmployeeGenerator randomGenerator;
    private DepartmentReporter departmentReporter;

    public SchoolManagementSystem() {
        this.employeeList = new ArrayList<>();
        this.managerList = new ArrayList<>();
        this.departmentList = new ArrayList<>();
        this.scanner = new Scanner(System.in);

        // create helper classes
        this.managerCreator = new ManagerCreator(employeeList, managerList, departmentList);
        this.randomGenerator = new RandomEmployeeGenerator(employeeList, managerList, departmentList, managerCreator);
        this.departmentReporter = new DepartmentReporter(employeeList, departmentList);
    }

    /**
     * Main entry point for the application.
     */
    public static void main(String[] args) {
        SchoolManagementSystem system = new SchoolManagementSystem();
        system.run();
    }

    /**
     * Main application loop
     * This method controls the overall flow of the application:
     * 1. Displays welcome message
     * 2. Prompts for and loads data file
     * 3. Displays menu and processes user selections
     * 4. Continues until user selects EXIT option
     */
    public void run() {
        displayWelcomeMessage();

        // Prompt user for filename and load data
        String filename = promptForFilename();
        if (!loadEmployeeDataFromFile(filename)) {
            System.out.println("Warning: Could not load data from file.");
            System.out.println("You can still use the system to add employees manually.");
        }

        // Main application loop - continues until user chooses to exit
        boolean running = true;
        while (running) {
            // Display the menu options using the MenuOption enum
            MenuOption.displayMenu();

            // Get user's menu choice
            int choice = getUserMenuChoice();
            MenuOption selectedOption = MenuOption.fromOptionNumber(choice);

            // Validate the user's selection
            if (selectedOption == null) {
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            // Process the selected menu option using a switch statement
            switch (selectedOption) {
                case SORT:
                    handleSortEmployees();
                    break;
                case SEARCH:
                    handleSearchEmployee();
                    break;
                case ADD_RECORDS:
                    handleAddEmployee();
                    break;
                case GENERATE_RANDOM:
                    handleGenerateRandomEmployees();
                    break;
                case DISPLAY_ALL:
                    handleDisplayAllEmployees();
                    break;
                case DISPLAY_HIERARCHY:
                    handleDisplayHierarchy();
                    break;
                case DEPARTMENT_STATS:
                    handleDepartmentStatistics();
                    break;
                case CHANGE_FILE:
                    System.out.println("\n>>> CHANGE FILE option selected");
                    System.out.println("This feature is not yet implemented.");
                    break;
                case EXIT:
                    running = false;
                    displayExitMessage();
                    break;
            }
        }

        // Clean up resources
        scanner.close();
    }

    private void displayWelcomeMessage() {
        System.out.println("\n");
        System.out.println("========================================");
        System.out.println("   SCHOOL MANAGEMENT SYSTEM");
        System.out.println("   Student: Rafael Valentim Ribeiro");
        System.out.println("   Student ID: 2025129");
        System.out.println("========================================");
        System.out.println("Welcome to the School Management System!");
        System.out.println("This system allows you to manage employee");
        System.out.println("records efficiently using sorting");
        System.out.println("and searching algorithms.");
        System.out.println("========================================\n");
    }

    private void displayExitMessage() {
        System.out.println("\n========================================");
        System.out.println("Thank you for using the");
        System.out.println("School Management System!");
        System.out.println("Goodbye for now!");
        System.out.println("========================================\n");
    }

    private String promptForFilename() {
        System.out.println("Please enter the filename to read:");
        System.out.print("> ");

        String filename = scanner.nextLine().trim();

        // Validate that a filename was entered
        while (filename.isEmpty()) {
            System.out.println("Filename cannot be empty. Please try again:");
            System.out.print("> ");
            filename = scanner.nextLine().trim();
        }

        return filename;
    }

    private int getUserMenuChoice() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                int choice = Integer.parseInt(input);
                return choice;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private boolean loadEmployeeDataFromFile(String filename) {
        System.out.println("\nLoading employee data from file: " + filename);

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstLine = true;
            int recordCount = 0;

            // Read file line by line
            while ((line = br.readLine()) != null) {
                // Skip the header line (first line with column names)
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Parse txt line - split by comma
                String[] data = line.split(",");

                // Validate that we have enough data fields
                if (data.length >= 9) {
                    // Extract employee data from CSV fields
                    String firstName = data[0].trim();
                    String lastName = data[1].trim();
                    String gender = data[2].trim();
                    String email = data[3].trim();
                    double salary = parseSalary(data[4].trim());
                    String departmentName = data[5].trim();
                    String position = data[6].trim();
                    String jobTitle = data[7].trim();
                    String company = data[8].trim();

                    // Create the right type of employee based on position field from CSV
                    // BUG FIX: was creating everyone as Teacher before which was wrong!
                    Employee employee;

                    // check position column to see if this person is a manager type
                    if (position.equalsIgnoreCase("Principal")) {
                        employee = new Principal(firstName, lastName, gender, email,
                                salary, position, jobTitle, company);
                        managerList.add((Manager) employee);
                    } else if (position.equalsIgnoreCase("DeputyPrincipal") ||
                            position.equalsIgnoreCase("Deputy Principal")) {
                        employee = new VicePrincipal(firstName, lastName, gender, email,
                                salary, position, jobTitle, company);
                        managerList.add((Manager) employee);
                    } else if (position.equalsIgnoreCase("DepartmentHead")) {
                        employee = new DepartmentHead(firstName, lastName, gender, email,
                                salary, position, jobTitle, company);
                        managerList.add((Manager) employee);
                    } else {
                        // regular employee, create as Teacher
                        employee = new Teacher(firstName, lastName, gender, email,
                                salary, position, jobTitle, company);
                    }

                    // Add to employee list
                    employeeList.add(employee);

                    // Find or create department and assign to employee
                    Department dept = findOrCreateDepartment(departmentName);
                    employee.setDepartment(dept);
                    dept.addStaff(employee);

                    recordCount++;
                }
            }

            // Create core management structure using helper class
            managerCreator.ensureCoreManagementExists();

            // Create Department Heads for each department
            managerCreator.createDepartmentHeads();

            // Assign managers to employees based on department
            managerCreator.assignManagersToEmployees();

            // Display loading statistics
            System.out.println("File read successfully!");
            System.out.println("Successfully loaded " + recordCount + " employee records.");
            System.out.println("Created " + managerList.size() + " managers.");
            System.out.println("Created " + departmentList.size() + " departments.");
            System.out.println("========================================");

            return true;

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            System.out.println("Please check that the file exists and is accessible.");
            return false;
        }
    }

    private double parseSalary(String salaryStr) {
        try {
            return Double.parseDouble(salaryStr);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private Department findOrCreateDepartment(String departmentName) {
        // Check if department already exists in our list
        for (Department dept : departmentList) {
            if (dept.getDepartmentName().equalsIgnoreCase(departmentName)) {
                return dept;
            }
        }

        // Department doesn't exist, so create a new one
        // Try to match the department name to a DepartmentType enum value
        DepartmentType deptType = DepartmentType.fromDisplayName(departmentName);

        // If no match found, use COMPUTER_SCIENCE as default
        if (deptType == null) {
            deptType = DepartmentType.COMPUTER_SCIENCE;
        }

        // Create new department as AcademicDepartment (suitable for school)
        Department newDept = new AcademicDepartment(departmentName, deptType);
        departmentList.add(newDept);

        return newDept;
    }

    private void handleSortEmployees() {
        System.out.println("\n>>> SORT option selected");

        // Validate that we have employees to sort
        if (employeeList.isEmpty()) {
            System.out.println("No employees to sort.");
            System.out.println("Please load data or add employees first.");
            return;
        }

        // Convert ArrayList to array for the sorting algorithm
        Employee[] employeeArray = employeeList.toArray(new Employee[0]);

        // Sort and display employees (sorted by first name)
        SortingAlgorithms.sortAndDisplayFirst(employeeArray, 20);
    }

    private void handleSearchEmployee() {
        System.out.println("\n>>> SEARCH option selected");

        // Validate that we have employees to search
        if (employeeList.isEmpty()) {
            System.out.println("No employees to search.");
            System.out.println("Please load data or add employees first.");
            return;
        }

        // Sorting the array first
        Employee[] employeeArray = employeeList.toArray(new Employee[0]);
        Employee[] sortedArray = SortingAlgorithms.mergeSort(employeeArray);

        // Prompt user for search term
        System.out.print("Enter employee name to search (Last name or Full name): ");
        String searchName = scanner.nextLine().trim();

        // Validate input
        if (searchName.isEmpty()) {
            System.out.println("Search cancelled. Name cannot be empty.");
            return;
        }

        // Perform search and display results
        // This uses Linear Search (recursive) internally
        SearchAlgorithms.searchAndDisplay(sortedArray, searchName);
    }

    private void handleAddEmployee() {
        System.out.println("\n>>> ADD NEW EMPLOYEE option selected");
        System.out.println("========================================");

        // Get employee name
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine().trim();

        if (firstName.isEmpty()) {
            System.out.println("Operation cancelled. First name cannot be empty.");
            return;
        }

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine().trim();

        if (lastName.isEmpty()) {
            System.out.println("Operation cancelled. Last name cannot be empty.");
            return;
        }

        // Get employee details
        System.out.print("Enter gender (Male/Female): ");
        String gender = scanner.nextLine().trim();

        String email = getValidEmail();
        if (email == null) {
            System.out.println("Operation cancelled.");
            return;
        }

        // Get and validate salary
        System.out.print("Enter salary: ");
        double salary = 0.0;
        try {
            salary = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary format. Using default value of 0.0");
        }

        System.out.print("Enter position (e.g., senior, middle, junior): ");
        String position = scanner.nextLine().trim();

        System.out.print("Enter job title: ");
        String jobTitle = scanner.nextLine().trim();

        // Display and select Department using the DepartmentType enum
        System.out.println("\nSelect Department:");
        DepartmentType[] departmentTypes = DepartmentType.values();
        for (int i = 0; i < departmentTypes.length; i++) {
            System.out.println((i + 1) + ". " + departmentTypes[i].getDisplayName());
        }

        System.out.print("Enter your choice (1-" + departmentTypes.length + "): ");
        int deptChoice = getUserMenuChoice();

        // Validate department selection
        DepartmentType selectedDeptType = null;
        if (deptChoice >= 1 && deptChoice <= departmentTypes.length) {
            selectedDeptType = departmentTypes[deptChoice - 1];
        } else {
            System.out.println("Invalid choice. Using default department.");
            selectedDeptType = DepartmentType.COMPUTER_SCIENCE;
        }

        // Create the new employee as a Teacher (appropriate for school system)
        Employee newEmployee = new Teacher(firstName, lastName, gender, email,
                salary, position, jobTitle, "School");

        // Find or create department and assign to employee
        Department dept = findOrCreateDepartment(selectedDeptType.getDisplayName());
        newEmployee.setDepartment(dept);
        dept.addStaff(newEmployee);

        // Find an appropriate manager for this department using helper
        Manager assignedManager = managerCreator.findManagerForEmployee(newEmployee);
        if (assignedManager != null) {
            assignedManager.addEmployee(newEmployee);
        }

        // Add to employee list
        employeeList.add(newEmployee);

        // Display success message with all details
        System.out.println("\n========================================");
        System.out.println("SUCCESS!");
        System.out.println("\"" + newEmployee.getFullName() + "\" has been added as");
        System.out.println("\"" + jobTitle + "\" to \"" + dept.getDepartmentName() + "\" successfully!");
        System.out.println();
        System.out.println("Assigned Manager: " +
                (assignedManager != null ? assignedManager.getFullName() + " (" +
                        assignedManager.getManagerTypeString() + ")" : "None"));
        System.out.println("========================================\n");

        // Display the newly added employee details
        System.out.println("Employee has been added to the system:");
        newEmployee.displayInfo();
    }

    private String getValidEmail() {
        while (true) {
            System.out.print("Enter email: ");
            String email = scanner.nextLine().trim();

            if (email.isEmpty()) {
                System.out.println("Email cannot be empty. Please try again.");
                continue;
            }

            if (!email.toLowerCase().endsWith("@sunnydalehs.com")) {
                System.out.println("ERROR: Email must use school domain @sunnydalehs.com");
                System.out.print("Try again? (y/n): ");
                String retry = scanner.nextLine().trim().toLowerCase();
                if (!retry.equals("y")) {
                    return null;
                }
                continue;
            }

            return email;
        }
    }

    private void handleGenerateRandomEmployees() {
        System.out.println("\n>>> GENERATE RANDOM EMPLOYEES option selected");

        // Prompt for number of employees to generate
        System.out.print("How many random employees would you like to generate? ");
        int count = getUserMenuChoice();

        // use helper class to generate employees
        int generatedCount = randomGenerator.generateRandomEmployees(count);

        // display stats
        randomGenerator.displayGenerationStats(generatedCount);

        // Display all employees to show the newly generated ones
        System.out.println("Displaying all employees (including newly generated):\n");
        displayAllEmployees();
    }

    private void handleDisplayAllEmployees() {
        System.out.println("\n>>> DISPLAY ALL EMPLOYEES option selected");
        displayAllEmployees();
    }

    private void displayAllEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees to display.");
            return;
        }

        // Sort employees before displaying
        Employee[] employeeArray = employeeList.toArray(new Employee[0]);
        Employee[] sortedEmployees = SortingAlgorithms.mergeSort(employeeArray);

        System.out.println("========================================");
        System.out.println("ALL EMPLOYEES (" + sortedEmployees.length + " total)");
        System.out.println("Sorted alphabetically by first name");
        System.out.println("========================================");

        // Display each employee with their details
        for (int i = 0; i < sortedEmployees.length; i++) {
            Employee emp = sortedEmployees[i];
            System.out.println((i + 1) + ". " + emp.getFullName() +
                    " - " + emp.getJobTitle() +
                    " (" + (emp.getDepartment() != null ? emp.getDepartment().getDepartmentName() : "No Dept") + ")");
        }

        System.out.println("========================================\n");
    }

    private void handleDisplayHierarchy() {
        System.out.println("\n>>> DISPLAY ORGANIZATIONAL HIERARCHY option selected");

        // Validate that we have employees to display information about
        if (employeeList.isEmpty()) {
            System.out.println("No employees to display.");
            System.out.println("Please load data or add employees first.");
            return;
        }

        // Check if we have minimum 20 employees as per requirements
        if (employeeList.size() < 20) {
            System.out.println("WARNING: The system requires minimum 20 employee records.");
            System.out.println("Current records: " + employeeList.size());
            System.out.println("Please add more employees using option 3 or 4.");
            System.out.println();
        }

        // Create a new binary tree for the employee hierarchy
        EmployeeHierarchyTree hierarchyTree = new EmployeeHierarchyTree();

        // Build the tree from the employee list using level-order insertion
        System.out.println("Building employee hierarchy binary tree...");
        System.out.println("Using level-order (breadth-first) insertion method.");
        System.out.println();

        // Insert all employees into the tree
        hierarchyTree.buildFromList(employeeList);

        // Display the tree with level-order traversal
        hierarchyTree.displayTreeSummary();
    }

    private void handleDepartmentStatistics() {
        System.out.println("\n>>> DEPARTMENT STATISTICS REPORT option selected");

        // use helper class to display department statistics
        departmentReporter.displayDepartmentStatistics();
    }
}