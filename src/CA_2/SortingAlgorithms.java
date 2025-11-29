package CA_2;

/**
 * SortingAlgorithms class provides sorting functionality for employee records.
 * This class implements Merge Sort, a recursive divide-and-conquer algorithm.
 *
 * Why Merge Sort?
 * - Properly recursive (divides problem into smaller parts, solves them, then merges)
 * - O(n log n) time complexity guaranteed (unlike Quick Sort which can be O(n²))
 * - Stable sort (keeps equal elements in same order)
 * - Works well for employee data that needs reliable performance
 *
 * Time Complexity: O(n log n) in all cases
 * Space Complexity: O(n) - needs temporary arrays for merging
 *
 * @author Rafael Valentim Ribeiro
 * @version 2.0
 */
public class SortingAlgorithms {

    /**
     * Sorts an array of employees using Merge Sort.
     * Sorts by last name first, then first name if last names are equal.
     *
     * @param employees Array of Employee objects to sort
     * @return Sorted array of Employee objects
     */
    public static Employee[] mergeSort(Employee[] employees) {
        // Input validation - handle null or empty arrays
        if (employees == null || employees.length == 0) {
            return new Employee[0];
        }

        // Base case: arrays with 0 or 1 elements are already sorted
        if (employees.length <= 1) {
            return employees;
        }

        // Call the recursive merge sort helper method
        // We use indices to avoid unnecessary array copying
        Employee[] sortedArray = new Employee[employees.length];

        // Copy input array to avoid modifying the original
        for (int i = 0; i < employees.length; i++) {
            sortedArray[i] = employees[i];
        }

        // Perform the recursive merge sort
        mergeSortRecursive(sortedArray, 0, sortedArray.length - 1);

        return sortedArray;
    }

    /**
     * Recursive helper method for Merge Sort.
     * Divides array in half, recursively sorts each half, then merges them.
     *
     * @param employees The array being sorted
     * @param left      Starting index of subarray
     * @param right     Ending index of subarray
     */
    private static void mergeSortRecursive(Employee[] employees, int left, int right) {
        // Base case: if the subarray has 0 or 1 elements, it's already sorted
        if (left >= right) {
            return;
        }

        // Calculate the middle point to divide the array into two halves
        // Using left + (right - left) / 2 to avoid integer overflow
        int middle = left + (right - left) / 2;

        // RECURSIVE STEP 1: Sort the left half
        // This recursively divides and sorts the left portion [left...middle]
        mergeSortRecursive(employees, left, middle);

        // RECURSIVE STEP 2: Sort the right half
        // This recursively divides and sorts the right portion [middle+1...right]
        mergeSortRecursive(employees, middle + 1, right);

        // MERGE STEP: Combine the two sorted halves
        // After both halves are sorted, merge them into a single sorted array
        merge(employees, left, middle, right);
    }

    /**
     * Merges two sorted subarrays into one sorted subarray.
     * Left subarray: employees[left...middle]
     * Right subarray: employees[middle+1...right]
     *
     * @param employees The array containing both subarrays
     * @param left      Starting index of left subarray
     * @param middle    Ending index of left subarray
     * @param right     Ending index of right subarray
     */
    private static void merge(Employee[] employees, int left, int middle, int right) {
        // Calculate sizes of the two subarrays to be merged
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        // Create temporary arrays to hold the data
        Employee[] leftArray = new Employee[leftSize];
        Employee[] rightArray = new Employee[rightSize];

        // Copy data into temporary arrays
        // Left subarray: employees[left...middle]
        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = employees[left + i];
        }

        // Right subarray: employees[middle+1...right]
        for (int j = 0; j < rightSize; j++) {
            rightArray[j] = employees[middle + 1 + j];
        }

        // Merge the temporary arrays back into employees[left...right]

        // Initial indices for left, right, and merged subarrays
        int i = 0;        // Index for leftArray
        int j = 0;        // Index for rightArray
        int k = left;     // Index for merged array (employees)

