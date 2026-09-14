package edu.ccrm;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.*;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.StudentService;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.impl.CourseServiceImpl;
import edu.ccrm.service.impl.StudentServiceImpl;
import edu.ccrm.service.impl.EnrollmentServiceImpl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialize configuration
            AppConfig config = AppConfig.getInstance();
            Path dataDir = config.getDataFolderPath();
            
            // Create a placeholder EnrollmentService reference for StudentServiceImpl
            EnrollmentService enrollmentService = null;
            
            // Step 1: Create StudentServiceImpl (pass null, it will be used later)
            StudentServiceImpl studentService = new StudentServiceImpl(enrollmentService);
            
            // Step 2: Create CourseServiceImpl  
            CourseService courseService = new CourseServiceImpl(studentService);
            
            // Step 3: Create EnrollmentServiceImpl with fully initialized services
            enrollmentService = new EnrollmentServiceImpl(studentService, courseService, config.getMaxCreditsPerSemester());
            
            // Step 4: Set the enrollmentService in StudentServiceImpl
            studentService.setEnrollmentService(enrollmentService);
            
            // Initialize import/export service
            ImportExportService importExport = new ImportExportService(dataDir);

            System.out.println("Importing sample data...");
            
            // Import students and courses from CSV files
            List<Student> students = importExport.importStudents(Paths.get("test-data/students.csv"));
            List<Course> courses = importExport.importCourses(Paths.get("test-data/courses.csv"));

            // Add imported data to services
            for (Student student : students) {
                studentService.create(student);
            }
            for (Course course : courses) {
                courseService.create(course);
            }

            // Display imported data
            System.out.println("\n========================================");
            System.out.println("IMPORTED STUDENTS:");
            System.out.println("========================================");
            if (students.isEmpty()) {
                System.out.println("No students found. Creating sample students...");
                Student s1 = new Student("S001", "Alice Johnson", "alice@university.edu", "REG-2021-001");
                Student s2 = new Student("S002", "Bob Smith", "bob@university.edu", "REG-2021-002");
                Student s3 = new Student("S003", "Charlie Brown", "charlie@university.edu", "REG-2021-003");
                students = Arrays.asList(s1, s2, s3);
                for (Student student : students) {
                    studentService.create(student);
                }
            }
            students.forEach(s -> System.out.println("  - " + s.getFullName() + " (" + s.getId() + ")"));

            System.out.println("\n========================================");
            System.out.println("IMPORTED COURSES:");
            System.out.println("========================================");
            if (courses.isEmpty()) {
                System.out.println("No courses found. Creating sample courses...");
                Course c1 = new Course.Builder("CS101")
                    .title("Introduction to Java")
                    .credits(3)
                    .semester(Semester.FALL)
                    .department("Computer Science")
                    .build();
                Course c2 = new Course.Builder("CS102")
                    .title("Data Structures")
                    .credits(4)
                    .semester(Semester.SPRING)
                    .department("Computer Science")
                    .build();
                courses = Arrays.asList(c1, c2);
                for (Course course : courses) {
                    courseService.create(course);
                }
            }
            courses.forEach(c -> System.out.println("  - " + c.getTitle() + " (" + c.getCode() + ", " + c.getCredits() + " credits)"));

            System.out.println("\n========================================");
            System.out.println("TESTING ENROLLMENT:");
            System.out.println("========================================");
            
            // Enroll all students in all courses
            for (Student student : students) {
                for (Course course : courses) {
                    try {
                        enrollmentService.enrollStudent(student.getId(), course.getCode());
                        System.out.println("✓ Enrolled " + student.getFullName() + " in " + course.getTitle());
                    } catch (Exception e) {
                        System.out.println("✗ Could not enroll " + student.getFullName() + " in " + course.getTitle() + ": " + e.getMessage());
                    }
                }
            }

            // Record grades
            System.out.println("\n========================================");
            System.out.println("RECORDING GRADES:");
            System.out.println("========================================");
            
            Grade[] gradeArray = {Grade.A, Grade.B, Grade.A};
            for (int i = 0; i < Math.min(students.size(), courses.size()); i++) {
                try {
                    enrollmentService.recordGrade(students.get(i).getId(), courses.get(i).getCode(), gradeArray[i % gradeArray.length]);
                    System.out.println("✓ Recorded " + gradeArray[i % gradeArray.length] + " for " + students.get(i).getFullName() + " in " + courses.get(i).getTitle());
                } catch (Exception e) {
                    System.out.println("✗ Could not record grade: " + e.getMessage());
                }
            }

            // Display final status
            System.out.println("\n========================================");
            System.out.println("FINAL STATUS:");
            System.out.println("========================================");
            for (Student student : students) {
                System.out.println("Student: " + student.getFullName());
                System.out.println("  Enrolled Courses: " + student.getEnrolledCourses().size());
                System.out.println("  Current GPA: " + String.format("%.2f", student.getGpa()));
            }

            System.out.println("\n========================================");
            System.out.println("✓ Demo completed successfully!");
            System.out.println("========================================\n");

        } catch (Exception e) {
            System.err.println("Error running demo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}