package CA_2;

/**
 * DepartmentType enum defines the different types of departments in the school management system.
 * Using an enum provides type safety and ensures only valid department types can be assigned.
 *
 * Design Decision: Enums are used to enforce valid department classifications
 * and provide compile-time type checking for robust code.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public enum DepartmentType {
    // Enum constants representing different department types
    IT_DEVELOPMENT("IT Development", "Information Technology and Software Development"),
    SALES("Sales", "Sales and Business Development"),
    HR("HR", "Human Resources"),
    FINANCE("Finance", "Financial Management and Accounting"),
    MARKETING("Marketing", "Marketing and Communications"),
    ACCOUNTING("Accounting", "Accounting and Financial Records"),
    OPERATIONS("Operations", "Operations and Logistics"),
    TECHNICAL_SUPPORT("Technical Support", "Technical Support and Customer Service"),
    CUSTOMER_SERVICE("Customer Service", "Customer Relations and Support");

    // Instance variables
    private final String displayName;
    private final String description;

    /**
     * Private constructor for enum constants
     * Initializes each department type with its display name and description
     *
     * @param displayName The human-readable name for this department type
     * @param description A description of the department's function
     */
    private DepartmentType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    /**
     * Returns the display name of this department type
     *
     * @return The display name as a String
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the description of this department type
     *
     * @return The description as a String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Finds a DepartmentType enum by its display name (case-insensitive)
     *
     * @param displayName The display name to search for
     * @return The matching DepartmentType, or null if not found
     */
    public static DepartmentType fromDisplayName(String displayName) {
        if (displayName == null) {
            return null;
        }

        for (DepartmentType type : DepartmentType.values()) {
            if (type.displayName.equalsIgnoreCase(displayName)) {
                return type;
            }
        }

        return null;
    }

    /**
     * Checks if a given string is a valid department type display name
     *
     * @param displayName The string to validate
     * @return true if the string matches a valid department type, false otherwise
     */
    public static boolean isValidDepartmentType(String displayName) {
        return fromDisplayName(displayName) != null;
    }

    /**
     * Returns a string representation of all available department types
     *
     * @return A formatted string listing all department types
     */
    public static String getAllDepartmentTypes() {
        StringBuilder sb = new StringBuilder();
        sb.append("Available Department Types:\n");

        DepartmentType[] types = DepartmentType.values();
        for (int i = 0; i < types.length; i++) {
            sb.append((i + 1)).append(". ").append(types[i].getDisplayName()).append("\n");
        }

        return sb.toString();
    }

    /**
     * Returns the string representation of this department type
     *
     * @return The display name as a String
     */
    @Override
    public String toString() {
        return displayName;
    }
}
