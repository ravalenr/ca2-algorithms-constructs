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
- Recursive sorting algorithms (Merge Sort)
- Efficient searching algorithms (Linear Search)
- Data structures and algorithms
- File handling and data processing
- Input validation and error handling

## What Does This System Do?

The School Management System allows users to:

1. **Sort Employee Records** - Sort all employees alphabetically by name and display the first 20
2. **Search for Employees** - Find specific employees and view their details including manager and department
3. **Add New Employees** - Add new employee records with validation
4. **Generate Random Data** - Create random employee records for testing
5. **View Hierarchy** - Display the organizational structure showing managers and their departments
6. **Department Statistics** - View department breakdown by category (Academic, Arts, Support, Administrative)

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
│   ├── SortingAlgorithms.java        # Merge Sort implementation
│   ├── SearchAlgorithms.java         # Linear Search implementation
│   │
│   └── SchoolManagementSystem.java   # Main application (to be completed)
│
├── Applicants_Form.txt               # Sample data
└── README.md                         # This file
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
- Make sure `Applicants_Form.txt` is in the project root directory
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


### 2. Sorting Algorithm - Merge Sort (Recursive)

**Why I chose Merge Sort:**

I selected Merge Sort as my recursive sorting algorithm for several important reasons:

1. **Guaranteed Performance** - Merge Sort has O(n log n) time complexity in ALL cases (best, average, and worst), unlike Quick Sort which can degrade to O(n²). This ensures consistent performance regardless of input data patterns.

2. **Properly Recursive** - Merge Sort is a true divide-and-conquer recursive algorithm. It divides the problem into smaller subproblems, solves them recursively, then combines the results - perfectly demonstrating the recursive paradigm.

3. **Stable Sorting** - Merge Sort is a stable sort, meaning employees with identical names maintain their original order. This is important for maintaining data integrity in employee records.

4. **Predictable Behavior** - The algorithm's performance is reliable and doesn't depend on pivot selection or data distribution, making it ideal for production systems.

5. **Educational Value** - Merge Sort demonstrates core algorithmic concepts: recursion, divide-and-conquer strategy, and the merge operation for combining sorted sequences.

**How it works:**
1. Divide the array into two halves (recursively)
2. Sort each half independently using the same merge sort algorithm (recursive calls)
3. Merge the two sorted halves back together into a single sorted array
4. Base case: arrays with 0 or 1 elements are already sorted

The sorting is based on employee names (last name first, then first name) in alphabetical order using case-insensitive comparison.

### 3. Searching Algorithm - Linear Search

**Why I chose Linear Search:**

I selected Linear Search as my searching algorithm for several practical reasons:

1. **Partial Name Matching** - Linear Search allows me to search for partial names (e.g., searching "buff" finds "Buffy Summers"). This makes the system much more user-friendly since people don't always remember full names.

2. **Case-Insensitive Searching** - The implementation ignores case, making it easier for users to search without worrying about capitalization.

3. **No Sorting Required** - Unlike Binary Search which requires sorted data, Linear Search works on any array. This means I can search immediately without having to sort first.

4. **Real-World Performance** - With O(n) time complexity, it's fast enough for typical school sizes (50-500 employees). The search completes in well under a second for these dataset sizes.

5. **Better User Experience** - While Binary Search is technically faster (O(log n)), it only works with exact matches. Linear Search provides a much better user experience by finding partial matches.

**How it works:**
1. Takes an array of employees and a search term
2. Iterates through each employee in the array
3. Compares the search term (case-insensitive) against first name, last name, and full name
4. Checks if the search term is contained in any of these fields (partial matching)
5. Returns all employees that match the search criteria

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
- Regular commits showing development progress

## Academic Integrity Statement

I confirm that this is my own work. While I consulted various resources for learning, all code was written by me, and I understand how every part of it works. I am prepared to explain any part of my submission during the Q&A session.

## Contact

If you have any questions about this project:

**Student:** Rafael Valentim Ribeiro
**Student ID:** 2025129


