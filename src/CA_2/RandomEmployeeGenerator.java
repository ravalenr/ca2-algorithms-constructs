package CA_2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * RandomEmployeeGenerator handles generating random employees for testing.
 * Contains all the sample data arrays and generation logic.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class RandomEmployeeGenerator {

    private ArrayList<Employee> employeeList;
    private ArrayList<Manager> managerList;
    private ArrayList<Department> departmentList;
    private ManagerCreator managerCreator;

    // sample data for random generation
    private static final String[] FIRST_NAMES = {
            "John", "Jane", "Michael", "Sarah", "David", "Emily",
            "Robert", "Lisa", "James", "Mary", "William", "Patricia",
            "Richard", "Jennifer", "Thomas", "Linda", "Charles", "Elizabeth",
            "Daniel", "Susan", "Matthew", "Karen", "Anthony", "Nancy",
            "Mark", "Betty", "Donald", "Helen", "Steven", "Sandra"
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia",
            "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez",
            "Wilson", "Anderson", "Taylor", "Thomas", "Moore", "Jackson",
            "Martin", "Lee", "Thompson", "White", "Harris", "Clark",
            "Lewis", "Robinson", "Walker", "Young", "Allen", "King"
    };

    private static final String[] GENDERS = {"Male", "Female"};

    private static final String[] POSITIONS = {"senior", "middle", "junior", "intern", "contract"};

    private static final String[] JOB_TITLES = {
            "Teacher", "Assistant Teacher", "Counselor", "Librarian",
            "Lab Technician", "School Nurse", "Administrator", "Coordinator",
            "Specialist", "Support Staff", "Custodian", "Security Guard"
    };

    public RandomEmployeeGenerator(ArrayList<Employee> employeeList,
                                   ArrayList<Manager> managerList,
                                   ArrayList<Department> departmentList,
                                   ManagerCreator managerCreator) {
        this.employeeList = employeeList;
        this.managerList = managerList;
        this.departmentList = departmentList;
        this.managerCreator = managerCreator;
    }

    /**
     * Generates the specified number of random employees.
     * Makes sure to create core management first, then generates random staff.
     *
     * @param count Number of employees to generate
     * @return Number of employees successfully generated
     */
    public int generateRandomEmployees(int count) {
        // validate input
        if (count <= 0 || count > 1000) {
            System.out.println("Invalid count. Please enter a number between 1 and 1000.");
            return 0;
        }

        // get set of all existing emails to avoid duplicates
        Set<String> usedEmails = new HashSet<>();
        for (Employee e : employeeList) {
            if (e.getEmail() != null) {
                usedEmails.add(e.getEmail().toLowerCase());
            }
        }

        // make sure we have core management team
        managerCreator.ensureCoreManagementExists();

        System.out.println("\nGenerating " + count + " random employees...\n");

        int generatedCount = 0;
        DepartmentType[] departmentTypes = DepartmentType.values();

        // generate the requested number of employees
        for (int i = 0; i < count; i++) {
            String firstName;
            String lastName;
            String gender;
            String email;

            // keep trying until we get a unique email
            int safetyCounter = 0;
            do {
                firstName = FIRST_NAMES[(int) (Math.random() * FIRST_NAMES.length)];
                lastName = LAST_NAMES[(int) (Math.random() * LAST_NAMES.length)];
                gender = GENDERS[(int) (Math.random() * GENDERS.length)];
                email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@sunnydalehs.com";

                safetyCounter++;
                if (safetyCounter > 1000) {
                    System.out.println("WARNING: Could not generate unique email, skipping remaining employees.");
                    return generatedCount;
                }
            } while (usedEmails.contains(email.toLowerCase()));

            usedEmails.add(email.toLowerCase());

            // generate other random attributes
            double salary = 25000 + (Math.random() * 75000);
            String position = POSITIONS[(int) (Math.random() * POSITIONS.length)];
            String jobTitle = JOB_TITLES[(int) (Math.random() * JOB_TITLES.length)];
            DepartmentType randomDeptType = departmentTypes[(int) (Math.random() * departmentTypes.length)];

            // create the employee
            Employee newEmployee = new Teacher(firstName, lastName, gender, email,
                    salary, position, jobTitle, "School");

            // mark as randomly generated so it shows [RANDOM] tag
            newEmployee.setRandomlyGenerated(true);

            // assign to department
            Department dept = findOrCreateDepartment(randomDeptType.getDisplayName());
            newEmployee.setDepartment(dept);
            dept.addStaff(newEmployee);

            // assign a manager
            Manager assignedManager = managerCreator.findManagerForEmployee(newEmployee);
            if (assignedManager != null) {
                assignedManager.addEmployee(newEmployee);
            }

            employeeList.add(newEmployee);
            generatedCount++;
        }

        return generatedCount;
    }

    /**
     * Displays generation statistics
     */
    public void displayGenerationStats(int generatedCount) {
        System.out.println("========================================");
        System.out.println("Successfully generated " + generatedCount + " random employees!");
        System.out.println("Total employees in system: " + employeeList.size());
        System.out.println("========================================\n");
    }

    // helper method
    private Department findOrCreateDepartment(String departmentName) {
        // check if department already exists
        for (Department dept : departmentList) {
            if (dept.getDepartmentName().equalsIgnoreCase(departmentName)) {
                return dept;
            }
        }

        // doesn't exist, create new one
        DepartmentType deptType = DepartmentType.fromDisplayName(departmentName);
        if (deptType == null) {
            deptType = DepartmentType.COMPUTER_SCIENCE;
        }

        Department newDept = new AcademicDepartment(departmentName, deptType);
        departmentList.add(newDept);
        return newDept;
    }
}