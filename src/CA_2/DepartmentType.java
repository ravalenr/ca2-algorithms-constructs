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
    // Academic Departments
    SLAYER_STUDIES("Slayer Studies"),
    MAGIC_COMPUTATION("Magic & Computation"),
    MATHEMATICS("Mathematics"),
    SCIENCE("Science"),
    ENGLISH("English"),
    MODERN_LANGUAGES("Modern Languages"),
    GEOGRAPHY("Geography"),
    HISTORY("History"),
    COMPUTER_SCIENCE("Computer Science"),
    PHYSICAL_EDUCATION("Physical Education"),

    // Arts and Performance Departments
    PERFORMING_ARTS("Performing Arts"),
    DRAMA("Drama"),
    MUSIC("Music"),
    ART("Art"),

    // Student Support Services
    LIBRARY("Library"),
    GUIDANCE("Guidance"),
    STUDENT_SUPPORT("Student Support"),
    NURSING("Nursing"),

    // Administrative and Support Departments
    SENIOR_MANAGEMENT("Senior Management"),
    FINANCE_ADMINISTRATION("Finance and Administration"),
    RECEPTION("Reception"),
    LEGAL("Legal"),

    // Facilities and Operations
    FACILITIES("Facilities"),
    SECURITY("Security"),
    IT_SUPPORT("IT Support"),
    MECHANICS("Mechanics"),
    CANTEEN("Canteen");

    private final String displayName;

    private DepartmentType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the display name of this department type
     *
     * @return The display name as a String
     */
    public String getDisplayName() {
        return displayName;
    }

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
}
