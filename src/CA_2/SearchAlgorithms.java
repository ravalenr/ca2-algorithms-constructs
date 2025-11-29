package CA_2;

/**
 * SearchAlgorithms class provides searching functionality for employee records.
 * Uses linear search with partial name matching for flexible searching.
 *
 * Why Linear Search?
 * - Allows partial matching (search "buff" finds "Buffy Summers")
 * - Case-insensitive for easier use
 * - Doesn't need sorted data
 * - Fast enough for typical school sizes (50-500 employees)
 * - O(n) time complexity, but better user experience than exact-match binary search
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class SearchAlgorithms {

    /**
     * Searches for employees matching the search term (partial matching).
     * Searches first name, last name, and full name. Case-insensitive.
     * Returns all matching employees.
     *
     * @param employees  Array of employees to search
     * @param searchName Name or partial name to search for
     * @return Array of matching employees (empty if none found)
     */
    public static Employee[] binarySearch(Employee[] employees, String searchName) {
        // Input validation
        if (employees == null || employees.length == 0 || searchName == null || searchName.trim().isEmpty()) {
            return new Employee[0];
        }

        // Normalize search term to lowercase
        String searchTerm = searchName.trim().toLowerCase();

        // First pass: count matches
        int matchCount = 0;
        for (int i = 0; i < employees.length; i++) {
            Employee emp = employees[i];

            if (emp == null) {
                continue;
            }

            // Get first and last name (null-safe)
            String firstName = emp.getFirstName() != null ? emp.getFirstName().toLowerCase() : "";
            String lastName = emp.getLastName() != null ? emp.getLastName().toLowerCase() : "";
            String fullName = (firstName + " " + lastName).trim();

            // Check if search term is contained in first name, last name, or full name
            if (firstName.contains(searchTerm) ||
                lastName.contains(searchTerm) ||
                fullName.contains(searchTerm)) {
                matchCount++;
            }
        }

        // Create result array
        Employee[] matches = new Employee[matchCount];
        int index = 0;

        // Second pass: collect matches
        for (int i = 0; i < employees.length; i++) {
            Employee emp = employees[i];

            if (emp == null) {
                continue;
            }

            // Get first and last name (null-safe)
            String firstName = emp.getFirstName() != null ? emp.getFirstName().toLowerCase() : "";
            String lastName = emp.getLastName() != null ? emp.getLastName().toLowerCase() : "";
            String fullName = (firstName + " " + lastName).trim();

            // Check if search term is contained in first name, last name, or full name
            if (firstName.contains(searchTerm) ||
                lastName.contains(searchTerm) ||
                fullName.contains(searchTerm)) {
                matches[index++] = emp;
            }
        }

        return matches;
    }

    /**
     * Searches for employees and displays results.
     *
     * @param employees  Array of employees to search
     * @param searchName Name to search for
     */
    public static void searchAndDisplay(Employee[] employees, String searchName) {
        System.out.println("\n========================================");
        System.out.println("EMPLOYEE SEARCH");
        System.out.println("========================================");
        System.out.println("Searching for: " + searchName);
        System.out.println("----------------------------------------");

        Employee[] results = binarySearch(employees, searchName);

        if (results.length == 0) {
            System.out.println("No employees found matching '" + searchName + "'");
        } else if (results.length == 1) {
            // Single result - display full details
            Employee result = results[0];
            System.out.println("EMPLOYEE FOUND!");
            System.out.println("----------------------------------------");
            System.out.println("Name: " + result.getFullName());
            System.out.println("Employee ID: " + result.getEmployeeId());
            System.out.println("Job Title: " + result.getJobTitle());
            System.out.println("Email: " + result.getEmail());
            System.out.println("Salary: " + result.getSalary());

            if (result.getManager() != null) {
                System.out.println("Manager: " + result.getManager().getFullName());
                System.out.println("Manager Type: " + result.getManager().getManagerTypeString());
            } else {
                System.out.println("Manager: None");
                System.out.println("Manager Type: N/A");
            }

            if (result.getDepartment() != null) {
                System.out.println("Department: " + result.getDepartment().getDepartmentName());
                System.out.println("Department Type: " + result.getDepartment().getDepartmentTypeString());
            } else {
                System.out.println("Department: Not assigned");
            }
        } else {
            // Multiple results - display summary list
            System.out.println("FOUND " + results.length + " MATCHING EMPLOYEES:");
            System.out.println("----------------------------------------");
            for (int i = 0; i < results.length; i++) {
                Employee emp = results[i];
                String deptName = emp.getDepartment() != null ? emp.getDepartment().getDepartmentName() : "No Dept";
                System.out.println((i + 1) + ". " + emp.getFullName() +
                                 " - " + emp.getJobTitle() +
                                 " (" + deptName + ")");
            }
            System.out.println("----------------------------------------");
            System.out.println("Tip: To get an specific employee, please enter the full name of the employee you wish to search.");
        }

        System.out.println("========================================\n");
    }

    /**
     * Searches for employees by partial name match.
     *
     * @param employees  Array of employees
     * @param searchTerm Search term to match
     * @return Array of matching employees
     */
    public static Employee[] partialNameSearch(Employee[] employees, String searchTerm) {
        if (employees == null || employees.length == 0 || searchTerm == null || searchTerm.trim().isEmpty()) {
            return new Employee[0];
        }

        String normalizedSearchTerm = searchTerm.trim().toLowerCase();

        // Count matches first
        int matchCount = 0;
        for (int i = 0; i < employees.length; i++) {
            String fullName = getFullNameNormalized(employees[i]);
            if (fullName.contains(normalizedSearchTerm)) {
                matchCount++;
            }
        }

        // Create result array
        Employee[] matches = new Employee[matchCount];
        int index = 0;

        // Fill result array
        for (int i = 0; i < employees.length; i++) {
            String fullName = getFullNameNormalized(employees[i]);
            if (fullName.contains(normalizedSearchTerm)) {
                matches[index] = employees[i];
                index++;
            }
        }

        return matches;
    }

    /**
     * Searches for employees by department.
     *
     * @param employees      Array of employees
     * @param departmentName Department name to search for
     * @return Array of employees in that department
     */
    public static Employee[] searchByDepartment(Employee[] employees, String departmentName) {
        if (employees == null || employees.length == 0 || departmentName == null || departmentName.trim().isEmpty()) {
            return new Employee[0];
        }

        String normalizedDeptName = departmentName.trim().toLowerCase();

        // Count matches
        int matchCount = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getDepartment() != null) {
                String empDeptName = employees[i].getDepartment().getDepartmentName().toLowerCase();
                if (empDeptName.equals(normalizedDeptName)) {
                    matchCount++;
                }
            }
        }

        // Create result array
        Employee[] matches = new Employee[matchCount];
        int index = 0;

        // Fill result array
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getDepartment() != null) {
                String empDeptName = employees[i].getDepartment().getDepartmentName().toLowerCase();
                if (empDeptName.equals(normalizedDeptName)) {
                    matches[index] = employees[i];
                    index++;
                }
            }
        }

        return matches;
    }

    /**
     * Searches for employees by manager type.
     *
     * @param employees       Array of employees
     * @param managerTypeName Manager type to search for
     * @return Array of employees with that manager type
     */
    public static Employee[] searchByManagerType(Employee[] employees, String managerTypeName) {
        if (employees == null || employees.length == 0 || managerTypeName == null || managerTypeName.trim().isEmpty()) {
            return new Employee[0];
        }

        String normalizedManagerType = managerTypeName.trim().toLowerCase();

        // Count matches
        int matchCount = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getManager() != null) {
                String empManagerType = employees[i].getManager().getManagerTypeString().toLowerCase();
                if (empManagerType.equals(normalizedManagerType)) {
                    matchCount++;
                }
            }
        }

        // Create result array
        Employee[] matches = new Employee[matchCount];
        int index = 0;

        // Fill result array
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getManager() != null) {
                String empManagerType = employees[i].getManager().getManagerTypeString().toLowerCase();
                if (empManagerType.equals(normalizedManagerType)) {
                    matches[index] = employees[i];
                    index++;
                }
            }
        }

        return matches;
    }

    /**
     * Helper method to get normalized full name.
     *
     * @param employee Employee object
     * @return Full name in lowercase
     */
    private static String getFullNameNormalized(Employee employee) {
        if (employee == null) {
            return "";
        }

        String firstName = employee.getFirstName() != null ? employee.getFirstName() : "";
        String lastName = employee.getLastName() != null ? employee.getLastName() : "";

        return (firstName + " " + lastName).trim().toLowerCase();
    }
}
