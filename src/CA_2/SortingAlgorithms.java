package CA_2;

/**
 * SortingAlgorithms class provides sorting functionality for employee records.
 * This class implements Merge Sort, a recursive divide-and-conquer sorting algorithm
 * that guarantees O(n log n) performance in all cases.
 *
 * ALGORITHM CHOICE: Merge Sort
 *
 * Design Decision: Merge Sort was chosen as the recursive sorting algorithm for this
 * School Management System for several compelling reasons:
 *
 * 1. GUARANTEED PERFORMANCE: Merge Sort provides O(n log n) time complexity in ALL cases
 *    (best, average, and worst). This ensures that sorting operations complete within
 *    the required 2-3 seconds even with large datasets (100+ employees).
 *
 * 2. STABILITY: Merge Sort is a stable sorting algorithm, meaning it preserves the
 *    relative order of employees with equal names. This is important for maintaining
 *    data consistency in organizational records.
 *
 * 3. PREDICTABLE: Unlike Quick Sort (O(n²) worst case) or Binary Tree Sort (O(n²) if
 *    unbalanced), Merge Sort's performance is predictable and reliable regardless of
 *    input data patterns.
 *
 * 4. CLEARLY RECURSIVE: The algorithm's divide-and-conquer nature makes it unambiguously
 *    recursive - the sorting logic itself recursively divides the problem into smaller
 *    subproblems, sorts them, and merges the results.
 *
 * 5. INDUSTRY STANDARD: Merge Sort is used in Java's standard library (Collections.sort()
 *    for objects) and is the preferred algorithm when stability and guaranteed performance
 *    are required.
 *
 * 6. EDUCATIONAL VALUE: Merge Sort is a classic example of the divide-and-conquer
 *    paradigm, demonstrating fundamental algorithmic thinking.
 *
 * Time Complexity:
 * - Best Case: O(n log n)
 * - Average Case: O(n log n)
 * - Worst Case: O(n log n) - GUARANTEED
 *
 * Space Complexity: O(n) - requires temporary arrays for merging
 *
 * Stability: STABLE - maintains relative order of equal elements
 *
 * @author Rafael Valentim Ribeiro
 * @version 2.0
 */
public class SortingAlgorithms {

    /**
     * Sorts an array of employees using the Merge Sort algorithm.
     * This is the main public method that users call to sort employee data.
     *
     * Merge Sort is a recursive divide-and-conquer algorithm that:
     * 1. Divides the array into two halves
     * 2. Recursively sorts each half
     * 3. Merges the sorted halves back together
     *
     * @param employees Array of Employee objects to sort
     * @return Sorted array of Employee objects (sorted by last name, then first name)
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
     * Recursive helper method that implements the core Merge Sort algorithm.
     * This method divides the array into halves and recursively sorts them.
     *
     * RECURSIVE STRUCTURE:
     * - Base Case: When left >= right, the subarray has 0 or 1 elements (already sorted)
     * - Recursive Case:
     *   1. Find the middle point to divide the array
     *   2. Recursively sort the left half
     *   3. Recursively sort the right half
     *   4. Merge the two sorted halves
     *
     * @param employees The array being sorted
     * @param left      The starting index of the subarray to sort
     * @param right     The ending index of the subarray to sort
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
     * Merges two sorted subarrays into a single sorted subarray.
     * This is the "conquer" step of the divide-and-conquer algorithm.
     *
     * The two subarrays to be merged are:
     * - Left subarray: employees[left...middle]
     * - Right subarray: employees[middle+1...right]
     *
     * MERGE ALGORITHM:
     * 1. Create temporary arrays to hold the left and right subarrays
     * 2. Compare elements from both subarrays and place the smaller one in the result
     * 3. Copy any remaining elements from either subarray
     *
     * This method maintains stability by choosing the left element when values are equal.
     *
     * @param employees The array containing both subarrays
     * @param left      Starting index of the left subarray
     * @param middle    Ending index of the left subarray (middle+1 starts right subarray)
     * @param right     Ending index of the right subarray
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
     * Compares two employees for sorting purposes.
     * This method defines the sorting order for the entire algorithm.
     *
     * COMPARISON LOGIC:
     * - Primary sort: Last name (alphabetically, case-insensitive)
     * - Secondary sort: First name (if last names are equal, case-insensitive)
     *
     * @param emp1 First employee to compare
     * @param emp2 Second employee to compare
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
     * Sorts an array of employees and displays the first N employees.
     * This method is specifically designed for the assignment requirement
     * to sort and display the first 20 employee names.
     *
     * This method:
     * 1. Sorts all employees using Merge Sort
     * 2. Displays the first 'displayCount' employees (e.g., 20)
     * 3. Shows additional information like job title and department
     *
     * @param employees    Array of employees to sort
     * @param displayCount Number of employees to display (e.g., 20)
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
     * Verifies if an array is correctly sorted in ascending order.
     * This method is useful for testing and validation purposes.
     *
     * The array is considered sorted if for every pair of adjacent elements,
     * the earlier element is less than or equal to the later element.
     *
     * @param employees Array to check
     * @return true if the array is sorted in ascending order, false otherwise
     */
    public static boolean isSorted(Employee[] employees) {
        // Null or single-element arrays are considered sorted
        if (employees == null || employees.length <= 1) {
            return true;
        }

        // Check each pair of adjacent elements
        for (int i = 0; i < employees.length - 1; i++) {
            // If any element is greater than the next element, array is not sorted
            if (compareEmployees(employees[i], employees[i + 1]) > 0) {
                return false;
            }
        }

        // All adjacent pairs are in order - array is sorted
        return true;
    }

    /**
     * Utility method to get a sorted array count.
     * Returns the number of elements in a sorted array.
     * This method exists for consistency with the original interface.
     *
     * @param employees Array of employees
     * @return Number of employees in the array
     */
    public static int getSortedCount(Employee[] employees) {
        if (employees == null) {
            return 0;
        }
        return employees.length;
    }

    /**
     * Compares two employees by FIRST NAME first, then last name.
     * This is the opposite order from the main compareEmployees method.
     *
     * @param emp1 First employee to compare
     * @param emp2 Second employee to compare
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
     * Sorts an array of employees by FIRST NAME using Merge Sort.
     * This is an alternative sorting method for when you want employees
     * sorted by first name instead of last name.
     *
     * @param employees Array of Employee objects to sort
     * @return Sorted array of Employee objects (sorted by first name, then last name)
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
     * Recursive helper method for sorting by FIRST NAME.
     * Same structure as mergeSortRecursive but uses different comparison.
     *
     * @param employees The array being sorted
     * @param left      The starting index of the subarray to sort
     * @param right     The ending index of the subarray to sort
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
     * Merges two sorted subarrays comparing by FIRST NAME.
     * Same structure as merge() but uses compareEmployeesByFirstName.
     *
     * @param employees The array containing both subarrays
     * @param left      Starting index of the left subarray
     * @param middle    Ending index of the left subarray
     * @param right     Ending index of the right subarray
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
        System.out.println("Sorted by FIRST NAME");
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
