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
     * Sorts by first name first, then last name if first names are equal.
     *
     * @param employees Array of Employee objects to sort
     * @return Sorted array of Employee objects
     */
    public static Employee[] mergeSort(Employee[] employees) {
        if (employees == null || employees.length == 0) {
            return new Employee[0];
        }

        if (employees.length <= 1) {
            return employees;
        }

        Employee[] sortedArray = new Employee[employees.length];

        for (int i = 0; i < employees.length; i++) {
            sortedArray[i] = employees[i];
        }

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
        if (left >= right) {
            return;
        }

        // Using left + (right - left) / 2 to avoid integer overflow
        int middle = left + (right - left) / 2;

        // RECURSIVE STEP 1: Sort the left half
        mergeSortRecursive(employees, left, middle);

        // RECURSIVE STEP 2: Sort the right half
        mergeSortRecursive(employees, middle + 1, right);

        // MERGE STEP: Combine the two sorted halves
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
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        Employee[] leftArray = new Employee[leftSize];
        Employee[] rightArray = new Employee[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = employees[left + i];
        }

        for (int j = 0; j < rightSize; j++) {
            rightArray[j] = employees[middle + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < leftSize && j < rightSize) {
            int comparison = compareEmployees(leftArray[i], rightArray[j]);

            if (comparison <= 0) {
                employees[k] = leftArray[i];
                i++;
            } else {
                employees[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            employees[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            employees[k] = rightArray[j];
            j++;
            k++;
        }
    }

    /**
     * Compares two employees by first name, then last name.
     * Uses case-insensitive comparison.
     *
     * @param emp1 First employee
     * @param emp2 Second employee
     * @return Negative if emp1 < emp2, Positive if emp1 > emp2, Zero if equal
     */
    private static int compareEmployees(Employee emp1, Employee emp2) {
        if (emp1 == null && emp2 == null) {
            return 0;
        }
        if (emp1 == null) {
            return 1;
        }
        if (emp2 == null) {
            return -1;
        }

        // Extract and normalize first names for comparison
        String firstName1 = emp1.getFirstName() != null ? emp1.getFirstName().toLowerCase() : "";
        String firstName2 = emp2.getFirstName() != null ? emp2.getFirstName().toLowerCase() : "";

        // Compare first names alphabetically
        int firstNameComparison = firstName1.compareTo(firstName2);

        if (firstNameComparison != 0) {
            return firstNameComparison;
        }

        // First names are the same, so compare last names
        String lastName1 = emp1.getLastName() != null ? emp1.getLastName().toLowerCase() : "";
        String lastName2 = emp2.getLastName() != null ? emp2.getLastName().toLowerCase() : "";

        return lastName1.compareTo(lastName2);
    }

    /**
     * Sorts employees and displays the first N results.
     *
     * @param employees    Array of employees to sort
     * @param displayCount Number of employees to display
     */
    public static void sortAndDisplayFirst(Employee[] employees, int displayCount) {
        if (employees == null || employees.length == 0) {
            System.out.println("No employees to sort.");
            return;
        }

        Employee[] sortedEmployees = mergeSort(employees);

        int randomCount = 0;
        for (Employee emp : sortedEmployees) {
            if (emp.isRandomlyGenerated()) {
                randomCount++;
            }
        }

        System.out.println("\n========================================");
        System.out.println("SORTED EMPLOYEE LIST (First " + displayCount + ")");
        System.out.println("Sorted by First Name");
        if (randomCount > 0) {
            System.out.println("Total Randomly Generated: " + randomCount);
        }
        System.out.println("========================================");

        int countToDisplay = Math.min(displayCount, sortedEmployees.length);

        for (int i = 0; i < countToDisplay; i++) {
            Employee emp = sortedEmployees[i];
            String randomTag = emp.isRandomlyGenerated() ? " [RANDOM]" : "";

            System.out.println((i + 1) + ". " + emp.getFullName() +
                    " - " + emp.getJobTitle() +
                    " (" + (emp.getDepartment() != null ? emp.getDepartment().getDepartmentName() : "No Dept") + ")" +
                    randomTag);
        }

        System.out.println("========================================");
        System.out.println("Total employees sorted: " + sortedEmployees.length);
        System.out.println("========================================\n");
    }
}
