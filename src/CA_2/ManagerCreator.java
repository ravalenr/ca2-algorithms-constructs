package CA_2;

import java.util.ArrayList;

/**
 * ManagerCreator handles all manager creation and setup logic.
 * This includes creating the core management team (Principal, VP, etc.)
 * and department heads for each department.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class ManagerCreator {

    private ArrayList<Employee> employeeList;
    private ArrayList<Manager> managerList;
    private ArrayList<Department> departmentList;

    public ManagerCreator(ArrayList<Employee> employeeList,
                          ArrayList<Manager> managerList,
                          ArrayList<Department> departmentList) {
        this.employeeList = employeeList;
        this.managerList = managerList;
        this.departmentList = departmentList;
    }

    /**
     * Makes sure the school has all core management positions filled.
     * Creates Principal, Vice Principal, Dean, and Academic Coordinator if missing.
     */
    public void ensureCoreManagementExists() {
        // check what managers we already have
        boolean hasPrincipal = false;
        boolean hasVicePrincipal = false;
        boolean hasDean = false;
        boolean hasAcademicCoordinator = false;

        for (Manager m : managerList) {
            if (m.getManagerType() == ManagerType.PRINCIPAL) hasPrincipal = true;
            if (m.getManagerType() == ManagerType.VICE_PRINCIPAL) hasVicePrincipal = true;
            if (m.getManagerType() == ManagerType.DEAN) hasDean = true;
            if (m.getManagerType() == ManagerType.ACADEMIC_COORDINATOR) hasAcademicCoordinator = true;
        }

        // get or create Senior Management department for these managers
        Department seniorMgmt = findOrCreateDepartment("Senior Management");

        // create each manager if they don't exist
        if (!hasPrincipal) {
            createPrincipal(seniorMgmt);
        }

        if (!hasVicePrincipal) {
            createVicePrincipal(seniorMgmt);
        }

        if (!hasDean) {
            createDean(seniorMgmt);
        }

        if (!hasAcademicCoordinator) {
            createAcademicCoordinator(seniorMgmt);
        }
    }

    /**
     * Creates the Principal if they don't already exist in employee list
     */
    private void createPrincipal(Department dept) {
        Employee existing = findEmployeeByEmail("principal.snyder@sunnydalehs.com");
        if (existing == null) {
            Principal principal = new Principal("Principal", "Snyder", "Male",
                    "principal.snyder@sunnydalehs.com", 90000.0, "senior", "School Principal", "School");
            principal.setDepartment(dept);
            dept.addStaff(principal);
            managerList.add(principal);
            employeeList.add(principal);
        } else if (existing instanceof Manager) {
            // already exists, just add to manager list
            managerList.add((Manager) existing);
        }
    }

    /**
     * Creates the Vice Principal if they don't already exist
     */
    private void createVicePrincipal(Department dept) {
        Employee existing = findEmployeeByEmail("robin.wood@sunnydalehs.com");
        if (existing == null) {
            VicePrincipal vp = new VicePrincipal("Robin", "Wood", "Male",
                    "robin.wood@sunnydalehs.com", 75000.0, "senior", "Vice Principal", "School");
            vp.setDepartment(dept);
            dept.addStaff(vp);
            managerList.add(vp);
            employeeList.add(vp);
        } else if (existing instanceof Manager) {
            managerList.add((Manager) existing);
        }
    }

    /**
     * Creates the Dean if they don't already exist
     */
    private void createDean(Department dept) {
        Employee existing = findEmployeeByEmail("jenny.calendar@sunnydalehs.com");
        if (existing == null) {
            DepartmentHead dean = new DepartmentHead("Jenny", "Calendar", "Female",
                    "jenny.calendar@sunnydalehs.com", 70000.0, "senior", "Dean of Students", "School");
            dean.managerType = ManagerType.DEAN;
            dean.setDepartment(dept);
            dept.addStaff(dean);
            managerList.add(dean);
            employeeList.add(dean);
        } else if (existing instanceof Manager) {
            managerList.add((Manager) existing);
        }
    }

    /**
     * Creates the Academic Coordinator if they don't already exist
     */
    private void createAcademicCoordinator(Department dept) {
        Employee existing = findEmployeeByEmail("rupert.giles@sunnydalehs.com");
        if (existing == null) {
            DepartmentHead coordinator = new DepartmentHead("Rupert", "Giles", "Male",
                    "rupert.giles@sunnydalehs.com", 68000.0, "senior", "Academic Coordinator", "School");
            coordinator.managerType = ManagerType.ACADEMIC_COORDINATOR;
            coordinator.setDepartment(dept);
            dept.addStaff(coordinator);
            managerList.add(coordinator);
            employeeList.add(coordinator);
        } else if (existing instanceof Manager) {
            managerList.add((Manager) existing);
        }
    }

    /**
     * Creates department heads for all departments that need one.
     * Skips Senior Management since it has the school-wide managers.
     */
    public void createDepartmentHeads() {
        // names to use for department heads
        String[] headFirstNames = {"Willow", "Xander", "Cordelia", "Oz", "Faith",
                "Wesley", "Anya", "Tara", "Dawn", "Andrew",
                "Kennedy", "Jonathan", "Harmony", "Amy", "Larry"};
        String[] headLastNames = {"Rosenberg", "Harris", "Chase", "Osbourne", "Lehane",
                "Wyndam-Pryce", "Jenkins", "Maclay", "Summers", "Wells",
                "Unknown", "Levinson", "Kendall", "Madison", "Blaisdell"};

        int nameIndex = 0;

        // go through each department
        for (Department dept : departmentList) {
            String deptName = dept.getDepartmentName();

            // skip senior management - already has Principal, VP, etc
            if (deptName.equalsIgnoreCase("Senior Management")) {
                continue;
            }

            // check if this department already has a head
            boolean hasHead = false;
            for (Manager m : managerList) {
                if (m.getManagerType() == ManagerType.DEPARTMENT_HEAD &&
                        m.getDepartment() != null &&
                        m.getDepartment().equals(dept)) {
                    hasHead = true;
                    break;
                }
            }

            // create a department head if needed
            if (!hasHead) {
                String firstName = headFirstNames[nameIndex % headFirstNames.length];
                String lastName = headLastNames[nameIndex % headLastNames.length];
                nameIndex++;

                String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@sunnydalehs.com";

                // check if someone with this email already exists
                Employee existing = findEmployeeByEmail(email);
                if (existing == null) {
                    existing = findEmployeeByName(firstName, lastName);
                }

                if (existing == null) {
                    // create new department head
                    DepartmentHead manager = new DepartmentHead(firstName, lastName, "Male",
                            email, 65000.0, "senior", "Department Head of " + deptName, "School");

                    manager.setDepartment(dept);
                    dept.addStaff(manager);
                    dept.setDepartmentHead(manager);

                    managerList.add(manager);
                    employeeList.add(manager);
                } else {
                    nameIndex++; // skip to next name
                }
            }
        }
    }

    /**
     * Assigns managers to all employees who don't have one yet.
     * Tries to match by department first.
     */
    public void assignManagersToEmployees() {
        for (Employee emp : employeeList) {
            // skip if already a manager or already has a manager
            if (emp instanceof Manager || emp.getManager() != null) {
                continue;
            }

            // find a manager in same department
            Manager assignedManager = findManagerForEmployee(emp);
            if (assignedManager != null) {
                assignedManager.addEmployee(emp);
            }
        }
    }

    /**
     * Finds an appropriate manager for an employee.
     * First tries same department, then any available manager.
     */
    public Manager findManagerForEmployee(Employee employee) {
        Department empDept = employee.getDepartment();

        // try to find manager in same department
        for (Manager manager : managerList) {
            if (manager.getDepartment() != null &&
                    manager.getDepartment().equals(empDept)) {
                return manager;
            }
        }

        // if no match, just use first available manager
        if (!managerList.isEmpty()) {
            return managerList.get(0);
        }

        return null;
    }

    // helper methods that access the lists

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

    private Employee findEmployeeByEmail(String email) {
        if (email == null) return null;

        for (Employee emp : employeeList) {
            if (emp.getEmail() != null && emp.getEmail().equalsIgnoreCase(email)) {
                return emp;
            }
        }
        return null;
    }

    private Employee findEmployeeByName(String firstName, String lastName) {
        if (firstName == null || lastName == null) return null;

        for (Employee emp : employeeList) {
            if (emp.getFirstName() != null && emp.getLastName() != null) {
                if (emp.getFirstName().equalsIgnoreCase(firstName) &&
                        emp.getLastName().equalsIgnoreCase(lastName)) {
                    return emp;
                }
            }
        }
        return null;
    }
}