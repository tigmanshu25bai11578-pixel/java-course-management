# Java Project Vityarthi - Course Management System

## Student Details
- **Name:** TIGAMSNHU MISHRA
- **Reg No:** 25BAI11578

## Project Overview
This is a comprehensive Java-based Course Management System designed to manage student enrollments, course information, grades, and academic transcripts. The system demonstrates object-oriented design principles with service-oriented architecture and proper exception handling.

### Key Features
- **Student Management** - Create, retrieve, and manage student records with enrollment tracking
- **Course Management** - Manage courses with credits, instructors, and semesters
- **Enrollment System** - Enroll students in courses with credit limit validation
- **Grade Recording** - Record and track student grades for enrolled courses
- **GPA Calculation** - Automatically calculate student GPAs based on recorded grades
- **CSV Import/Export** - Import students and courses from CSV files and export data
- **Data Persistence** - Backup and restore student and course data
- **Exception Handling** - Custom exceptions for business rule validation

## Prerequisites
- **Java Development Kit (JDK) 11** or higher
- A terminal/command prompt
- **No Maven required!** (compiles directly with javac)

## Installation

### Verify Java Installation
```bash
java -version
javac -version
```

If Java is not found, install Java 11 or higher from: https://www.oracle.com/java/technologies/downloads/

## Building and Running the Project

### **Recommended: Build and Run in One Command**

#### Windows
```bash
run.bat
```

#### Linux/Mac
```bash
./run.sh
chmod +x run.sh  # Make script executable if needed
```

This will automatically:
1. Compile all Java files with javac
2. Create test data if needed
3. Run the application

No external build tools needed!

## Project Structure
```
├── Main.java                          # Application entry point
├── config/
│   └── AppConfig.java                # Singleton configuration management
├── domain/                            # Domain model classes
│   ├── Person.java                   # Base class for persons
│   ├── Student.java                  # Student entity with enrollment tracking
│   ├── Course.java                   # Course entity with builder pattern
│   ├── Instructor.java               # Instructor information
│   ├── Grade.java                    # Grade enumeration (A, B, C, D, F)
│   ├── Semester.java                 # Semester enumeration (FALL, SPRING, SUMMER)
│   └── Name.java                     # Student name object
├── service/                           # Service interfaces and implementations
│   ├── CrudService.java              # Generic CRUD interface
│   ├── CourseService.java            # Course service interface
│   ├── StudentService.java           # Student service interface
│   ├── EnrollmentService.java        # Enrollment service interface
│   ├── TranscriptService.java        # Transcript service interface
│   └── impl/                          # Service implementations
│       ├── CourseServiceImpl.java     # Course management implementation
│       ├── StudentServiceImpl.java    # Student management implementation
│       └── EnrollmentServiceImpl.java # Enrollment management implementation
├── exception/                         # Custom exception classes
│   ├── DuplicateEnrollmentException.java
│   ├── EntityNotFoundException.java
│   ├── MaxCreditLimitExceededException.java
│   └── PrerequisiteNotMetException.java
├── io/                                # Input/Output and data operations
│   ├── CsvParser.java                # Generic CSV parsing utility
│   ├── ImportExportService.java      # CSV import/export for students and courses
│   └── BackupService.java            # Data backup functionality
├── test-data/                         # Sample CSV files
│   ├── students.csv                  # Sample student data
│   └── courses.csv                   # Sample course data
├── bin/                               # Compiled .class files (auto-generated)
├── run.bat                            # Windows build and run script
├── run.sh                             # Linux/Mac build and run script
└── README.md                          # This documentation
```

## Compiled Output
After successful run, you will find:
- `bin/` directory - Contains all compiled `.class` files

## Sample Test Data
The application automatically creates sample CSV files in the `test-data/` directory if they don't exist:

### students.csv Format
```
ID,RegNo,FirstName,LastName,Email
S001,REG-2021-001,Alice,Johnson,alice@university.edu
S002,REG-2021-002,Bob,Smith,bob@university.edu
S003,REG-2021-003,Charlie,Brown,charlie@university.edu
...
```

### courses.csv Format
```
Code,Title,Credits,Instructor,Semester,Department
CS101,Introduction to Java,3,Dr. Smith,FALL,Computer Science
CS102,Data Structures,4,Dr. Johnson,FALL,Computer Science
MATH201,Calculus I,4,Dr. Davis,FALL,Mathematics
...
```

## Design Patterns Used
- **Singleton Pattern** - AppConfig for application configuration
- **Service Layer Pattern** - Separation of business logic through service interfaces
- **Builder Pattern** - Course object construction
- **Generic CRUD Pattern** - Common data access operations
- **Exception Handling** - Custom exceptions for domain-specific errors

## Features Demonstrated
When you run the application, it will:
1. Initialize the course management system with configuration
2. Import students and courses from CSV files (or create sample data if files don't exist)
3. Display all imported students with their details
4. Display all imported courses with credits and information
5. Enroll all students in all available courses
6. Record grades for students in their enrolled courses
7. Calculate and display student GPAs
8. Show final enrollment status for each student

## Running the Application

### Quick Start
Simply execute the appropriate script for your operating system:

**Windows:**
```bash
run.bat
```

**Linux/Mac:**
```bash
./run.sh
```

The script will automatically:
- Compile all Java source files
- Create test-data directory with sample CSV files if needed
- Run the application
- Display enrollment and grade recording operations
- Show final student status with GPA calculations

### Expected Output
```
========================================
Building and Running Course Management System
========================================

IMPORTED STUDENTS:
  - Alice Johnson (S001)
  - Bob Smith (S002)
  ...

IMPORTED COURSES:
  - Introduction to Java (CS101, 3 credits)
  - Data Structures (CS102, 4 credits)
  ...

TESTING ENROLLMENT:
✓ Enrolled Alice Johnson in Introduction to Java
✓ Enrolled Alice Johnson in Data Structures
...

RECORDING GRADES:
✓ Recorded A for Alice Johnson in Introduction to Java
...

FINAL STATUS:
Student: Alice Johnson
  Enrolled Courses: 2
  Current GPA: 3.50
...

✓ Demo completed successfully!
```

## Troubleshooting

### Build fails with "Java not found"
Ensure Java 11+ is installed and in your PATH:
```bash
java -version
javac -version
```

Install from: https://www.oracle.com/java/technologies/downloads/

### Cannot find symbol error
Verify all Java files are in their correct directories:
- `config/` - AppConfig.java
- `domain/` - Student.java, Course.java, etc.
- `service/` - Service interfaces
- `service/impl/` - Service implementations
- `exception/` - Exception classes
- `io/` - Import/export services

### Test data not found
The run scripts automatically create CSV files in `test-data/` directory if they don't exist.

## Project Dependencies
- **JDK 11+** - Java Development Kit
- **No external libraries** - Uses only Java Standard Library

## Learning Outcomes
This project demonstrates:
- Object-oriented design principles (inheritance, polymorphism, encapsulation)
- Service-oriented architecture
- Exception handling and custom exceptions
- File I/O and CSV parsing
- Generic programming and collections
- Singleton and builder design patterns
- Layered architecture design

## License
This project is part of an educational course assignment.
