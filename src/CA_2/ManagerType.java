package CA_2;

/**
 * ManagerType enum defines the different types of managers in the school management system.
 * Using an enum provides type safety and ensures only valid manager types can be assigned.
 *
 * Design Decision: Enums are used instead of String constants to prevent invalid manager types
 * and to provide compile-time type checking, making the code more robust and maintainable.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public enum ManagerType {
    // Enum constants representing different manager types
    PRINCIPAL("Principal", "School Principal - Overall school leadership"),
    VICE_PRINCIPAL("Vice Principal", "Vice Principal - Assists principal, handles discipline and events"),
    DEPARTMENT_HEAD("Department Head", "Department Head - Manages specific department and curriculum"),
    HEAD_MANAGER("Head Manager", "Head Manager - Senior leadership position"),
    SENIOR_MANAGER("Senior Manager", "Senior Manager - Experienced management role"),
    MANAGER("Manager", "Manager - Standard management position"),
    ASSISTANT_MANAGER("Assistant Manager", "Assistant Manager - Assists senior managers"),
    TEAM_LEAD("Team Lead", "Team Lead - Leads a specific team or project");

    // Instance variables
    private final String displayName;
    private final String description;

    /**
     * Private constructor for enum constants
     * Initializes each manager type with its display name and description
     *
     * @param displayName The human-readable name for this manager type
     * @param description A description of the manager type's role and responsibilities
     */
    private ManagerType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    /**
     * Returns the display name of this manager type
     * This is the human-readable format used in the UI
     *
     * @return The display name as a String
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the description of this manager type
     * Provides details about the role and responsibilities
     *
     * @return The description as a String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Finds a ManagerType enum by its display name (case-insensitive)
     * This method is useful for converting user input strings to enum values
     *
     * @param displayName The display name to search for
     * @return The matching ManagerType, or null if not found
     */
    public static ManagerType fromDisplayName(String displayName) {
        if (displayName == null) {
            return null;
        }

        for (ManagerType type : ManagerType.values()) {
            if (type.displayName.equalsIgnoreCase(displayName)) {
                return type;
            }
        }

        return null;
    }

    /**
     * Checks if a given string is a valid manager type display name
     *
     * @param displayName The string to validate
     * @return true if the string matches a valid manager type, false otherwise
     */
    public static boolean isValidManagerType(String displayName) {
        return fromDisplayName(displayName) != null;
    }

    /**
     * Returns a string representation of all available manager types
     * Useful for displaying options to users
     *
     * @return A formatted string listing all manager types
     */
    public static String getAllManagerTypes() {
        StringBuilder sb = new StringBuilder();
        sb.append("Available Manager Types:\n");

        ManagerType[] types = ManagerType.values();
        for (int i = 0; i < types.length; i++) {
            sb.append((i + 1)).append(". ").append(types[i].getDisplayName()).append("\n");
        }

        return sb.toString();
    }

    /**
     * Returns the string representation of this manager type
     * Returns the display name for easy printing
     *
     * @return The display name as a String
     */
    @Override
    public String toString() {
        return displayName;
    }
}
