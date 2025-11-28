package CA_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * SchoolManagementSystem is the main application class that manages employee records
 * for a school organization. This class provides a console-based interface for sorting,
 * searching, adding, and generating employee data.
 *
 * Design Decision: This class serves as the controller and main entry point for the application,
 * coordinating between the data model (Employee objects), algorithms (sorting and searching),
 * and user interface (console menu system).
 *
 * Key Features implemented in this system:
 * 1. File Reading - Loads employee data from CSV file
 * 2. Sorting - Uses Binary Tree Sort (recursive) to sort employees alphabetically
 * 3. Searching - Uses Binary Search (recursive) to find specific employees
 * 4. Adding Records - Allows users to add new employee records with validation
 * 5. Random Generation - Creates random employee data for testing purposes
 * 6. Menu System - Uses Enums for structured, type-safe menu navigation
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class SchoolManagementSystem {

    /**
     * Instance variable to store all employees in the system
     * This ArrayList holds all Employee objects, including Manager subclasses
     */
    private ArrayList<Employee> employeeList;

    /**
     * Instance variable to store all managers separately for quick access
     * This ArrayList provides convenient access to Manager objects for assignment operations
     */
    private ArrayList<Manager> managerList;

    /**
     * Instance variable to store all departments in the system
     * This ArrayList holds all Department objects for the school
     */
    private ArrayList<Department> departmentList;

    /**
     * Scanner object for reading user input from console
     * Used throughout the application for all user interactions
     */
    private Scanner scanner;

    /**
     * Constructor for SchoolManagementSystem
     * Initializes all instance variables with empty collections
     */
    public SchoolManagementSystem() {
        this.employeeList = new ArrayList<>();
        this.managerList = new ArrayList<>();
        this.departmentList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main entry point for the application
     * Creates a SchoolManagementSystem instance and starts the program
     *
     * @param args Command line arguments (not used in this application)
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
                case CHANGE_FILE:
                    handleChangeDataFile();
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

    /**
     * Displays welcome message when the application starts
     * Provides context about the application and its purpose
     */
    private void displayWelcomeMessage() {
        System.out.println("\n");
        System.out.println("========================================");
        System.out.println("   SUNNYDALE HIGH SCHOOL");
        System.out.println("   Staff Management System");
        System.out.println("========================================");
        System.out.println("   Student: Rafael Valentim Ribeiro");
        System.out.println("   Student ID: 2025129");
        System.out.println("========================================");
        System.out.println("Welcome to Sunnydale High School!");
        System.out.println("This system manages staff records using");
        System.out.println("advanced sorting and searching algorithms.");
        System.out.println("========================================\n");
    }

    /**
     * Displays exit message when the application closes
     * Provides a friendly goodbye message to the user
     */
    private void displayExitMessage() {
        System.out.println("\n========================================");
        System.out.println("Thank you for using the");
        System.out.println("School Management System!");
        System.out.println("Goodbye!");
        System.out.println("========================================\n");
    }

    /**
     * Prompts the user to enter a filename for loading employee data
     * Implements input validation to ensure a filename is provided
     *
     * @return The filename entered by the user as a String
     */
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

    /**
     * Reads the user's menu choice from console input
     * Implements error handling for invalid integer input
     *
     * @return The user's menu choice as an integer
     */
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

    /**
     * Loads employee data from a CSV file and populates the system
     * File format expected: First name,Last name,Gender,Email,Salary,Department,Position,Job title,Company
     *
     * This method performs the following operations:
     * 1. Reads the CSV file line by line
     * 2. Parses each line to extract employee information
     * 3. Creates appropriate Employee or Manager objects
     * 4. Assigns departments to employees
     * 5. Assigns managers to employees
     *
     * @param filename The name of the file to read
     * @return true if file was loaded successfully, false otherwise
     */
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

                // Parse CSV line - split by comma
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

                    // Check if position indicates this should be a Manager
                    ManagerType managerType = ManagerType.fromDisplayName(position);

                    Employee employee;

                    if (managerType != null) {
                        // Create Manager object based on manager type
                        Manager manager = null;

                        if (managerType == ManagerType.PRINCIPAL) {
                            manager = new Principal(firstName, lastName, gender, email,
                                    salary, position, jobTitle, company);
                        } else if (managerType == ManagerType.DEPUTY_PRINCIPAL ||
                                   managerType == ManagerType.VICE_PRINCIPAL) {
                            manager = new VicePrincipal(firstName, lastName, gender, email,
                                    salary, position, jobTitle, company);
                        } else if (managerType == ManagerType.DEPARTMENT_HEAD) {
                            manager = new DepartmentHead(firstName, lastName, gender, email,
                                    salary, position, jobTitle, company);
                        } else {
                            // For other manager types, use DepartmentHead as default
                            manager = new DepartmentHead(firstName, lastName, gender, email,
                                    salary, position, jobTitle, company);
                        }

                        employee = manager;
                        managerList.add(manager);
                    } else {
                        // Create regular Teacher employee
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

            // Note: Managers are now loaded directly from the file based on position field
            // Only create additional managers for departments that don't have one
            createManagersForDepartmentsIfNeeded();

            // Assign managers to employees based on department
            assignManagersToEmployees();

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

    /**
     * Parses a salary string to double value
     * Implements error handling for invalid number formats
     *
     * @param salaryStr The salary as a string
     * @return The salary as a double, or 0.0 if parsing fails
     */
    private double parseSalary(String salaryStr) {
        try {
            return Double.parseDouble(salaryStr);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    /**
     * Finds an existing department or creates a new one
     * This method ensures we don't create duplicate departments
     *
     * @param departmentName Name of the department
     * @return Department object (either existing or newly created)
     */
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

        // If no match found, use STUDENT_SUPPORT as default
        if (deptType == null) {
            System.out.println("Warning: Department '" + departmentName + "' not found in enum. Using STUDENT_SUPPORT as default.");
            deptType = DepartmentType.STUDENT_SUPPORT;
        }

        // Create new department as AcademicDepartment (suitable for school)
        Department newDept = new AcademicDepartment(departmentName, deptType);
        departmentList.add(newDept);

        return newDept;
    }

    /**
     * Creates manager positions for departments that don't already have one
     * This method only creates managers for departments lacking management
     * (since managers are now loaded from the data file)
     */
    private void createManagersForDepartmentsIfNeeded() {
        // If managers were loaded from file, we don't need to create additional ones
        if (managerList.size() > 0) {
            System.out.println("Managers loaded from file. Skipping automatic manager creation.");
            return;
        }

        // Only create managers if none were loaded from file
        System.out.println("No managers found in file. Creating default managers for departments...");
        createManagersForDepartments();
    }

    /**
     * Creates manager positions for each department
     * This method ensures each department has at least one manager
     * (Legacy method - now only used when no managers are in the data file)
     */
    private void createManagersForDepartments() {
        // School-appropriate names for managers
        String[] principalFirstNames = {"Robert", "Margaret", "William"};
        String[] principalLastNames = {"Thompson", "Richardson", "Patterson"};

        String[] vpFirstNames = {"Jennifer", "Michael", "Elizabeth"};
        String[] vpLastNames = {"Collins", "Murphy", "Stewart"};

        String[] headFirstNames = {"James", "Mary", "Thomas", "Patricia", "Charles",
                                  "Linda", "Christopher", "Barbara", "Daniel", "Susan"};
        String[] headLastNames = {"Kelly", "Rogers", "Reed", "Cook", "Morgan",
                                 "Bell", "Ward", "Cox", "Hughes", "Sanders"};

        int principalIndex = 0;
        int vpIndex = 0;
        int headIndex = 0;

        // Array of manager types to assign
        ManagerType[] managerTypes = {
            ManagerType.PRINCIPAL,
            ManagerType.VICE_PRINCIPAL,
            ManagerType.DEPARTMENT_HEAD
        };

        int managerTypeIndex = 0;

        // Create one manager for each department
        for (Department dept : departmentList) {
            // Determine manager type (cycle through available types)
            ManagerType managerType = managerTypes[managerTypeIndex % managerTypes.length];
            managerTypeIndex++;

            // Create manager based on type
            Manager manager = null;
            String deptName = dept.getDepartmentName();

            if (managerType == ManagerType.PRINCIPAL) {
                String firstName = principalFirstNames[principalIndex % principalFirstNames.length];
                String lastName = principalLastNames[principalIndex % principalLastNames.length];
                principalIndex++;

                manager = new Principal(firstName, lastName, "Male",
                        firstName.toLowerCase() + "." + lastName.toLowerCase() + "@sunnydalehs.edu",
                        85000.0, "senior", "Principal", "Sunnydale High School");
            } else if (managerType == ManagerType.VICE_PRINCIPAL) {
                String firstName = vpFirstNames[vpIndex % vpFirstNames.length];
                String lastName = vpLastNames[vpIndex % vpLastNames.length];
                vpIndex++;

                manager = new VicePrincipal(firstName, lastName, "Female",
                        firstName.toLowerCase() + "." + lastName.toLowerCase() + "@sunnydalehs.edu",
                        75000.0, "senior", "Vice Principal", "Sunnydale High School");
            } else {
                // Department Head - use proper names
                String firstName = headFirstNames[headIndex % headFirstNames.length];
                String lastName = headLastNames[headIndex % headLastNames.length];
                headIndex++;

                manager = new DepartmentHead(firstName, lastName, "Male",
                        firstName.toLowerCase() + "." + lastName.toLowerCase() + "@sunnydalehs.edu",
                        65000.0, "senior", "Department Head of " + deptName, "Sunnydale High School");
            }

            // Assign department to manager
            manager.setDepartment(dept);
            dept.addStaff(manager);
            dept.setDepartmentHead(manager);

            // Add to lists
            managerList.add(manager);
            employeeList.add(manager);
        }
    }

    /**
     * Assigns managers to employees based on department
     * Each employee is assigned to a manager in their department
     */
    private void assignManagersToEmployees() {
        for (Employee emp : employeeList) {
            // Skip if already a manager or already has a manager assigned
            if (emp instanceof Manager || emp.getManager() != null) {
                continue;
            }

            // Find a manager in the same department
            Manager assignedManager = findManagerForEmployee(emp);
            if (assignedManager != null) {
                assignedManager.addEmployee(emp);
            }
        }
    }

    /**
     * Finds an appropriate manager for an employee
     * Prioritizes managers in the same department
     *
     * @param employee The employee who needs a manager
     * @return Manager object, or null if no suitable manager found
     */
    private Manager findManagerForEmployee(Employee employee) {
        Department empDept = employee.getDepartment();

        // First, try to find a manager in the same department
        for (Manager manager : managerList) {
            if (manager.getDepartment() != null &&
                    manager.getDepartment().equals(empDept)) {
                return manager;
            }
        }

        // If no manager in same department, assign to first available manager
        if (!managerList.isEmpty()) {
            return managerList.get(0);
        }

        return null;
    }

    /**
     * Handles the Sort Employees menu option
     * Sorts all employees alphabetically by name and displays the first 20
     *
     * This method demonstrates the use of Merge Sort algorithm:
     * 1. Asks user to choose sort order (Last Name or First Name)
     * 2. Converts ArrayList to array
     * 3. Calls appropriate SortingAlgorithms method which uses Merge Sort
     * 4. Displays the first 20 sorted employees with their details
     */
    private void handleSortEmployees() {
        System.out.println("\n>>> SORT option selected");

        // Validate that we have employees to sort
        if (employeeList.isEmpty()) {
            System.out.println("No employees to sort.");
            System.out.println("Please load data or add employees first.");
            return;
        }

        // Ask user how they want to sort
        System.out.println("\nHow would you like to sort employees?");
        System.out.println("1. By Last Name (e.g., Anderson, Brown, Clark...)");
        System.out.println("2. By First Name (e.g., Benjamin, Charles, Daniel...)");
        System.out.print("Enter your choice (1 or 2): ");

        int sortChoice = getUserMenuChoice();

        // Convert ArrayList to array for the sorting algorithm
        Employee[] employeeArray = employeeList.toArray(new Employee[0]);

        // Use the appropriate sorting method based on user choice
        if (sortChoice == 2) {
            // Sort by FIRST NAME
            SortingAlgorithms.sortByFirstNameAndDisplayFirst(employeeArray, 20);
        } else {
            // Sort by LAST NAME (default)
            SortingAlgorithms.sortAndDisplayFirst(employeeArray, 20);
        }
    }

    /**
     * Handles the Search Employee menu option
     * Prompts user for a name and searches for the employee using Binary Search
     *
     * This method demonstrates the use of Binary Search algorithm:
     * 1. Sorts the employee array (required for Binary Search)
     * 2. Prompts user for search term
     * 3. Calls SearchAlgorithms.searchAndDisplay() which uses Binary Search (recursive)
     * 4. Displays employee details if found, or not found message
     */
    private void handleSearchEmployee() {
        System.out.println("\n>>> SEARCH option selected");

        // Validate that we have employees to search
        if (employeeList.isEmpty()) {
            System.out.println("No employees to search.");
            System.out.println("Please load data or add employees first.");
            return;
        }

        // Binary Search requires sorted data, so sort the array first
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
        // This uses Binary Search (recursive) internally
        SearchAlgorithms.searchAndDisplay(sortedArray, searchName);
    }

    /**
     * Handles the Add New Employee menu option
     * Prompts user for employee details and adds them to the system
     *
     * This method implements comprehensive input validation:
     * 1. Validates all required fields are not empty
     * 2. Validates numeric inputs (salary)
     * 3. Validates enum selections (Manager Type, Department)
     * 4. Creates appropriate Employee object
     * 5. Assigns department and manager
     * 6. Adds to system and displays confirmation
     */
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

        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

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
            selectedDeptType = DepartmentType.STUDENT_SUPPORT;
        }

        // Create the new employee as a Teacher (appropriate for school system)
        Employee newEmployee = new Teacher(firstName, lastName, gender, email,
                salary, position, jobTitle, "Sunnydale High School");

        // Find or create department and assign to employee
        Department dept = findOrCreateDepartment(selectedDeptType.getDisplayName());
        newEmployee.setDepartment(dept);
        dept.addStaff(newEmployee);

        // Find an appropriate manager for this department
        Manager assignedManager = findManagerForEmployee(newEmployee);
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

    /**
     * Finds an existing manager of a specific type or creates a new one
     * This ensures that each manager type exists in each department
     *
     * @param managerType The type of manager to find or create
     * @param department The department for the manager
     * @return Manager object (either existing or newly created)
     */
    private Manager findOrCreateManager(ManagerType managerType, Department department) {
        // Try to find existing manager of this type in this department
        for (Manager manager : managerList) {
            if (manager.getManagerType() == managerType &&
                    manager.getDepartment() != null &&
                    manager.getDepartment().equals(department)) {
                return manager;
            }
        }

        // No existing manager found, create a new one with a proper name
        Manager newManager = null;

        // Generate a unique manager based on count to avoid name collisions
        int managerCount = managerList.size() + 1;
        String[] firstNames = {"Alexander", "Victoria", "Benjamin", "Catherine", "Nicholas"};
        String[] lastNames = {"Wright", "Scott", "Green", "Adams", "Baker"};

        String firstName = firstNames[managerCount % firstNames.length];
        String lastName = lastNames[managerCount % lastNames.length];

        // Create appropriate Manager subclass based on type
        if (managerType == ManagerType.PRINCIPAL) {
            newManager = new Principal(firstName, lastName, "Male",
                    firstName.toLowerCase() + "." + lastName.toLowerCase() + "@sunnydalehs.edu",
                    80000.0, "senior", "Principal", "Sunnydale High School");
        } else if (managerType == ManagerType.VICE_PRINCIPAL) {
            newManager = new VicePrincipal(firstName, lastName, "Female",
                    firstName.toLowerCase() + "." + lastName.toLowerCase() + "@sunnydalehs.edu",
                    70000.0, "senior", "Vice Principal", "Sunnydale High School");
        } else {
            newManager = new DepartmentHead(firstName, lastName, "Male",
                    firstName.toLowerCase() + "." + lastName.toLowerCase() + "@sunnydalehs.edu",
                    60000.0, "senior", "Department Head of " + department.getDepartmentName(), "Sunnydale High School");
        }

        // Assign department to manager
        newManager.setDepartment(department);
        department.addStaff(newManager);

        // Add to lists
        managerList.add(newManager);
        employeeList.add(newManager);

        return newManager;
    }

    /**
     * Handles the Generate Random Employees menu option
     * Creates random employee records for testing purposes
     *
     * This method:
     * 1. Prompts user for number of employees to generate
     * 2. Validates the input (must be between 1 and 1000)
     * 3. Generates random employee data using predefined arrays
     * 4. Creates Employee objects with random attributes
     * 5. Assigns random departments and managers
     * 6. Displays all employees including newly generated ones
     */
    private void handleGenerateRandomEmployees() {
        System.out.println("\n>>> GENERATE RANDOM EMPLOYEES option selected");

        // Prompt for number of employees to generate
        System.out.print("How many random employees would you like to generate? ");
        int count = getUserMenuChoice();

        // Validate input range
        if (count <= 0 || count > 1000) {
            System.out.println("Invalid count. Please enter a number between 1 and 1000.");
            return;
        }

        // Arrays of sample data for random generation
        String[] firstNames = {
            "John", "Jane", "Michael", "Sarah", "David", "Emily",
            "Robert", "Lisa", "James", "Mary", "William", "Patricia",
            "Richard", "Jennifer", "Thomas", "Linda", "Charles", "Elizabeth",
            "Daniel", "Susan", "Matthew", "Karen", "Anthony", "Nancy",
            "Mark", "Betty", "Donald", "Helen", "Steven", "Sandra"
        };

        String[] lastNames = {
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia",
            "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez",
            "Wilson", "Anderson", "Taylor", "Thomas", "Moore", "Jackson",
            "Martin", "Lee", "Thompson", "White", "Harris", "Clark",
            "Lewis", "Robinson", "Walker", "Young", "Allen", "King"
        };

        String[] genders = {"Male", "Female"};

        String[] positions = {"senior", "middle", "junior", "intern", "contract"};

        // School-appropriate job titles
        String[] jobTitles = {
            "Teacher", "Assistant Teacher", "Senior Teacher", "Head of Year",
            "Counselor", "Student Advisor", "Librarian", "Library Assistant",
            "Lab Technician", "Science Technician", "School Nurse", "Health Assistant",
            "Administrator", "Office Manager", "Receptionist", "Secretary",
            "Coordinator", "Activities Coordinator", "Specialist", "Learning Support",
            "Support Staff", "Teaching Assistant", "Custodian", "Maintenance Worker",
            "Security Guard", "Campus Security", "IT Technician", "Systems Administrator",
            "Canteen Staff", "Chef", "Music Teacher", "Art Teacher",
            "PE Teacher", "Sports Coach", "Drama Teacher", "Language Teacher"
        };

        // Get available manager types and department types from enums
        ManagerType[] managerTypes = ManagerType.values();
        DepartmentType[] departmentTypes = DepartmentType.values();

        System.out.println("\nGenerating " + count + " random employees...\n");

        int generatedCount = 0;

        // Generate the specified number of random employees
        for (int i = 0; i < count; i++) {
            // Generate random data for each employee
            String firstName = firstNames[(int) (Math.random() * firstNames.length)];
            String lastName = lastNames[(int) (Math.random() * lastNames.length)];
            String gender = genders[(int) (Math.random() * genders.length)];
            String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@sunnydalehs.edu";

            // Generate random salary between 25,000 and 100,000
            double salary = 25000 + (Math.random() * 75000);

            String position = positions[(int) (Math.random() * positions.length)];
            String jobTitle = jobTitles[(int) (Math.random() * jobTitles.length)];

            // Select random manager type and department type
            ManagerType randomManagerType = managerTypes[(int) (Math.random() * managerTypes.length)];
            DepartmentType randomDeptType = departmentTypes[(int) (Math.random() * departmentTypes.length)];

            // Create new employee as Teacher (appropriate for school)
            Employee newEmployee = new Teacher(firstName, lastName, gender, email,
                    salary, position, jobTitle, "Sunnydale High School");

            // Mark as randomly generated
            newEmployee.setRandomlyGenerated(true);

            // Assign department
            Department dept = findOrCreateDepartment(randomDeptType.getDisplayName());
            newEmployee.setDepartment(dept);
            dept.addStaff(newEmployee);

            // Assign manager
            Manager assignedManager = findOrCreateManager(randomManagerType, dept);
            if (assignedManager != null) {
                assignedManager.addEmployee(newEmployee);
            }

            // Add to employee list
            employeeList.add(newEmployee);
            generatedCount++;
        }

        // Display generation statistics
        System.out.println("========================================");
        System.out.println("Successfully generated " + generatedCount + " random employees!");
        System.out.println("Total employees in system: " + employeeList.size());
        System.out.println("========================================\n");

        // Display all employees to show the newly generated ones
        System.out.println("Displaying all employees (including newly generated):\n");
        displayAllEmployees();
    }

    /**
     * Handles the Display All Employees menu option
     * Displays all employees currently in the system sorted alphabetically
     */
    private void handleDisplayAllEmployees() {
        System.out.println("\n>>> DISPLAY ALL EMPLOYEES option selected");
        displayAllEmployees();
    }

    /**
     * Displays all employees currently in the system
     * Shows employee name, job title, department, and assigned manager
     * Employees are displayed in alphabetical order by last name
     * Randomly generated employees are marked with [RANDOM] tag
     */
    private void displayAllEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees to display.");
            return;
        }

        // Sort employees before displaying
        Employee[] employeeArray = employeeList.toArray(new Employee[0]);
        Employee[] sortedEmployees = SortingAlgorithms.mergeSort(employeeArray);

        // Count randomly generated employees
        int randomCount = 0;
        for (Employee emp : sortedEmployees) {
            if (emp.isRandomlyGenerated()) {
                randomCount++;
            }
        }

        System.out.println("========================================");
        System.out.println("ALL EMPLOYEES (" + sortedEmployees.length + " total)");
        if (randomCount > 0) {
            System.out.println("Randomly Generated: " + randomCount);
        }
        System.out.println("Sorted alphabetically by last name");
        System.out.println("========================================");

        // Display each employee with their details
        for (int i = 0; i < sortedEmployees.length; i++) {
            Employee emp = sortedEmployees[i];
            String randomTag = emp.isRandomlyGenerated() ? " [RANDOM]" : "";
            System.out.println((i + 1) + ". " + emp.getFullName() +
                    " - " + emp.getJobTitle() +
                    " (" + (emp.getDepartment() != null ? emp.getDepartment().getDepartmentName() : "No Dept") + ")" +
                    randomTag);
        }

        System.out.println("========================================\n");
    }

    /**
     * Handles the Change Data File menu option
     * Allows user to load employee data from a different file
     * Clears existing data before loading new file
     */
    private void handleChangeDataFile() {
        System.out.println("\n>>> CHANGE DATA FILE option selected");
        System.out.println("========================================");
        System.out.println("WARNING: This will clear all current data");
        System.out.println("including manually added employees.");
        System.out.println("========================================");
        System.out.print("Do you want to continue? (yes/no): ");

        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (!confirmation.equals("yes") && !confirmation.equals("y")) {
            System.out.println("Operation cancelled. Returning to main menu.");
            return;
        }

        // Prompt for new filename
        String newFilename = promptForFilename();

        // Clear existing data
        employeeList.clear();
        managerList.clear();
        departmentList.clear();

        System.out.println("\nClearing existing data...");
        System.out.println("Loading new file: " + newFilename);

        // Load new data from file
        if (loadEmployeeDataFromFile(newFilename)) {
            System.out.println("\n========================================");
            System.out.println("SUCCESS: Data loaded from new file!");
            System.out.println("Total employees loaded: " + employeeList.size());
            System.out.println("Total managers: " + managerList.size());
            System.out.println("Total departments: " + departmentList.size());
            System.out.println("========================================\n");
        } else {
            System.out.println("\n========================================");
            System.out.println("ERROR: Could not load data from file.");
            System.out.println("The system is now empty.");
            System.out.println("You can add employees manually or try");
            System.out.println("loading another file.");
            System.out.println("========================================\n");
        }
    }

    /**
     * Handles the Display Employee Hierarchy menu option
     * Displays the organizational hierarchy showing managers and their departments
     *
     * This method shows:
     * 1. The organizational structure of the school
     * 2. Managers and their assigned departments
     * 3. Current system statistics
     */
    private void handleDisplayHierarchy() {
        System.out.println("\n>>> DISPLAY ORGANIZATIONAL HIERARCHY option selected");

        // Validate that we have employees to display information about
        if (employeeList.isEmpty()) {
            System.out.println("No employees to display.");
            System.out.println("Please load data or add employees first.");
            return;
        }

        // Display organizational hierarchy
        System.out.println("========================================");
        System.out.println("SCHOOL ORGANIZATIONAL HIERARCHY");
        System.out.println("========================================");
        System.out.println();
        System.out.println("MANAGEMENT STRUCTURE:");
        System.out.println("----------------------------------------");

        // Display managers by type
        for (Manager manager : managerList) {
            System.out.println(manager.getManagerTypeString() + ": " +
                             manager.getFullName());
            System.out.println("  Department: " +
                             (manager.getDepartment() != null ?
                              manager.getDepartment().getDepartmentName() : "Not assigned"));
            System.out.println("  Employees Managed: " + manager.getEmployeeCount());
            System.out.println();
        }

        System.out.println("========================================");
        System.out.println("SYSTEM STATISTICS:");
        System.out.println("----------------------------------------");
        System.out.println("Total employees: " + employeeList.size());
        System.out.println("Total managers: " + managerList.size());
        System.out.println("Total departments: " + departmentList.size());
        System.out.println("========================================\n");
    }
}
