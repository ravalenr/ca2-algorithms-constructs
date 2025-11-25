package CA_2;

import java.util.ArrayList;

/**
 * SortingAlgorithms class provides sorting functionality for employee records.
 * This class implements Binary Tree Sort, a recursive sorting algorithm that
 * uses
 * a binary search tree structure to sort elements.
 *
 * Design Decision: Binary Tree Sort was chosen because it naturally integrates
 * with
 * the binary tree hierarchy display requirement and provides O(n log n) average
 * performance.
 * The recursive element of BST insertion makes it a great solution for sorting
 * employee data.
 *
 * Algorithm Justification:
 * - Binary Tree Sort uses recursive insertion into a BST
 * - Average case: O(n log n), which is efficient for the data insertion size
 * - Worst case: O(n^2) if the tree becomes unbalanced (mitigated by random
 * data)
 * - More intuitive for school hierarchy representation than other algorithms
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class SortingAlgorithms {

    /**
     * Inner class representing a node in the binary search tree
     * Each node contains an employee and references to left and right children
     */
    private static class TreeNode {
        Employee data;
        TreeNode left;
        TreeNode right;

        /**
         * Constructor for TreeNode
         * 
         * @param employee The employee data to store in this node
         */
        public TreeNode(Employee employee) {
            this.data = employee;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Sorts an array of employees using Binary Tree Sort algorithm
     * This is the main public method that users call to sort employee data
     *
     * Process:
     * 1. Build a Binary Search Tree by inserting all employees
     * 2. Perform in-order traversal to get sorted order
     * 3. Return sorted array
     *
     * @param employees Array of Employee objects to sort
     * @return Sorted array of Employee objects (sorted by last name, then first
     *         name)
     */
    public static Employee[] binaryTreeSort(Employee[] employees) {
        // Handle null or empty array
        if (employees == null || employees.length == 0) {
            return new Employee[0];
        }

        // Build the binary search tree
        TreeNode root = null;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                root = insertRecursive(root, employees[i]);
            }
        }

        // Perform in-order traversal to get sorted array
        ArrayList<Employee> sortedList = new ArrayList<>();
        inOrderTraversal(root, sortedList);

        // Convert ArrayList back to array
        Employee[] sortedArray = new Employee[sortedList.size()];
        for (int i = 0; i < sortedList.size(); i++) {
            sortedArray[i] = sortedList.get(i);
        }

        return sortedArray;
    }

    /**
     * Recursively inserts an employee into the binary search tree
     * This is the core recursive method that builds the BST
     *
     * Comparison logic: Compares by last name first, then by first name if last
     * names are equal
     *
     * @param node     Current node in the tree (root of current subtree)
     * @param employee Employee to insert
     * @return The node after insertion (maintains tree structure)
     */
    private static TreeNode insertRecursive(TreeNode node, Employee employee) {
        // Base case: found the position to insert
        if (node == null) {
            return new TreeNode(employee);
        }

        // Compare employee names to determine left or right insertion
        int comparison = compareEmployees(employee, node.data);

        if (comparison < 0) {
            // Employee comes before current node - go left
            node.left = insertRecursive(node.left, employee);
        } else {
            // Employee comes after or equals current node - go right
            node.right = insertRecursive(node.right, employee);
        }

        return node;
    }

    /**
     * Performs in-order traversal of the binary search tree
     * In-order traversal of a BST yields elements in sorted order
     *
     * Traversal order: Left subtree -> Current node -> Right subtree
     * This recursive approach ensures all elements are visited in sorted order
     *
     * @param node   Current node being visited
     * @param result ArrayList to store the sorted employees
     */
    private static void inOrderTraversal(TreeNode node, ArrayList<Employee> result) {
        if (node == null) {
            return;
        }

        // Recursively traverse left subtree
        inOrderTraversal(node.left, result);

        // Visit current node (add to result)
        result.add(node.data);

        // Recursively traverse right subtree
        inOrderTraversal(node.right, result);
    }

    /**
     * Compares two employees for sorting purposes
     * Primary sort: Last name (alphabetically)
     * Secondary sort: First name (if last names are equal)
     *
     * @param emp1 First employee to compare
     * @param emp2 Second employee to compare
     * @return Negative if emp1 < emp2, Positive if emp1 > emp2, Zero if equal
     */
    private static int compareEmployees(Employee emp1, Employee emp2) {
        // Handle null cases
        if (emp1 == null && emp2 == null) {
            return 0;
        }
        if (emp1 == null) {
            return 1;
        }
        if (emp2 == null) {
            return -1;
        }

        // Compare last names first (case-insensitive)
        String lastName1 = emp1.getLastName() != null ? emp1.getLastName().toLowerCase() : "";
        String lastName2 = emp2.getLastName() != null ? emp2.getLastName().toLowerCase() : "";

        int lastNameComparison = lastName1.compareTo(lastName2);

        // If last names are different, return the comparison result
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }

        // Last names are the same, compare first names (case-insensitive)
        String firstName1 = emp1.getFirstName() != null ? emp1.getFirstName().toLowerCase() : "";
        String firstName2 = emp2.getFirstName() != null ? emp2.getFirstName().toLowerCase() : "";

        return firstName1.compareTo(firstName2);
    }

    /**
     * Sorts and displays the first N employees from the sorted array
     * This method is specifically designed for the assignment requirement
     * to display the first 20 sorted employee names
     *
     * @param employees    Array of employees to sort
     * @param displayCount Number of employees to display (e.g., 20)
     */
    public static void sortAndDisplayFirst(Employee[] employees, int displayCount) {
        if (employees == null || employees.length == 0) {
            System.out.println("No employees to sort.");
            return;
        }

        // Sort the employees
        Employee[] sortedEmployees = binaryTreeSort(employees);

        // Display header
        System.out.println("\n========================================");
        System.out.println("SORTED EMPLOYEE LIST (First " + displayCount + ")");
        System.out.println("========================================");

        // Determine how many to display
        int countToDisplay = Math.min(displayCount, sortedEmployees.length);

        // Display the sorted employees
        for (int i = 0; i < countToDisplay; i++) {
            Employee emp = sortedEmployees[i];
            System.out.println((i + 1) + ". " + emp.getFullName() +
                    " - " + emp.getJobTitle() +
                    " (" + (emp.getDepartment() != null ? emp.getDepartment().getDepartmentName() : "No Dept") + ")");
        }

        System.out.println("========================================");
        System.out.println("Total employees sorted: " + sortedEmployees.length);
        System.out.println("========================================\n");
    }

    /**
     * Alternative sorting method that returns sorted list as ArrayList
     * Useful for operations that need a List instead of an array
     *
     * @param employees Array of employees to sort
     * @return ArrayList of sorted employees
     */
    public static ArrayList<Employee> binaryTreeSortToList(Employee[] employees) {
        Employee[] sortedArray = binaryTreeSort(employees);
        ArrayList<Employee> sortedList = new ArrayList<>();

        for (int i = 0; i < sortedArray.length; i++) {
            sortedList.add(sortedArray[i]);
        }

        return sortedList;
    }

    /**
     * Verifies if an array is sorted correctly
     * Useful for testing and validation
     *
     * @param employees Array to check
     * @return true if array is sorted, false otherwise
     */
    public static boolean isSorted(Employee[] employees) {
        if (employees == null || employees.length <= 1) {
            return true;
        }

        for (int i = 0; i < employees.length - 1; i++) {
            if (compareEmployees(employees[i], employees[i + 1]) > 0) {
                return false;
            }
        }

        return true;
    }
}
