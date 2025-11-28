package CA_2;

/**
 * SearchAlgorithms class provides searching functionality for employee records.
 * This class implements flexible search with partial name matching for user-friendly lookup.
 *
 * Design Decision: Linear search with partial matching was chosen to provide
 * a better user experience. Users can search by partial names (first name, last name,
 * or any part of the full name) without needing exact matches.
 *
 * Algorithm Justification:
 * - Linear search with contains() matching: O(n) time complexity
 * - Case-insensitive matching for user convenience
 * - No requirement for sorted data
 * - Flexible partial matching (search "buff" finds "Buffy Summers")
 * - Fast enough for typical school staff sizes (50-500 employees)
 * - <1 second search time for datasets under 1000 employees
 *
 * Trade-off: We sacrifice the O(log n) speed of binary search for the flexibility
 * of partial matching. For a school management system with typically 50-500 staff,
 * this is an acceptable trade-off for much better usability.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class SearchAlgorithms {

    /**
     * Searches for an employee by name using flexible partial matching
     * This is the main public method for searching employees
     *
     * Search behavior:
     * - Searches both first name and last name
     * - Case-insensitive matching
     * - Partial matching (contains logic)
     * - Returns first match found
     *
     * Examples:
     * - "Fisher" → matches anyone with "Fisher" in first or last name
     * - "Isla" → matches "Isla Fisher"
     * - "buffy" → matches "Buffy Summers"
     *
     * @param employees  Array of Employee objects (does not need to be sorted)
     * @param searchName Name or partial name to search for (case-insensitive)
     * @return Employee object if found, null if not found
     */
    public static Employee binarySearch(Employee[] employees, String searchName) {
        // Input validation
        if (employees == null || employees.length == 0 || searchName == null || searchName.trim().isEmpty()) {
            return null;
        }

        // Normalize search term to lowercase
        String searchTerm = searchName.trim().toLowerCase();

        // Linear search with contains matching
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
                return emp;
            }
        }

        // Not found
        return null;
    }

    /**
     * Parses the user's search input and normalizes it to match array sort order.
     * Handles multiple input formats:
     * - "Fisher" → "fisher" (last name only - will match partial)
     * - "Isla Fisher" → "fisher isla" (rearranged to lastName firstName)
     * - "Fisher Isla" → "fisher isla" (already in correct order)
     *
     * @param searchName The raw search input from user
     * @return Normalized search string in "lastName firstName" format
     */
    private static String parseAndNormalizeSearchName(String searchName) {
        if (searchName == null || searchName.trim().isEmpty()) {
            return "";
        }

        // Trim and convert to lowercase
        String normalized = searchName.trim().toLowerCase();

        // Split by whitespace
        String[] parts = normalized.split("\\s+");

        if (parts.length == 1) {
            // Single word - could be first name or last name
            // Return as-is for partial matching
            return parts[0];
        } else if (parts.length == 2) {
            // Two words - assume "firstName lastName" and rearrange to "lastName firstName"
            // This matches how the array is sorted
            String possibleFirstName = parts[0];
            String possibleLastName = parts[1];

            // Return in "lastName firstName" format to match sort order
            return possibleLastName + " " + possibleFirstName;
        } else {
            // More than 2 words - just join them
            return normalized;
        }
    }

    /**
     * Recursive implementation of Binary Search
     * Divides the search space in half with each recursive call
     *
     * Algorithm:
     * 1. Find the middle element
     * 2. Compare search name with middle element
     * 3. If match found, return the employee
     * 4. If search name < middle, search left half (recursive)
     * 5. If search name > middle, search right half (recursive)
     * 6. If search space exhausted, return null (not found)
     *
     * @param employees  Sorted array of employees
     * @param searchName Normalized search name (lowercase)
     * @param left       Left boundary of current search space
     * @param right      Right boundary of current search space
     * @return Employee if found, null otherwise
     */
    private static Employee binarySearchRecursive(Employee[] employees, String searchName, int left, int right) {
        // Base case: search space is exhausted
        if (left > right) {
            return null;
        }

        // Find middle index (avoid integer overflow)
        int mid = left + (right - left) / 2;

        // Get middle employee's full name (normalized for comparison)
        Employee midEmployee = employees[mid];
        String midName = getFullNameNormalized(midEmployee);

        // Compare search name with middle element
        int comparison = searchName.compareTo(midName);

        if (comparison == 0) {
            // Found exact match
            return midEmployee;
        } else if (comparison < 0) {
            // Search name comes before middle element - search left half
            return binarySearchRecursive(employees, searchName, left, mid - 1);
        } else {
            // Search name comes after middle element - search right half
            return binarySearchRecursive(employees, searchName, mid + 1, right);
        }
    }

    /**
     * Searches for an employee and displays their complete information
     * This method combines search with formatted output display
     *
     * @param employees  Sorted array of employees
     * @param searchName Name to search for
     */
    public static void searchAndDisplay(Employee[] employees, String searchName) {
        System.out.println("\n========================================");
        System.out.println("EMPLOYEE SEARCH");
        System.out.println("========================================");
        System.out.println("Searching for: " + searchName);
        System.out.println("----------------------------------------");

        Employee result = binarySearch(employees, searchName);

        if (result == null) {
            System.out.println("Employee '" + searchName + "' not found in the system.");
        } else {
            System.out.println("EMPLOYEE FOUND!");
            System.out.println("----------------------------------------");
            System.out.println("Name: " + result.getFullName());
            System.out.println("Employee ID: " + result.getEmployeeId());
            System.out.println("Job Title: " + result.getJobTitle());
            System.out.println("Email: " + result.getEmail());
            System.out.println("Salary: " + result.getSalary());

            // Display Manager Type
            if (result.getManager() != null) {
                System.out.println("Manager: " + result.getManager().getFullName());
                System.out.println("Manager Type: " + result.getManager().getManagerTypeString());
            } else {
                System.out.println("Manager: None");
                System.out.println("Manager Type: N/A");
            }

            // Display Department
            if (result.getDepartment() != null) {
                System.out.println("Department: " + result.getDepartment().getDepartmentName());
                System.out.println("Department Type: " + result.getDepartment().getDepartmentTypeString());
            } else {
                System.out.println("Department: Not assigned");
            }
        }

        System.out.println("========================================\n");
    }

    /**
     * Searches for multiple employees whose names contain the search term
     * This is a partial match search (linear search required)
     * Useful for finding employees when exact name is not known
     *
     * Note: This uses linear search since partial matching cannot use binary search
     *
     * @param employees  Array of employees (does not need to be sorted)
     * @param searchTerm Search term to match (case-insensitive)
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
     * Searches for employees by department
     * Returns all employees belonging to a specific department
     *
     * @param employees      Array of employees
     * @param departmentName Department name to search for (case-insensitive)
     * @return Array of employees in the specified department
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
     * Searches for employees by manager type
     * Returns all employees with a specific manager type
     *
     * @param employees       Array of employees
     * @param managerTypeName Manager type to search for (case-insensitive)
     * @return Array of employees with the specified manager type
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
     * Helper method to get normalized full name from an employee
     * Returns full name in LAST NAME, FIRST NAME format (matching sort order)
     * This is critical for binary search to work correctly!
     *
     * @param employee Employee object
     * @return Normalized full name as "lastname firstname" (lowercase, trimmed)
     */
    private static String getFullNameNormalized(Employee employee) {
        if (employee == null) {
            return "";
        }

        String firstName = employee.getFirstName() != null ? employee.getFirstName() : "";
        String lastName = employee.getLastName() != null ? employee.getLastName() : "";

        // CRITICAL: Return "lastName firstName" to match the sort order!
        // The sort compares by last name first, then first name
        return (lastName + " " + firstName).trim().toLowerCase();
    }

    /**
     * Checks if an employee array is sorted (required for binary search)
     * Useful for validation before performing binary search
     *
     * @param employees Array to check
     * @return true if sorted, false otherwise
     */
    public static boolean isArraySorted(Employee[] employees) {
        if (employees == null || employees.length <= 1) {
            return true;
        }

        for (int i = 0; i < employees.length - 1; i++) {
            String name1 = getFullNameNormalized(employees[i]);
            String name2 = getFullNameNormalized(employees[i + 1]);

            if (name1.compareTo(name2) > 0) {
                return false;
            }
        }

        return true;
    }
}
