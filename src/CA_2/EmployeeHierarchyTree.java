package CA_2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * EmployeeHierarchyTree implements a binary tree structure for employee hierarchy.
 * Uses level-order (breadth-first) insertion to build a balanced tree.
 * Each node can have at most two children (left filled first, then right).
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class EmployeeHierarchyTree {

    // Root node of the tree
    private BinaryTreeNode root;

    // Total number of nodes in the tree
    private int nodeCount;

    /**
     * Constructor initializes an empty tree.
     */
    public EmployeeHierarchyTree() {
        this.root = null;
        this.nodeCount = 0;
    }

    /**
     * Inserts an employee into the tree using level-order insertion.
     * This method ensures the tree remains balanced by filling left child first,
     * then right child, before moving to the next level.
     *
     * Algorithm:
     * 1. If tree is empty, create root node
     * 2. Otherwise, use a queue to traverse level by level
     * 3. Find the first node that doesn't have both children
     * 4. Insert as left child if available, otherwise as right child
     *
     * @param employee The employee to insert into the tree
     */
    public void insert(Employee employee) {
        // Validate input
        if (employee == null) {
            return;
        }

        // Create new node for the employee
        BinaryTreeNode newNode = new BinaryTreeNode(employee);

        // If tree is empty, set as root
        if (root == null) {
            root = newNode;
            nodeCount++;
            return;
        }

        // Use a queue for level-order traversal
        // Queue helps us visit nodes level by level from left to right
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        // Traverse the tree level by level
        while (!queue.isEmpty()) {
            // Get the front node from queue
            BinaryTreeNode current = queue.poll();

            // Check if left child is empty
            if (!current.hasLeft()) {
                // Insert as left child
                current.setLeft(newNode);
                nodeCount++;
                return;
            } else {
                // Left child exists, add it to queue for later processing
                queue.add(current.getLeft());
            }

            // Check if right child is empty
            if (!current.hasRight()) {
                // Insert as right child
                current.setRight(newNode);
                nodeCount++;
                return;
            } else {
                // Right child exists, add it to queue for later processing
                queue.add(current.getRight());
            }
        }
    }

    /**
     * Builds the tree from an ArrayList of employees.
     * Uses two-pass insertion to build proper hierarchy:
     * Pass 1: Insert managers by rank (Principal -> VP -> Dept Heads)
     * Pass 2: Insert regular employees
     * This ensures managers appear at top levels of tree.
     *
     * @param employees ArrayList of employees to insert into the tree
     */
    public void buildFromList(ArrayList<Employee> employees) {
        // Clear existing tree
        root = null;
        nodeCount = 0;

        if (employees == null) {
            return;
        }

        // PASS 1: Insert managers in hierarchical order
        // This makes sure top management appears at top of tree

        // Step 1: Insert Principal first (should be root)
        for (Employee emp : employees) {
            if (emp instanceof Principal) {
                insert(emp);
            }
        }

        // Step 2: Insert Vice Principals next (level 2)
        for (Employee emp : employees) {
            if (emp instanceof VicePrincipal) {
                insert(emp);
            }
        }

        // Step 3: Insert Department Heads (level 3+)
        for (Employee emp : employees) {
            if (emp instanceof DepartmentHead) {
                insert(emp);
            }
        }

        // PASS 2: Insert all regular employees (teachers, staff, etc)
        // These will fill in under the managers
        for (Employee emp : employees) {
            if (!(emp instanceof Manager)) {
                insert(emp);
            }
        }
    }

    /**
     * Performs level-order traversal and displays the tree hierarchy.
     * Shows each level of the tree on a separate line.
     * Displays employee information including name, manager type, and department.
     */
    public void displayLevelOrder() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        System.out.println("========================================");
        System.out.println("EMPLOYEE HIERARCHY - BINARY TREE");
        System.out.println("Level Order Traversal (Breadth-First)");
        System.out.println("========================================\n");

        // Use queue for level-order traversal
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        int currentLevel = 0;

        // Process tree level by level
        while (!queue.isEmpty()) {
            // Get number of nodes at current level
            int levelSize = queue.size();
            currentLevel++;

            System.out.println("Level " + currentLevel + ":");
            System.out.println("----------------------------------------");

            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                BinaryTreeNode current = queue.poll();

                if (current != null) {
                    Employee emp = current.getEmployee();

                    // Display employee information
                    System.out.print("  " + emp.getFullName());

                    // Add manager type if employee is a manager
                    if (emp instanceof Manager) {
                        Manager mgr = (Manager) emp;
                        System.out.print(" [" + mgr.getManagerTypeString() + "]");
                    }

                    // Add department information
                    if (emp.getDepartment() != null) {
                        System.out.print(" - " + emp.getDepartment().getDepartmentName());
                    }

                    System.out.println();

                    // Add children to queue for next level processing
                    if (current.hasLeft()) {
                        queue.add(current.getLeft());
                    }
                    if (current.hasRight()) {
                        queue.add(current.getRight());
                    }
                }
            }

            System.out.println();
        }
    }

    /**
     * Calculates and returns the height of the tree.
     * Height is defined as the number of edges on the longest path
     * from root to a leaf node.
     *
     * Uses recursive approach:
     * - Empty tree has height -1
     * - Tree with only root has height 0
     * - For other trees, height = 1 + max(left subtree height, right subtree height)
     *
     * @return The height of the tree
     */
    public int getHeight() {
        return calculateHeight(root);
    }

    /**
     * Helper method to recursively calculate height of a subtree.
     *
     * @param node The root node of the subtree
     * @return The height of the subtree
     */
    private int calculateHeight(BinaryTreeNode node) {
        // Base case: empty tree has height -1
        if (node == null) {
            return -1;
        }

        // Recursive case: height = 1 + max of left and right subtree heights
        int leftHeight = calculateHeight(node.getLeft());
        int rightHeight = calculateHeight(node.getRight());

        // Return the maximum height plus 1 for current node
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Gets the total number of nodes in the tree.
     *
     * @return The node count
     */
    public int getNodeCount() {
        return nodeCount;
    }

    /**
     * Checks if the tree is empty.
     *
     * @return true if tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Gets the root node of the tree.
     *
     * @return The root node or null if tree is empty
     */
    public BinaryTreeNode getRoot() {
        return root;
    }

    /**
     * Displays a comprehensive summary of the tree including:
     * - Level-order traversal
     * - Tree height
     * - Total node count
     */
    public void displayTreeSummary() {
        if (root == null) {
            System.out.println("========================================");
            System.out.println("EMPLOYEE HIERARCHY BINARY TREE");
            System.out.println("========================================");
            System.out.println("Tree is empty. No employees to display.");
            System.out.println("========================================\n");
            return;
        }

        // Display the tree in level order
        displayLevelOrder();

        // Display tree statistics
        System.out.println("========================================");
        System.out.println("TREE STATISTICS");
        System.out.println("========================================");
        System.out.println("Total Nodes: " + nodeCount);
        System.out.println("Tree Height: " + getHeight());
        System.out.println("========================================\n");
    }
}