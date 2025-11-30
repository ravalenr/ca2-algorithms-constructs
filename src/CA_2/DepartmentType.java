package CA_2;

/**
 * DepartmentType enum defines the types of departments in the school.
 * Enums ensure only valid department types can be used.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public enum DepartmentType {
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
