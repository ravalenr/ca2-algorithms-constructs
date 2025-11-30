package CA_2;

/**
 * ManagerType enum defines the types of managers in the school.
 * Enums ensure only valid manager types can be used.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public enum ManagerType {
    PRINCIPAL("Principal"),
    DEPUTY_PRINCIPAL("Deputy Principal"),
    VICE_PRINCIPAL("Vice Principal"),
    DEPARTMENT_HEAD("Department Head"),
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
