# School Management System - CA2 Project

## Student Information
- **Name:** Rafael Valentim Ribeiro
- **Student ID:** 2025129
- **Programme:** H.Dip. in Computing
- **Cohort:** Feb 2025
- **Modules:** Algorithms & Constructs / Software Development Fundamentals
- **Submission Date:** 29th November 2025

## Project Overview

This is a command-line School Management System built in Java as part of my integrated assessment for Algorithms & Constructs and Software Development Fundamentals modules. The system manages employee records in a school setting, including teachers, administrative staff, support staff, and various manager types.

The project demonstrates my understanding of:
- Object-oriented programming with inheritance
- Recursive sorting algorithms (Binary Tree Sort)
- Efficient searching algorithms (Binary Search)
- Data structures (Binary Trees)
- File handling and data processing
- Input validation and error handling

## What Does This System Do?

The School Management System allows users to:

1. **Sort Employee Records** - Sort all employees alphabetically by name and display the first 20
2. **Search for Employees** - Find specific employees and view their details including manager and department
3. **Add New Employees** - Add new employee records with validation
4. **Generate Random Data** - Create random employee records for testing
5. **View Hierarchy** - Display the organizational structure as a binary tree

## Project Structure

```
ca2-algorithms-constructs/
│
├── src/CA_2/                          # All source code (package CA_2)
│   │
│   ├── Employee.java                  # Parent class for all employees
│   ├── Manager.java                   # Abstract parent class for managers
│   ├── Department.java                # Abstract parent class for departments
│   │
│   ├── Principal.java                 # Principal manager type
│   ├── VicePrincipal.java            # Vice Principal manager type
│   ├── DepartmentHead.java           # Department Head manager type
│   │
│   ├── Teacher.java                   # Teaching staff
│   ├── AdministrativeStaff.java      # Admin staff
│   ├── SupportStaff.java             # Support services staff
│   │
│   ├── AcademicDepartment.java       # Academic departments
│   ├── AdministrativeDepartment.java # Admin departments
│   ├── SupportDepartment.java        # Support departments
│   │
│   ├── ManagerType.java               # Enum for manager types
│   ├── DepartmentType.java            # Enum for department types
│   ├── MenuOption.java                # Enum for menu options
│   │
│   ├── SortingAlgorithms.java        # Binary Tree Sort implementation
│   ├── SearchAlgorithms.java         # Binary Search implementation
│   │
│   └── SchoolManagementSystem.java   # Main application (to be completed)
│
├── docs/                              # Assignment documentation
├── Applicants_Form - Sample data file for read.txt  # Sample data
└── README.md                          # This file
```

## How to Run the Program

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- NetBeans IDE (recommended) or any Java IDE
- Terminal/Command Prompt access

### Using NetBeans:
1. Open NetBeans IDE
2. Click `File` → `Open Project`
3. Navigate to the project folder `ca2-algorithms-constructs`
4. Select the project and click `Open Project`
5. Right-click on the project in the Projects panel
6. Select `Run` or press `F6`


### Important Notes:
- Make sure `Applicants_Form - Sample data file for read.txt` is in the project root directory
- All classes must be in the `CA_2` package

## Key Features Explained

### 1. Object-Oriented Design with Inheritance

I used a three-level inheritance hierarchy to organize the code:

**Employee Hierarchy:**
- `Employee` (parent) → `Teacher`, `AdministrativeStaff`, `SupportStaff` (children)

**Manager Hierarchy:**
- `Employee` (grandparent) → `Manager` (parent) → `Principal`, `VicePrincipal`, `DepartmentHead` (children)

**Department Hierarchy:**
- `Department` (parent) → `AcademicDepartment`, `AdministrativeDepartment`, `SupportDepartment` (children)

