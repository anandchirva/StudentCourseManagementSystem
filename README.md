# StudentCourseManagementSystem
Student Course Management System
A console-based Java application for managing students, trainers, courses, and enrollments. The project follows a layered architecture with separate packages for entities, custom exceptions, business services, user interface handling, and utilities.
Features
•	Add, remove, update, and list students
•	Add, remove, update, and list trainers
•	Add, view, activate, and deactivate courses
•	Enroll students in courses
•	View a student’s enrollments
•	Update enrollment status with validation
•	Generate unique IDs for persons, students, trainers, and enrollments
•	Handle invalid operations through custom runtime exceptions
Project Structure
com.project
├── entity
│   ├── Person.java
│   ├── Student.java
│   ├── Trainer.java
│   ├── Course.java
│   └── Enrollment.java
├── exception
│   ├── StudentCourseManagementException.java
│   ├── StudentException.java
│   ├── TrainerException.java
│   ├── CourseException.java
│   └── EnrollmentException.java
├── service
│   ├── StudentService.java
│   ├── StudentServiceImpl.java
│   ├── TrainerService.java
│   ├── TrainerServiceImpl.java
│   ├── CourseService.java
│   ├── CourseServiceImpl.java
│   ├── EnrollmentService.java
│   ├── EnrollmentServiceImpl.java
│   ├── MenuHandler.java
│   └── ValidatedInputHandler.java
├── ui
│   ├── ConsoleUI.java
│   └── Constants.java
└── util
    └── StudentCourseUtils.java

Architecture
1. Data Layer — com.project.entity
Contains the core domain classes:
Class	Purpose
Person	Abstract base class containing common personal details
Student	Represents a student and extends Person
Trainer	Represents a trainer and extends Person
Course	Represents a training course
Enrollment	Links a student to a course and tracks enrollment status

Person stores personId, firstName, lastName, emailId, and typeOfPerson.
Student adds studentId, batch, and active.
Trainer adds trainerId, coursesTaught, and active.
Course stores course information such as name, description, duration, and active status.
Enrollment stores studentId, courseId, enrollment date, and status. Its setStatus() method validates the provided status against ALLOWED_STATUS.
2. Exception Layer — com.project.exception
All custom exceptions extend StudentCourseManagementException, which extends RuntimeException.
•	StudentException
•	TrainerException
•	CourseException
•	EnrollmentException
These exceptions are thrown when invalid student, trainer, course, or enrollment operations are attempted.
3. Business Service Layer — com.project.service
This layer contains interfaces and their implementations.
Interface	Implementation	Responsibility
StudentService	StudentServiceImpl	Student CRUD operations
TrainerService	TrainerServiceImpl	Trainer CRUD operations
CourseService	CourseServiceImpl	Course management
EnrollmentService	EnrollmentServiceImpl	Student-course enrollment management

EnrollmentServiceImpl cross-references StudentServiceImpl and CourseServiceImpl to ensure that the student and course exist before creating or updating an enrollment.
MenuHandler displays menus and reads user input using a BufferedReader.
ValidatedInputHandler reads and validates trainer-related input values.
4. UI and Utility Layer
•	ConsoleUI is the application entry point and displays the main menu and module-specific menus.
•	Constants stores reusable display constants and formatting values.
•	StudentCourseUtils provides static methods to generate unique IDs.
Class Relationships
•	Student and Trainer inherit from Person
•	StudentService, TrainerService, CourseService, and EnrollmentService are implemented by their respective service implementation classes
•	A student can have zero or more enrollments
•	A course can have zero or more enrollments
•	EnrollmentServiceImpl depends on StudentServiceImpl and CourseServiceImpl
•	ConsoleUI uses MenuHandler and ValidatedInputHandler
•	Service implementations use StudentCourseUtils for ID generation
Technologies Used
•	Java
•	Java Collections Framework
•	BufferedReader for console input
•	Custom exception handling
•	Object-oriented programming concepts: inheritance, abstraction, encapsulation, interfaces, and polymorphism
How to Compile and Run
From the project root directory, compile the source files:
javac -d out $(find src -name "*.java")

Run the application:
java -cp out com.project.ui.ConsoleUI

If your source files are located directly in the current directory, use:
javac *.java
java ConsoleUI

Sample Workflow
1.	Start the application from ConsoleUI.
2.	Choose the Students menu to add a student.
3.	Choose the Courses menu to add a course.
4.	Choose the Enrollments menu to enroll the student in the course.
5.	View the student’s enrollments.
6.	Update the enrollment status using one of the allowed statuses.
Validation Rules
•	Enrollment status must belong to Enrollment.ALLOWED_STATUS
•	A student must exist before enrollment
•	A course must exist before enrollment
•	Duplicate or invalid student, trainer, course, and enrollment operations raise the relevant custom exception
Future Enhancements
•	Add persistent storage using files, JDBC, or a database
•	Add search and filtering options
•	Add course-trainer assignment functionality
•	Add unit tests using JUnit
•	Add date validation using java.time.LocalDate
•	Replace console input with a REST API or web-based interface