        // Merge process: Compare elements from both arrays and place smaller one first
        while (i < leftSize && j < rightSize) {
            // Compare employees from left and right subarrays
            int comparison = compareEmployees(leftArray[i], rightArray[j]);

            if (comparison <= 0) {
                // Left element is smaller or equal - take from left array
                // Using <= maintains stability (preserves original order for equal elements)
                employees[k] = leftArray[i];
                i++;
            } else {
                // Right element is smaller - take from right array
                employees[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy any remaining elements from leftArray (if any)
        // This happens when right subarray is exhausted first
        while (i < leftSize) {
            employees[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy any remaining elements from rightArray (if any)
        // This happens when left subarray is exhausted first
        while (j < rightSize) {
            employees[k] = rightArray[j];
            j++;
            k++;
        }
    }

    /**
     * Compares two employees by last name, then first name.
     * Uses case-insensitive comparison.
     *
     * @param emp1 First employee
     * @param emp2 Second employee
     * @return Negative if emp1 < emp2, Positive if emp1 > emp2, Zero if equal
     */
    private static int compareEmployees(Employee emp1, Employee emp2) {
        // Handle null cases - null employees are considered "greater" (sorted last)
        if (emp1 == null && emp2 == null) {
            return 0;
        }
        if (emp1 == null) {
            return 1;  // emp1 is "greater" (comes after emp2)
        }
        if (emp2 == null) {
            return -1; // emp2 is "greater" (comes after emp1)
        }

        // Extract and normalize last names for comparison (convert to lowercase)
        String lastName1 = emp1.getLastName() != null ? emp1.getLastName().toLowerCase() : "";
        String lastName2 = emp2.getLastName() != null ? emp2.getLastName().toLowerCase() : "";

        // Compare last names alphabetically
        int lastNameComparison = lastName1.compareTo(lastName2);

        // If last names are different, return the comparison result
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }

        // Last names are the same, so compare first names (secondary sort)
        String firstName1 = emp1.getFirstName() != null ? emp1.getFirstName().toLowerCase() : "";
        String firstName2 = emp2.getFirstName() != null ? emp2.getFirstName().toLowerCase() : "";

        // Return the comparison of first names
        return firstName1.compareTo(firstName2);
    }

    /**
     * Sorts employees and displays the first N results.
     *
     * @param employees    Array of employees to sort
     * @param displayCount Number of employees to display
     */
    public static void sortAndDisplayFirst(Employee[] employees, int displayCount) {
        // Input validation
        if (employees == null || employees.length == 0) {
            System.out.println("No employees to sort.");
            return;
        }

        // Sort the employees using Merge Sort
        Employee[] sortedEmployees = mergeSort(employees);

        // Count randomly generated employees
        int randomCount = 0;
        for (Employee emp : sortedEmployees) {
            if (emp.isRandomlyGenerated()) {
                randomCount++;
            }
        }

        // Display header with formatting
        System.out.println("\n========================================");
        System.out.println("SORTED EMPLOYEE LIST (First " + displayCount + ")");
        if (randomCount > 0) {
            System.out.println("Total Randomly Generated: " + randomCount);
        }
        System.out.println("========================================");

        // Determine how many employees to display
        // Use Math.min to avoid IndexOutOfBoundsException if array is smaller than displayCount
        int countToDisplay = Math.min(displayCount, sortedEmployees.length);

        // Display the sorted employees with numbering
        for (int i = 0; i < countToDisplay; i++) {
            Employee emp = sortedEmployees[i];

            // Add [RANDOM] tag for randomly generated employees
            String randomTag = emp.isRandomlyGenerated() ? " [RANDOM]" : "";

            // Format: "1. John Smith - Teacher (Mathematics Department)"
            System.out.println((i + 1) + ". " + emp.getFullName() +
                    " - " + emp.getJobTitle() +
                    " (" + (emp.getDepartment() != null ? emp.getDepartment().getDepartmentName() : "No Dept") + ")" +
                    randomTag);
        }

        // Display footer with statistics
        System.out.println("========================================");
        System.out.println("Total employees sorted: " + sortedEmployees.length);
        System.out.println("========================================\n");
    }

    /**
     * Compares two employees by first name, then last name.
     *
     * @param emp1 First employee
     * @param emp2 Second employee
     * @return Negative if emp1 < emp2, Positive if emp1 > emp2, Zero if equal
     */
    private static int compareEmployeesByFirstName(Employee emp1, Employee emp2) {
        // Handle null cases - null employees are considered "greater" (sorted last)
        if (emp1 == null && emp2 == null) {
            return 0;
        }
        if (emp1 == null) {
            return 1;  // emp1 is "greater" (comes after emp2)
        }
        if (emp2 == null) {
            return -1; // emp2 is "greater" (comes after emp1)
        }

        // Extract and normalize first names for comparison (convert to lowercase)
        String firstName1 = emp1.getFirstName() != null ? emp1.getFirstName().toLowerCase() : "";
        String firstName2 = emp2.getFirstName() != null ? emp2.getFirstName().toLowerCase() : "";

        // Compare first names alphabetically
        int firstNameComparison = firstName1.compareTo(firstName2);

        // If first names are different, return the comparison result
        if (firstNameComparison != 0) {
            return firstNameComparison;
        }

        // First names are the same, so compare last names (secondary sort)
        String lastName1 = emp1.getLastName() != null ? emp1.getLastName().toLowerCase() : "";
        String lastName2 = emp2.getLastName() != null ? emp2.getLastName().toLowerCase() : "";

        // Return the comparison of last names
        return lastName1.compareTo(lastName2);
    }

    /**
     * Sorts employees by first name using Merge Sort.
     *
     * @param employees Array of employees to sort
     * @return Sorted array (by first name, then last name)
     */
    public static Employee[] mergeSortByFirstName(Employee[] employees) {
        // Input validation - handle null or empty arrays
        if (employees == null || employees.length == 0) {
            return new Employee[0];
        }

        // Base case: arrays with 0 or 1 elements are already sorted
        if (employees.length <= 1) {
            return employees;
        }

        // Copy input array to avoid modifying the original
        Employee[] sortedArray = new Employee[employees.length];
        for (int i = 0; i < employees.length; i++) {
            sortedArray[i] = employees[i];
        }

        // Perform the recursive merge sort (by first name)
        mergeSortRecursiveByFirstName(sortedArray, 0, sortedArray.length - 1);

        return sortedArray;
    }

    /**
     * Recursive helper for sorting by first name.
     *
     * @param employees The array being sorted
     * @param left      Starting index
     * @param right     Ending index
     */
    private static void mergeSortRecursiveByFirstName(Employee[] employees, int left, int right) {
        // Base case: if the subarray has 0 or 1 elements, it's already sorted
        if (left >= right) {
            return;
        }

        // Calculate the middle point to divide the array into two halves
        int middle = left + (right - left) / 2;

        // RECURSIVE STEP 1: Sort the left half
        mergeSortRecursiveByFirstName(employees, left, middle);

        // RECURSIVE STEP 2: Sort the right half
        mergeSortRecursiveByFirstName(employees, middle + 1, right);

        // MERGE STEP: Combine the two sorted halves
        mergeByFirstName(employees, left, middle, right);
    }

    /**
     * Merges two sorted subarrays (comparing by first name).
     *
     * @param employees The array containing both subarrays
     * @param left      Starting index
     * @param middle    Middle index
     * @param right     Ending index
     */
    private static void mergeByFirstName(Employee[] employees, int left, int middle, int right) {
        // Calculate sizes of the two subarrays to be merged
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        // Create temporary arrays to hold the data
        Employee[] leftArray = new Employee[leftSize];
        Employee[] rightArray = new Employee[rightSize];

        // Copy data into temporary arrays
        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = employees[left + i];
        }
        for (int j = 0; j < rightSize; j++) {
            rightArray[j] = employees[middle + 1 + j];
        }

        // Merge the temporary arrays back into employees[left...right]
        int i = 0;        // Index for leftArray
        int j = 0;        // Index for rightArray
        int k = left;     // Index for merged array (employees)

        // Merge process using first name comparison
        while (i < leftSize && j < rightSize) {
            // Compare employees by FIRST NAME
            int comparison = compareEmployeesByFirstName(leftArray[i], rightArray[j]);

            if (comparison <= 0) {
                employees[k] = leftArray[i];
                i++;
            } else {
                employees[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy any remaining elements from leftArray
        while (i < leftSize) {
            employees[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy any remaining elements from rightArray
        while (j < rightSize) {
            employees[k] = rightArray[j];
            j++;
            k++;
        }
    }

    /**
     * Sorts and displays the first N employees sorted by FIRST NAME.
     *
     * @param employees    Array of employees to sort
     * @param displayCount Number of employees to display
     */
    public static void sortByFirstNameAndDisplayFirst(Employee[] employees, int displayCount) {
        // Input validation
        if (employees == null || employees.length == 0) {
            System.out.println("No employees to sort.");
            return;
        }

        // Sort the employees by first name using Merge Sort
        Employee[] sortedEmployees = mergeSortByFirstName(employees);

        // Count randomly generated employees
        int randomCount = 0;
        for (Employee emp : sortedEmployees) {
            if (emp.isRandomlyGenerated()) {
                randomCount++;
            }
        }

        // Display header with formatting
        System.out.println("\n========================================");
        System.out.println("SORTED EMPLOYEE LIST (First " + displayCount + ")");
        System.out.println("Sorted by First Name");
        if (randomCount > 0) {
            System.out.println("Total Randomly Generated: " + randomCount);
        }
        System.out.println("========================================");

        // Determine how many employees to display
        int countToDisplay = Math.min(displayCount, sortedEmployees.length);

        // Display the sorted employees with numbering
        for (int i = 0; i < countToDisplay; i++) {
            Employee emp = sortedEmployees[i];

            // Add [RANDOM] tag for randomly generated employees
            String randomTag = emp.isRandomlyGenerated() ? " [RANDOM]" : "";

            System.out.println((i + 1) + ". " + emp.getFullName() +
                    " - " + emp.getJobTitle() +
                    " (" + (emp.getDepartment() != null ? emp.getDepartment().getDepartmentName() : "No Dept") + ")" +
                    randomTag);
        }

        // Display footer with statistics
        System.out.println("========================================");
        System.out.println("Total employees sorted: " + sortedEmployees.length);
        System.out.println("========================================\n");
    }
}