This structure follows the "DRY" (Don't Repeat Yourself) principle and makes the code more maintainable.

### 2. Sorting Algorithm - Binary Tree Sort (Recursive)

**Why I chose Binary Tree Sort:**

I selected Binary Tree Sort as my recursive sorting algorithm for several important reasons:

1. **Natural Integration** - The assignment requires both sorting AND displaying employee hierarchy as a binary tree. Binary Tree Sort naturally accomplishes both tasks, making it a logical choice.

2. **Efficient Performance** - Binary Tree Sort has an average time complexity of O(n log n), which is efficient for the dataset sizes we're working with (100+ employees). This meets the performance requirement of completing operations within 2-3 seconds.

3. **Recursive Nature** - The algorithm is inherently recursive through its insert operation, which perfectly fulfills the assignment requirement for a recursive sorting algorithm.

4. **Intuitive for Hierarchies** - Since we're managing a school organization with natural hierarchical relationships (managers, departments, employees), a tree-based sorting approach makes conceptual sense.

5. **Educational Value** - Binary Tree Sort demonstrates my understanding of both tree data structures and recursive algorithms, which are core topics in the Algorithms & Constructs module.

**How it works:**
1. Insert each employee into a Binary Search Tree (BST) using recursive insertion
2. Perform an in-order traversal of the tree (also recursive)
3. The in-order traversal naturally produces a sorted sequence

The sorting is based on employee names (last name first, then first name) in alphabetical order.

### 3. Searching Algorithm - Binary Search (Recursive)

**Why I chose Binary Search:**

Binary Search is the most efficient searching algorithm for sorted data:

1. **Optimal Efficiency** - O(log n) time complexity means very fast searches even with large datasets
2. **Perfect Match** - Since we're already sorting the data with Binary Tree Sort, Binary Search is the natural choice
3. **Recursive Implementation** - The recursive nature divides the search space in half with each call
4. **Performance Guarantee** - Easily meets the <1 second search requirement specified in the assignment

**How it works:**
1. Takes a sorted array of employees
2. Compares the search term with the middle element
3. If match found, returns the employee
4. If search term is less, searches the left half (recursively)
5. If search term is greater, searches the right half (recursively)

The search is case-insensitive and returns complete employee details including Manager Type and Department.

### 4. Enums for Type Safety

I used Java Enums for three key areas:

- **MenuOption** - Defines all menu choices (SORT, SEARCH, ADD_RECORDS, etc.)
- **ManagerType** - Defines valid manager types (PRINCIPAL, VICE_PRINCIPAL, etc.)
- **DepartmentType** - Defines valid department types (IT_DEVELOPMENT, HR, FINANCE, etc.)

Using enums prevents invalid values and makes the code more robust and maintainable.

### 5. Input Validation

All user inputs are validated to ensure data integrity:
- Employee names cannot be empty
- Manager types must be valid (checked against ManagerType enum)
- Department types must be valid (checked against DepartmentType enum)
- Menu selections must be valid options

## Future Enhancements

If I were to extend this project, I would add:

1. **Persistence** - Save changes back to the file so data isn't lost when the program closes
2. **More Search Options** - Search by department, salary range, job title, etc.
3. **Edit/Delete Functions** - Allow modifying or removing existing employee records
4. **Reporting** - Generate reports (e.g., department summary, salary statistics)
5. **Data Validation** - More sophisticated validation (e.g., email format checking)

## References and Resources

During this project, I consulted:

- Module lecture notes on Object-Oriented Programming
- Module lecture notes on Sorting and Searching Algorithms
- Java Documentation: https://docs.oracle.com/javase/8/docs/api/
- "Data Structures and Algorithms in Java" course materials

## GitHub Repository

This project is maintained under version control using Git.

**Repository:** https://github.com/ravalenr/ca2-algorithms-constructs

The repository includes:
- All source code files (.java)
- Documentation (this README)
- Sample data file
- Regular commits showing development progress (10-15 commits as required)

## Academic Integrity Statement

I confirm that this is my own work. While I consulted various resources for learning, all code was written by me, and I understand how every part of it works. I am prepared to explain any part of my submission during the Q&A session.

## Contact

If you have any questions about this project:

**Student:** Rafael Valentim Ribeiro
**Student ID:** 2025129


