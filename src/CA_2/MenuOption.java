package CA_2;

/**
 * MenuOption enum defines all available menu options in the school management system.
 * Using an enum for menu options provides type safety, prevents invalid menu selections,
 * and makes the menu system more maintainable and structured.
 *
 * Design Decision: Enums are used to iterate through menu options systematically
 * and ensure compile-time type checking for robust user interface code.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public enum MenuOption {
    // Enum constants representing different menu options
    SORT(1, "Sort Employee List", "Sort employees alphabetically and display first 20 names"),
    SEARCH(2, "Search Employee", "Search for an employee and view their details"),
    ADD_RECORDS(3, "Add New Employee", "Add a new employee record to the system"),
    GENERATE_RANDOM(4, "Generate Random Employees", "Generate random employee records for testing"),
    CREATE_BINARY_TREE(5, "Display Employee Hierarchy", "View employee hierarchy as binary tree"),
    EXIT(6, "Exit", "Exit the application");

    // Instance variables
    private final int optionNumber;
    private final String displayName;
    private final String description;

    /**
     * Private constructor for enum constants
     * Initializes each menu option with its number, display name, and description
     *
     * @param optionNumber The numeric option for user selection
     * @param displayName The human-readable name for this menu option
     * @param description A description of what this menu option does
     */
    private MenuOption(int optionNumber, String displayName, String description) {
        this.optionNumber = optionNumber;
        this.displayName = displayName;
        this.description = description;
    }

    /**
     * Returns the option number for this menu item
     *
     * @return The option number as an integer
     */
    public int getOptionNumber() {
        return optionNumber;
    }

    /**
     * Returns the display name of this menu option
     *
     * @return The display name as a String
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the description of this menu option
     *
     * @return The description as a String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Finds a MenuOption enum by its option number
     * This method converts user numeric input to the corresponding enum value
     *
     * @param optionNumber The option number to search for
     * @return The matching MenuOption, or null if not found
     */
    public static MenuOption fromOptionNumber(int optionNumber) {
        for (MenuOption option : MenuOption.values()) {
            if (option.optionNumber == optionNumber) {
                return option;
            }
        }
        return null;
    }

    /**
     * Checks if a given number is a valid menu option
     *
     * @param optionNumber The number to validate
     * @return true if the number matches a valid menu option, false otherwise
     */
    public static boolean isValidOption(int optionNumber) {
        return fromOptionNumber(optionNumber) != null;
    }

    /**
     * Displays all menu options in a formatted menu
     * This method is used to present the main menu to users
     */
    public static void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("   SCHOOL MANAGEMENT SYSTEM - MAIN MENU");
        System.out.println("========================================");

        for (MenuOption option : MenuOption.values()) {
            System.out.println(option.optionNumber + ". " + option.displayName);
        }

        System.out.println("========================================");
        System.out.print("Please select an option: ");
    }

    /**
     * Returns a string representation of all available menu options with descriptions
     *
     * @return A formatted string listing all menu options with their descriptions
     */
    public static String getAllOptionsWithDescriptions() {
        StringBuilder sb = new StringBuilder();
        sb.append("Available Menu Options:\n");
        sb.append("========================================\n");

        for (MenuOption option : MenuOption.values()) {
            sb.append(option.optionNumber).append(". ").append(option.displayName)
              .append("\n   ").append(option.description).append("\n");
        }

        sb.append("========================================\n");
        return sb.toString();
    }

    /**
     * Returns the string representation of this menu option
     *
     * @return The display name as a String
     */
    @Override
    public String toString() {
        return optionNumber + ". " + displayName;
    }
}
