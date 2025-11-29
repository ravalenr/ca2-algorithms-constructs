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
    // Enum constants representing different manager types in a school context
    PRINCIPAL("Principal"),
    DEPUTY_PRINCIPAL("DeputyPrincipal"),
    VICE_PRINCIPAL("Vice Principal"),
    DEPARTMENT_HEAD("DepartmentHead"),
    DEAN("Dean"),
    ACADEMIC_COORDINATOR("Academic Coordinator");

    private final String displayName;

    private ManagerType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
