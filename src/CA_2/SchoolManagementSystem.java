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
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class SchoolManagementSystem {

    private ArrayList<Employee> employeeList;

    private ArrayList<Manager> managerList;

    private ArrayList<Department> departmentList;

    private Scanner scanner;

    public SchoolManagementSystem() {
        this.employeeList = new ArrayList<>();
        this.managerList = new ArrayList<>();
        this.departmentList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
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

                    // Create Employee object (will be Teacher for this school system)
                    Employee employee = new Teacher(firstName, lastName, gender, email,
                            salary, position, jobTitle, company);

                    // Add to employee list
                    employeeList.add(employee);

                    // Find or create department and assign to employee
                    Department dept = findOrCreateDepartment(departmentName);
                    employee.setDepartment(dept);
                    dept.addStaff(employee);

                    recordCount++;
                }
            }

            // Create some managers for the loaded employees
            createManagersForDepartments();

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

    private void createManagersForDepartments() {
        // School-appropriate names for managers
        String[] principalFirstNames = {"Rafael", "Jason", "Claudia"};
        String[] principalLastNames = {"Ribeiro", "Doyle", "Valentim"};

        String[] vpFirstNames = {"Daniela", "Suzane", "Linda"};
        String[] vpLastNames = {"Silva", "Santos", "Ahmed"};

        String[] headFirstNames = {"Cris", "Caoimhe", "Saoirse", "Niamh", "Tara",
                                  "Claire", "Paulo", "Roberto", "Carlos", "Keeva"};
        String[] headLastNames = {"Simpsons", "Stanley", "Creed", "Kardashian", "Rose",
                                 "Pinto", "White", "Cox", "Hughes", "Sanders"};

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
                        firstName.toLowerCase() + "." + lastName.toLowerCase() + "@sunnydalehs.com",
                        85000.0, "senior", "Principal", "School");
            } else if (managerType == ManagerType.VICE_PRINCIPAL) {
                String firstName = vpFirstNames[vpIndex % vpFirstNames.length];
                String lastName = vpLastNames[vpIndex % vpLastNames.length];
                vpIndex++;

                manager = new VicePrincipal(firstName, lastName, "Female",
                        firstName.toLowerCase() + "." + lastName.toLowerCase() + "@sunnydalehs.com",
                        75000.0, "senior", "Vice Principal", "School");
            } else {
                // Department Head - use proper names
                String firstName = headFirstNames[headIndex % headFirstNames.length];
                String lastName = headLastNames[headIndex % headLastNames.length];
                headIndex++;

                manager = new DepartmentHead(firstName, lastName, "Male",
                        firstName.toLowerCase() + "." + lastName.toLowerCase() + "@sunnydalehs.com",
                        65000.0, "senior", "Department Head of " + deptName, "School");
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

    private Manager findOrCreateManager(ManagerType managerType, Department department) {
        // School-wide unique positions: Principal, Vice Principal, Dean, Academic Coordinator
        // Only ONE of each should exist across the entire school
        if (managerType == ManagerType.PRINCIPAL ||
            managerType == ManagerType.VICE_PRINCIPAL ||
            managerType == ManagerType.DEPUTY_PRINCIPAL ||
            managerType == ManagerType.DEAN ||
            managerType == ManagerType.ACADEMIC_COORDINATOR) {

            // Check if this manager type already exists (globally, not per department)
            for (Manager manager : managerList) {
                if (manager.getManagerType() == managerType) {
                    // Found existing school-wide manager, assign this employee to them
                    return manager;
                }
            }
        } else if (managerType == ManagerType.DEPARTMENT_HEAD) {
            // Department Heads are unique PER department
            // Check if this department already has a Department Head
            for (Manager manager : managerList) {
                if (manager.getManagerType() == ManagerType.DEPARTMENT_HEAD &&
                        manager.getDepartment() != null &&
                        manager.getDepartment().equals(department)) {
                    return manager;
                }
            }
        }

        // No existing manager found, create a new one with a proper name
        Manager newManager = null;

        // Generate a unique manager based on count to avoid name collisions
        int managerCount = managerList.size() + 1;
        String[] firstNames = {"Alexander", "Victoria", "Benjamin", "Catherine", "Nicholas", "Rupert", "Joyce"};
        String[] lastNames = {"Wright", "Scott", "Green", "Adams", "Baker", "Giles", "Summers"};

        String firstName = firstNames[managerCount % firstNames.length];
        String lastName = lastNames[managerCount % lastNames.length];
        String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@sunnydalehs.com";

        // Create appropriate Manager subclass based on type
        if (managerType == ManagerType.PRINCIPAL) {
            newManager = new Principal(firstName, lastName, "Male", email,
                    80000.0, "senior", "Principal", "School");
        } else if (managerType == ManagerType.VICE_PRINCIPAL || managerType == ManagerType.DEPUTY_PRINCIPAL) {
            newManager = new VicePrincipal(firstName, lastName, "Female", email,
                    70000.0, "senior", "Vice Principal", "School");
        } else if (managerType == ManagerType.DEAN) {
            newManager = new DepartmentHead(firstName, lastName, "Male", email,
                    65000.0, "senior", "Dean of Students", "School");
        } else if (managerType == ManagerType.ACADEMIC_COORDINATOR) {
            newManager = new DepartmentHead(firstName, lastName, "Female", email,
                    60000.0, "senior", "Academic Coordinator", "School");
        } else {
            // DEPARTMENT_HEAD or default
            newManager = new DepartmentHead(firstName, lastName, "Male", email,
                    60000.0, "senior", "Department Head of " + department.getDepartmentName(), "School");
        }

        // Assign department to manager
        newManager.setDepartment(department);
        department.addStaff(newManager);

        // Add to lists
        managerList.add(newManager);
        employeeList.add(newManager);

        return newManager;
    }

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
            "Teacher", "Assistant Teacher", "Counselor", "Librarian",
            "Lab Technician", "School Nurse", "Administrator", "Coordinator",
            "Specialist", "Support Staff", "Custodian", "Security Guard"
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
            String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@sunnydalehs.com";

            // Generate random salary between 25,000 and 100,000
            double salary = 25000 + (Math.random() * 75000);

            String position = positions[(int) (Math.random() * positions.length)];
            String jobTitle = jobTitles[(int) (Math.random() * jobTitles.length)];

            // Select random manager type and department type
            ManagerType randomManagerType = managerTypes[(int) (Math.random() * managerTypes.length)];
            DepartmentType randomDeptType = departmentTypes[(int) (Math.random() * departmentTypes.length)];

            // Create new employee as Teacher (appropriate for school)
            Employee newEmployee = new Teacher(firstName, lastName, gender, email,
                    salary, position, jobTitle, "School");

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
        System.out.println("Sorted alphabetically by last name");
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

    private void handleDepartmentStatistics() {
        System.out.println("\n>>> DEPARTMENT STATISTICS REPORT option selected");

        if (departmentList.isEmpty()) {
            System.out.println("No departments to display.");
            System.out.println("Please load data or generate employees first.");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("DEPARTMENT STATISTICS REPORT");
        System.out.println("========================================\n");

        // Group departments by category
        System.out.println("ACADEMIC DEPARTMENTS:");
        System.out.println("----------------------------------------");
        int academicCount = 0;
        int academicStaff = 0;
        for (Department dept : departmentList) {
            DepartmentType type = dept.departmentType;
            if (type == DepartmentType.SLAYER_STUDIES || type == DepartmentType.MAGIC_COMPUTATION ||
                type == DepartmentType.MATHEMATICS || type == DepartmentType.SCIENCE ||
                type == DepartmentType.ENGLISH || type == DepartmentType.MODERN_LANGUAGES ||
                type == DepartmentType.GEOGRAPHY || type == DepartmentType.HISTORY ||
                type == DepartmentType.COMPUTER_SCIENCE || type == DepartmentType.PHYSICAL_EDUCATION) {

                int staffCount = getStaffCountForDepartment(dept);
                System.out.println("  " + dept.getDepartmentName() + ": " + staffCount + " staff");
                academicCount++;
                academicStaff += staffCount;
            }
        }
        System.out.println("  Total Academic Departments: " + academicCount);
        System.out.println("  Total Academic Staff: " + academicStaff);
        System.out.println();

        // Arts and Performance
        System.out.println("ARTS & PERFORMANCE DEPARTMENTS:");
        System.out.println("----------------------------------------");
        int artsCount = 0;
        int artsStaff = 0;
        for (Department dept : departmentList) {
            DepartmentType type = dept.departmentType;
            if (type == DepartmentType.PERFORMING_ARTS || type == DepartmentType.DRAMA ||
                type == DepartmentType.MUSIC || type == DepartmentType.ART) {

                int staffCount = getStaffCountForDepartment(dept);
                System.out.println("  " + dept.getDepartmentName() + ": " + staffCount + " staff");
                artsCount++;
                artsStaff += staffCount;
            }
        }
        System.out.println("  Total Arts Departments: " + artsCount);
        System.out.println("  Total Arts Staff: " + artsStaff);
        System.out.println();

        // Student Support Services
        System.out.println("STUDENT SUPPORT SERVICES:");
        System.out.println("----------------------------------------");
        int supportCount = 0;
        int supportStaff = 0;
        for (Department dept : departmentList) {
            DepartmentType type = dept.departmentType;
            if (type == DepartmentType.LIBRARY || type == DepartmentType.GUIDANCE ||
                type == DepartmentType.STUDENT_SUPPORT || type == DepartmentType.NURSING) {

                int staffCount = getStaffCountForDepartment(dept);
                System.out.println("  " + dept.getDepartmentName() + ": " + staffCount + " staff");
                supportCount++;
                supportStaff += staffCount;
            }
        }
        System.out.println("  Total Support Departments: " + supportCount);
        System.out.println("  Total Support Staff: " + supportStaff);
        System.out.println();

        // Administrative and Operations
        System.out.println("ADMINISTRATIVE & OPERATIONS:");
        System.out.println("----------------------------------------");
        int adminCount = 0;
        int adminStaff = 0;
        for (Department dept : departmentList) {
            DepartmentType type = dept.departmentType;
            if (type == DepartmentType.SENIOR_MANAGEMENT || type == DepartmentType.FINANCE_ADMINISTRATION ||
                type == DepartmentType.RECEPTION || type == DepartmentType.LEGAL ||
                type == DepartmentType.FACILITIES || type == DepartmentType.SECURITY ||
                type == DepartmentType.IT_SUPPORT || type == DepartmentType.MECHANICS ||
                type == DepartmentType.CANTEEN) {

                int staffCount = getStaffCountForDepartment(dept);
                System.out.println("  " + dept.getDepartmentName() + ": " + staffCount + " staff");
                adminCount++;
                adminStaff += staffCount;
            }
        }
        System.out.println("  Total Admin/Operations Departments: " + adminCount);
        System.out.println("  Total Admin/Operations Staff: " + adminStaff);
        System.out.println();

        // Overall Summary
        System.out.println("========================================");
        System.out.println("OVERALL SUMMARY:");
        System.out.println("----------------------------------------");
        System.out.println("Total Departments: " + departmentList.size());
        System.out.println("Total Staff: " + employeeList.size());
        System.out.println("Average Staff per Department: " +
                          (departmentList.size() > 0 ? employeeList.size() / departmentList.size() : 0));

        // Find largest department
        Department largest = null;
        int maxStaff = 0;
        for (Department dept : departmentList) {
            int staffCount = getStaffCountForDepartment(dept);
            if (staffCount > maxStaff) {
                maxStaff = staffCount;
                largest = dept;
            }
        }
        if (largest != null) {
            System.out.println("Largest Department: " + largest.getDepartmentName() +
                              " (" + maxStaff + " staff)");
        }
        System.out.println("========================================\n");
    }

    private int getStaffCountForDepartment(Department dept) {
        int count = 0;
        for (Employee emp : employeeList) {
            if (emp.getDepartment() != null && emp.getDepartment().equals(dept)) {
                count++;
            }
        }
        return count;
    }
}
