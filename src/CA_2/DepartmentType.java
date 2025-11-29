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
    SLAYER_STUDIES("Slayer Studies", "Slayer training and combat education"),
    MAGIC_COMPUTATION("Magic & Computation", "Magical sciences and computational studies"),
    MATHEMATICS("Mathematics", "Mathematics and numerical sciences"),
    SCIENCE("Science", "Biology, Chemistry, Physics, and general sciences"),
    ENGLISH("English", "English language and literature"),
    MODERN_LANGUAGES("Modern Languages", "Foreign language instruction (Spanish, Japanese, etc.)"),
    GEOGRAPHY("Geography", "Geography and earth sciences"),
    HISTORY("History", "History and ancient civilizations"),
    COMPUTER_SCIENCE("Computer Science", "Computer science, programming, and cybersecurity"),
    PHYSICAL_EDUCATION("Physical Education", "Sports, fitness, and physical education"),

    // Arts and Performance Departments
    PERFORMING_ARTS("Performing Arts", "Music, vocal performance, and musical theatre"),
    DRAMA("Drama", "Drama, acting, and theatre arts"),
    MUSIC("Music", "Music education and instrumental instruction"),
    ART("Art", "Visual arts and creative expression"),

    // Student Support Services
    LIBRARY("Library", "Library services and information resources"),
    GUIDANCE("Guidance", "Student counselling and guidance services"),
    STUDENT_SUPPORT("Student Support", "Student welfare, behaviour, and liaison services"),
    NURSING("Nursing", "School health and medical services"),

    // Administrative and Support Departments
    SENIOR_MANAGEMENT("Senior Management", "School leadership and administration"),
    FINANCE_ADMINISTRATION("Finance and Administration", "Financial management and accounting"),
    RECEPTION("Reception", "Reception and front office services"),
    LEGAL("Legal", "Legal advisory services"),

    // Facilities and Operations
    FACILITIES("Facilities", "Campus maintenance and support coordination"),
    SECURITY("Security", "Campus security and safety services"),
    IT_SUPPORT("IT Support", "Technology support and systems engineering"),
    MECHANICS("Mechanics", "Workshop and technical instruction"),
    CANTEEN("Canteen", "Food services and canteen operations");

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
