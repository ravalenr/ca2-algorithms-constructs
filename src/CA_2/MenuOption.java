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
    SORT(1, "Sort Employee List"),
    SEARCH(2, "Search Employee"),
    ADD_RECORDS(3, "Add New Employee"),
    GENERATE_RANDOM(4, "Generate Random Employees"),
    DISPLAY_ALL(5, "Display All Employees"),
    DISPLAY_HIERARCHY(6, "Display Organizational Hierarchy"),
    DEPARTMENT_STATS(7, "Department Statistics Report"),
    CHANGE_FILE(8, "Change Data File"),
    EXIT(9, "Exit");

    private final int optionNumber;
    private final String displayName;

    private MenuOption(int optionNumber, String displayName) {
        this.optionNumber = optionNumber;
        this.displayName = displayName;
    }

    public static MenuOption fromOptionNumber(int optionNumber) {
        for (MenuOption option : MenuOption.values()) {
            if (option.optionNumber == optionNumber) {
                return option;
            }
        }
        return null;
    }

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
}
