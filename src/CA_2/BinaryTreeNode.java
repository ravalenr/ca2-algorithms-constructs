package CA_2;

/**
 * BinaryTreeNode represents a single node in the employee hierarchy binary tree.
 * Each node contains an employee and references to left and right child nodes.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class BinaryTreeNode {

    // Instance variables
    private Employee employee;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    /**
     * Constructor creates a new node with the given employee.
     * Initially, left and right children are null.
     *
     * @param employee The employee to store in this node
     */
    public BinaryTreeNode(Employee employee) {
        this.employee = employee;
        this.left = null;
        this.right = null;
    }

    /**
     * Gets the employee stored in this node.
     *
     * @return The employee object
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Sets the employee for this node.
     *
     * @param employee The employee to store
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Gets the left child node.
     *
     * @return The left child node or null if no left child exists
     */
    public BinaryTreeNode getLeft() {
        return left;
    }

    /**
     * Sets the left child node.
     *
     * @param left The node to set as left child
     */
    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    /**
     * Gets the right child node.
     *
     * @return The right child node or null if no right child exists
     */
    public BinaryTreeNode getRight() {
        return right;
    }

    /**
     * Sets the right child node.
     *
     * @param right The node to set as right child
     */
    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    /**
     * Checks if this node has a left child.
     *
     * @return true if left child exists, false otherwise
     */
    public boolean hasLeft() {
        return left != null;
    }

    /**
     * Checks if this node has a right child.
     *
     * @return true if right child exists, false otherwise
     */
    public boolean hasRight() {
        return right != null;
    }

    /**
     * Checks if this node is a leaf (has no children).
     *
     * @return true if node has no children, false otherwise
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }
}