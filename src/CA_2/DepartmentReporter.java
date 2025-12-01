package CA_2;

import java.util.ArrayList;

/**
 * DepartmentReporter handles displaying department statistics and reports.
 * Groups departments by category and shows staff counts.
 *
 * @author Rafael Valentim Ribeiro
 * @version 1.0
 */
public class DepartmentReporter {

    private ArrayList<Employee> employeeList;
    private ArrayList<Department> departmentList;

    public DepartmentReporter(ArrayList<Employee> employeeList,
                              ArrayList<Department> departmentList) {
        this.employeeList = employeeList;
        this.departmentList = departmentList;
    }

    /**
     * Displays complete department statistics report.
     * Groups departments by category and shows staff counts.
     */
    public void displayDepartmentStatistics() {
        if (departmentList.isEmpty()) {
            System.out.println("No departments to display.");
            System.out.println("Please load data or generate employees first.");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("DEPARTMENT STATISTICS REPORT");
        System.out.println("========================================\n");

        // display each category
        displayAcademicDepartments();
        displayArtsDepartments();
        displaySupportDepartments();
        displayAdminDepartments();
        displayOverallSummary();
    }

    /**
     * Shows academic departments like Math, Science, etc.
     */
    private void displayAcademicDepartments() {
        System.out.println("ACADEMIC DEPARTMENTS:");
        System.out.println("----------------------------------------");

        int academicCount = 0;
        int academicStaff = 0;

        for (Department dept : departmentList) {
            DepartmentType type = dept.departmentType;
            if (isAcademicDepartment(type)) {
                int staffCount = getStaffCountForDepartment(dept);
                System.out.println("  " + dept.getDepartmentName() + ": " + staffCount + " staff");
                academicCount++;
                academicStaff += staffCount;
            }
        }

        System.out.println("  Total Academic Departments: " + academicCount);
        System.out.println("  Total Academic Staff: " + academicStaff);
        System.out.println();
    }

    /**
     * Shows arts and performance departments
     */
    private void displayArtsDepartments() {
        System.out.println("ARTS & PERFORMANCE DEPARTMENTS:");
        System.out.println("----------------------------------------");

        int artsCount = 0;
        int artsStaff = 0;

        for (Department dept : departmentList) {
            DepartmentType type = dept.departmentType;
            if (isArtsDepartment(type)) {
                int staffCount = getStaffCountForDepartment(dept);
                System.out.println("  " + dept.getDepartmentName() + ": " + staffCount + " staff");
                artsCount++;
                artsStaff += staffCount;
            }
        }

        System.out.println("  Total Arts Departments: " + artsCount);
        System.out.println("  Total Arts Staff: " + artsStaff);
        System.out.println();
    }

    /**
     * Shows student support services
     */
    private void displaySupportDepartments() {
        System.out.println("STUDENT SUPPORT SERVICES:");
        System.out.println("----------------------------------------");

        int supportCount = 0;
        int supportStaff = 0;

        for (Department dept : departmentList) {
            DepartmentType type = dept.departmentType;
            if (isSupportDepartment(type)) {
                int staffCount = getStaffCountForDepartment(dept);
                System.out.println("  " + dept.getDepartmentName() + ": " + staffCount + " staff");
                supportCount++;
                supportStaff += staffCount;
            }
        }

        System.out.println("  Total Support Departments: " + supportCount);
        System.out.println("  Total Support Staff: " + supportStaff);
        System.out.println();
    }

    /**
     * Shows administrative and operations departments
     */
    private void displayAdminDepartments() {
        System.out.println("ADMINISTRATIVE & OPERATIONS:");
        System.out.println("----------------------------------------");

        int adminCount = 0;
        int adminStaff = 0;

        for (Department dept : departmentList) {
            DepartmentType type = dept.departmentType;
            if (isAdminDepartment(type)) {
                int staffCount = getStaffCountForDepartment(dept);
                System.out.println("  " + dept.getDepartmentName() + ": " + staffCount + " staff");
                adminCount++;
                adminStaff += staffCount;
            }
        }

        System.out.println("  Total Admin/Operations Departments: " + adminCount);
        System.out.println("  Total Admin/Operations Staff: " + adminStaff);
        System.out.println();
    }

    /**
     * Shows overall summary with totals and largest department
     */
    private void displayOverallSummary() {
        System.out.println("========================================");
        System.out.println("OVERALL SUMMARY:");
        System.out.println("----------------------------------------");
        System.out.println("Total Departments: " + departmentList.size());
        System.out.println("Total Staff: " + employeeList.size());
        System.out.println("Average Staff per Department: " +
                (departmentList.size() > 0 ? employeeList.size() / departmentList.size() : 0));

        // find largest department
        Department largest = null;
        int maxStaff = 0;
        for (Department dept : departmentList) {
            int staffCount = getStaffCountForDepartment(dept);
            if (staffCount > maxStaff) {
                maxStaff = staffCount;
                largest = dept;
            }
        }

        if (largest != null) {
            System.out.println("Largest Department: " + largest.getDepartmentName() +
                    " (" + maxStaff + " staff)");
        }

        System.out.println("========================================\n");
    }

    /**
     * Counts how many staff members are in a department
     */
    private int getStaffCountForDepartment(Department dept) {
        int count = 0;
        for (Employee emp : employeeList) {
            if (emp.getDepartment() != null && emp.getDepartment().equals(dept)) {
                count++;
            }
        }
        return count;
    }

    // helper methods to check department categories

    private boolean isAcademicDepartment(DepartmentType type) {
        return type == DepartmentType.SLAYER_STUDIES ||
                type == DepartmentType.MAGIC_COMPUTATION ||
                type == DepartmentType.MATHEMATICS ||
                type == DepartmentType.SCIENCE ||
                type == DepartmentType.ENGLISH ||
                type == DepartmentType.MODERN_LANGUAGES ||
                type == DepartmentType.GEOGRAPHY ||
                type == DepartmentType.HISTORY ||
                type == DepartmentType.COMPUTER_SCIENCE ||
                type == DepartmentType.PHYSICAL_EDUCATION;
    }

    private boolean isArtsDepartment(DepartmentType type) {
        return type == DepartmentType.PERFORMING_ARTS ||
                type == DepartmentType.DRAMA ||
                type == DepartmentType.MUSIC ||
                type == DepartmentType.ART;
    }

    private boolean isSupportDepartment(DepartmentType type) {
        return type == DepartmentType.LIBRARY ||
                type == DepartmentType.GUIDANCE ||
                type == DepartmentType.STUDENT_SUPPORT ||
                type == DepartmentType.NURSING;
    }

    private boolean isAdminDepartment(DepartmentType type) {
        return type == DepartmentType.SENIOR_MANAGEMENT ||
                type == DepartmentType.FINANCE_ADMINISTRATION ||
                type == DepartmentType.RECEPTION ||
                type == DepartmentType.LEGAL ||
                type == DepartmentType.FACILITIES ||
                type == DepartmentType.SECURITY ||
                type == DepartmentType.IT_SUPPORT ||
                type == DepartmentType.MECHANICS ||
                type == DepartmentType.CANTEEN;
    }
}