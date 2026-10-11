package com.project.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.project.entity.Enrollment;
import com.project.entity.Student;
import com.project.entity.Trainer;
import com.project.util.Constants;

public class MenuHandler {
	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int mainMenu() {
		// TODO Auto-generated method stub
		while(true) {
			try{
			System.out.println(Constants.SINGLELINE);
			System.out.println("1. Trainers");
			System.out.println("2. Students");
			System.out.println("3. Courses");
			System.out.println("4. Enrollments");
			System.out.println("5. Exit");
			System.out.println(Constants.SINGLELINE);
			System.out.print("Enter your choice: ");
			
				String input = br.readLine();
				if (input == null) {
					return 5; // Safe fallback exit code if stream breaks
				}
				int choice = Integer.parseInt(input.trim());
				if (choice >= 1 && choice <= 5) {
					return choice; // Exit the loop and return the clean, valid selection
				} else {
					System.out.println("Error: Choice must be from 1 to 5 only. Please try again.");
				}
			}catch (NumberFormatException e) {
				System.out.println("Error: Invalid format. Enter values in digits only.");
				System.out.println("The choice must be from 1 to 5 only.");
				return -1; // Returns invalid flag to let the main switch loop handle it
			}catch (com.project.exception.TrainerException | 
	                 com.project.exception.StudentException | 
	                 com.project.exception.CourseException | 
	                 com.project.exception.EnrollmentException e) {

				System.out.println("\n System Validation Error: " + e.getMessage());
	            System.out.println("Please try your selection again.");
	            continue;
	            
	        }
			catch (IOException e) {
				 System.out.println("Console reading error: " + e.getMessage() + ". Attempting to retry...");
				    
				    try {
				        if (!br.ready()) {
				            System.out.println(" Critical system failure: Keyboard stream is unresponsive. Exiting program.");
				            return 5; 
				        }
				    } catch (IOException ignored) {
				        return 5;
				    }
				    
				    continue;
			}
		}
				
	}
	public static BufferedReader getReader() {
		return br;
	}
	
	public static void displayTrainersMenu() {
		boolean flag = true;
	    TrainerService trainerService = new TrainerServiceImpl();
	    ValidatedInputHandler inputHandler = new ValidatedInputHandler();
	    
	    BufferedReader br = MenuHandler.getReader();

	    while (flag) {
	    	try{
	    	System.out.println(Constants.SINGLELINE);
			System.out.println("1. Add Trainer");
			System.out.println("2. Remove Trainer");
			System.out.println("3. Update Trainer");
			System.out.println("4. List Trainers");
			System.out.println("5. Go to Main Menu");
			System.out.println(Constants.SINGLELINE);
			System.out.print("Enter your choice (1-5): ");
			
			String input = br.readLine();
            if (input == null) {
                System.out.println("Terminal stream disconnected.");
                break;
            }
            int choice = Integer.parseInt(input.trim());
            Map<String, Object> inputValues;
            
            switch(choice) {
            case 1:
            	inputValues = inputHandler.getTrainerInputValues();
                String firstName = inputValues.get("FirstName").toString();
                String lastName = inputValues.get("LastName").toString();
                String emailId = inputValues.get("EmailId").toString();
                List<String> courses = (List<String>) inputValues.get("Courses");
                
                String addResponse = trainerService.addTrainer(firstName, lastName, emailId, courses);
                System.out.println("\n" + addResponse);
                break;
            case 2:
            	
            	 if (TrainerServiceImpl.getTrainersList().isEmpty()) {
            	        System.out.println("Error: There are no trainers registered in the system to remove.");
            	        continue; // Instantly stops this action block and redraws the Trainer Menu options
            	    }
            	
            	System.out.println("\nAvailable Trainers in System:");
                System.out.println("LIST OF ACTIVE TRAINERS");
                
                trainerService.listTrainers();
            	System.out.print("Enter Trainer ID to remove: ");
                String removeInput = br.readLine();
                if (removeInput != null && !removeInput.trim().isEmpty()) {
                    int removeId = Integer.parseInt(removeInput.trim());
                    String removeResponse = trainerService.removeTrainer(removeId);
                    System.out.println("\n" + removeResponse);
                } else {
                    System.out.println("\n❌ Error: Trainer ID cannot be empty.");
                }
                break;
                
            case 3:
            	System.out.println("\n--- Update Trainer  ---");
            	
            	System.out.println("LIST OF TRAINERS");
                trainerService.listTrainers();
            	System.out.print("Enter Trainer ID to update: ");
            	String updateInput = br.readLine();
            	if (updateInput != null && !updateInput.trim().isEmpty()) {
                    int updateId = Integer.parseInt(updateInput.trim());
                    
                    Map<String, Object> updatedData = inputHandler.getTrainerInputValues();
                    Trainer updatedTrainer = new Trainer(
                        updatedData.get("FirstName").toString(),
                        updatedData.get("LastName").toString(),
                        updatedData.get("EmailId").toString()
                    );
                    updatedTrainer.setTrainerId(updateId);
                    updatedTrainer.setCoursesTaught((List<String>) updatedData.get("Courses"));
                    updatedTrainer.setActive(true);
                    String updateResponse = trainerService.updateTrainer(updatedTrainer);
                    System.out.println("\n" + updateResponse);
                }else {
                    System.out.println("\n Error: Trainer ID cannot be empty.");
                }
            	break;
            case 4:
            	trainerService.listTrainers();
                break;
            case 5:
            	System.out.println("Returning to Main Menu...");
                flag = false;
                break;
            default:
                System.out.println("\n Error: Choice must be between 1 and 5 only. Please try again.");
                continue; 
            }
			
	    }catch (NumberFormatException e) {
            System.out.println("\n Error: Input contains invalid characters or symbols. Please input numbers only.");
        } catch (IOException e) {
            System.out.println("\n Critical error running inner input loop stream: " + e.getMessage());
            flag = false;
        }catch (com.project.exception.TrainerException | 
                com.project.exception.StudentException | 
                com.project.exception.CourseException | 
                com.project.exception.EnrollmentException e) {
           
           System.out.println("\n System Validation Error: " + e.getMessage());
           System.out.println("Please try your selection again.");
           continue;
           
       }catch (Exception e) {
            System.out.println("\n App Error: " + e.getMessage());
            System.out.println("Please try your selection again.");
        }
	   }
	}
	
	public static void displayStudentsMenu() {
	    boolean flag = true;
	    StudentService studentService = new StudentServiceImpl();
	    ValidatedInputHandler inputHandler = new ValidatedInputHandler();
	    
	    // Fetch the shared reader instance from MenuHandler to keep the stream unified
	    BufferedReader br = MenuHandler.getReader();

	    while (flag) {
	        try {
	            System.out.println(Constants.SINGLELINE);
	            System.out.println("1. Add Student");
	            System.out.println("2. Remove Student");
	            System.out.println("3. Update Student");
	            System.out.println("4. List Students");
	            System.out.println("5. Go to Main Menu");
	            System.out.println(Constants.SINGLELINE);
	            System.out.print("Enter your choice (1-5): ");

	            String input = br.readLine();
	            if (input == null) {
	                System.out.println("\n❌ Terminal stream disconnected.");
	                break;
	            }

	            int choice = Integer.parseInt(input.trim());

	            switch (choice) {
	                case 1:
	                    // 1. Add Student Flow
	                    // We use custom loop handles here for the specific fields matching addStudent
	                    String firstName = "";
	                    while (firstName.trim().isEmpty()) {
	                        System.out.print("Enter First name: ");
	                        firstName = br.readLine();
	                    }

	                    String lastName = "";
	                    while (lastName.trim().isEmpty()) {
	                        System.out.print("Enter Last name: ");
	                        lastName = br.readLine();
	                    }

	                    String emailId = "";
	                    while (!emailId.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
	                        System.out.print("Enter Email Id: ");
	                        emailId = br.readLine().trim();
	                    }

	                    System.out.print("Enter Batch name: ");
	                    String batch = br.readLine().trim();
	                    
	                    String addResponse = studentService.addStudent(firstName.trim(), lastName.trim(), emailId, batch);
	                    System.out.println("\n" + addResponse);
	                    break;

	                case 2:
	                    // 2. Remove Student Flow
	                    System.out.println("\n--- Remove Student  ---");
	                    System.out.println("Available Students in System:");
	                    studentService.listStudents(); // Display student records before requesting option selection
	                    
	                    System.out.print("Enter Student ID to remove: ");
	                    String removeInput = br.readLine();
	                    if (removeInput != null && !removeInput.trim().isEmpty()) {
	                        int removeId = Integer.parseInt(removeInput.trim());
	                        String removeResponse = studentService.removeStudent(removeId);
	                        System.out.println("\n" + removeResponse);
	                    } else {
	                        System.out.println("\n Error: Student ID cannot be empty.");
	                    }
	                    break;

	                case 3:
	                    // 3. Update Student Flow
	                    System.out.println("\n Update Student  ---");
	                    System.out.println("Available Students in System:");
	                    studentService.listStudents(); // Display student records before requesting option selection
	                    
	                    System.out.print("Enter the Student ID to update: ");
	                    String updateIdInput = br.readLine();
	                    if (updateIdInput != null && !updateIdInput.trim().isEmpty()) {
	                        int updateId = Integer.parseInt(updateIdInput.trim());
	                        
	                        System.out.println("Enter updated details for this Student:");
	                        System.out.print("Enter New First name: ");
	                        String updatedFirst = br.readLine().trim();
	                        System.out.print("Enter New Last name: ");
	                        String updatedLast = br.readLine().trim();
	                        System.out.print("Enter New Email Id: ");
	                        String updatedEmail = br.readLine().trim();
	                        System.out.print("Enter New Batch: ");
	                        String updatedBatch = br.readLine().trim();
	                        
	                        // Populate a clean Student object to pass over into the implementation interface mapping
	                        Student updatedStudentObj = new Student(updatedFirst, updatedLast, updatedEmail);
	                        updatedStudentObj.setStudentId(updateId);
	                        updatedStudentObj.setBatch(updatedBatch);
	                        updatedStudentObj.setActive(true);
	                        
	                        String updateResponse = studentService.updateStudent(updatedStudentObj);
	                        System.out.println("\n" + updateResponse);
	                    } else {
	                        System.out.println("\n Error: Student ID cannot be empty.");
	                    }
	                    break;

	                case 4:
	                    // 4. List Students Flow
	                    studentService.listStudents();
	                    break;

	                case 5:
	                    // 5. Graceful back-link to Master Menu loop
	                    System.out.println("Returning to Main Menu...");
	                    flag = false;
	                    break;

	                default:
	                    System.out.println("\n Error: Choice must be between 1 and 5 only. Please try again.");
	                    continue; 
	            }

	        } catch (NumberFormatException e) {
	            System.out.println("\n Error: Input contains invalid characters or symbols. Please input numbers only.");
	        } catch (com.project.exception.TrainerException | 
	                 com.project.exception.StudentException | 
	                 com.project.exception.CourseException | 
	                 com.project.exception.EnrollmentException e) {

	        	System.out.println("\n System Validation Error: " + e.getMessage());
	            System.out.println("Please try your selection again.");
	            continue;
	            
	        }catch (IOException e) {
	            System.out.println("\n Critical error running inner input loop stream: " + e.getMessage());
	            flag = false;
	        } catch (Exception e) {
	            System.out.println("\n App Error: " + e.getMessage());
	            System.out.println("Please try your selection again.");
	        }
	    }
	}
	
	public static void displayCoursesMenu() {
	    boolean flag = true;
	    CourseService courseService = new CourseServiceImpl();
	    
	    // Fetch the shared reader instance from MenuHandler to keep the stream unified
	    BufferedReader br = MenuHandler.getReader();

	    while (flag) {
	        try {
	            System.out.println(Constants.SINGLELINE);
	            System.out.println("1. Add Course");
	            System.out.println("2. View Courses");
	            System.out.println("3. Activate Course");
	            System.out.println("4. Deactivate Course");
	            System.out.println("5. Go to Main Menu");
	            System.out.println(Constants.SINGLELINE);
	            System.out.print("Enter your choice (1-5): ");

	            String input = br.readLine();
	            if (input == null) {
	                System.out.println("\n Terminal stream disconnected.");
	                break;
	            }

	            int choice = Integer.parseInt(input.trim());

	            switch (choice) {
	                case 1:
	                    // 1. Add Course Flow
	                    String courseName = "";
	                    while (courseName.trim().isEmpty()) {
	                        System.out.print("Enter Course Name: ");
	                        courseName = br.readLine();
	                        if (courseName.trim().isEmpty()) {
	                            System.out.println(" Error: Course name cannot be empty.");
	                        }
	                    }
	                    
	                    String courseDesc="";
	                    while(courseDesc.trim().isEmpty()) {
	                    	System.out.print("Enter Course Description: ");
		                    courseDesc = br.readLine().trim();
		                    if(courseDesc.trim().isEmpty()) {
		                    	System.out.println(" Error: Course Description cannot be empty.");
		                    }
	                    }
	                    

	                    int duration = 0;
	                    while (duration <= 0) {
	                        System.out.print("Enter Duration (in weeks): ");
	                        try {
	                            duration = Integer.parseInt(br.readLine().trim());
	                            if (duration <= 0) {
	                                System.out.println(" Error: Duration must be greater than 0.");
	                            }
	                        } catch (NumberFormatException e) {
	                            System.out.println(" Error: Please enter a valid number for duration.");
	                        }
	                    }

	                    // Default new courses to active state upon creation
	                    String addResponse = courseService.addCourse(courseName.trim(), courseDesc.trim(), duration, true);
	                    System.out.println("\n" + addResponse);
	                    break;

	                case 2:
	                    // 2. View Courses Flow
	                    courseService.viewCourses();
	                    break;

	                case 3:
	                    // 3. Activate Course Flow
	                    System.out.println("\n--- Activate Course ---");
	                    System.out.println("Current Courses Status:");
	                    courseService.viewCourses(); // Display courses with status before asking for ID selection
	                    
	                    System.out.print("Enter Course ID to activate: ");
	                    String actInput = br.readLine();
	                    if (actInput != null && !actInput.trim().isEmpty()) {
	                        int actId = Integer.parseInt(actInput.trim());
	                        String actResponse = courseService.activateCourse(actId);
	                        System.out.println("\n" + actResponse);
	                    } else {
	                        System.out.println("\n Error: Course ID cannot be empty.");
	                    }
	                    break;

	                case 4:
	                    // 4. Deactivate Course Flow
	                    System.out.println("\n--- Deactivate Course  ---");
	                    System.out.println("Current Courses Status:");
	                    courseService.viewCourses(); // Display courses with status before asking for ID selection
	                    
	                    System.out.print("Enter Course ID to deactivate: ");
	                    String deactInput = br.readLine();
	                    if (deactInput != null && !deactInput.trim().isEmpty()) {
	                        int deactId = Integer.parseInt(deactInput.trim());
	                        String deactResponse = courseService.deactivateCourse(deactId);
	                        System.out.println("\n" + deactResponse);
	                    } else {
	                        System.out.println("\n Error: Course ID cannot be empty.");
	                    }
	                    break;

	                case 5:
	                    // 5. Return back to Master Menu loop
	                    System.out.println("Returning to Main Menu...");
	                    flag = false;
	                    break;

	                default:
	                    System.out.println("\n Error: Choice must be between 1 and 5 only. Please try again.");
	                    continue;
	            }

	        } catch (NumberFormatException e) {
	            System.out.println("\n Error: Input contains invalid characters or symbols. Please input numbers only.");
	        }catch (com.project.exception.TrainerException | 
	                 com.project.exception.StudentException | 
	                 com.project.exception.CourseException | 
	                 com.project.exception.EnrollmentException e) {
	            
	            System.out.println("\n System Validation Error: " + e.getMessage());
	            System.out.println("Please try your selection again.");
	            continue;
	            
	        } catch (IOException e) {
	            System.out.println("\n Critical error running inner input loop stream: " + e.getMessage());
	            flag = false;
	        } catch (Exception e) {
	            System.out.println("\n App Error: " + e.getMessage());
	            System.out.println("Please try your selection again.");
	        }
	    }
	}
	public static void displayEnrollmentsMenu() {
	    boolean flag = true;
	    EnrollmentService enrollmentService = new EnrollmentServiceImpl();
	    StudentService studentService = new StudentServiceImpl();
	    CourseService courseService = new CourseServiceImpl();
	    
	    // Fetch the shared reader instance from MenuHandler to keep the stream unified
	    BufferedReader br = MenuHandler.getReader();

	    while (flag) {
	        try {
	            System.out.println(Constants.SINGLELINE);
	            System.out.println("1. Enroll Student in Course");
	            System.out.println("2. View Student Enrollments");
	            System.out.println("3. Update Enrollment Status");
	            System.out.println("4. Go to Main Menu");
	            System.out.println(Constants.SINGLELINE);
	            System.out.print("Enter your choice (1-4): ");

	            String input = br.readLine();
	            if (input == null) {
	                System.out.println("\n Terminal stream disconnected.");
	                break;
	            }

	            int choice = Integer.parseInt(input.trim());

	            switch (choice) {
	                case 1:
	                    // 1. Enroll Student Flow
	                    System.out.println("\n--- New Enrollment  ---");
	                    
	                    // Fail-fast verification: check if there are records available to pair
	                    if (StudentServiceImpl.getStudentsList().isEmpty() || CourseServiceImpl.getCoursesList().isEmpty()) {
	                        System.out.println("\n Error: Enrollment requires at least one student and one active course in the system.");
	                        continue;
	                    }

	                    System.out.println("Available Students:");
	                    studentService.listStudents();
	                    System.out.print("Enter Student ID to enroll: ");
	                    int studentId = Integer.parseInt(br.readLine().trim());

	                    System.out.println("\nAvailable Courses:");
	                    courseService.viewCourses();
	                    System.out.print("Enter Course ID for enrollment: ");
	                    int courseId = Integer.parseInt(br.readLine().trim());
	                    
	                    System.out.print("Enter Enrollment Date (Format: dd-MMM-yyyy, e.g., 11-Oct-2026): ");
	                    String enrollmentDate = br.readLine();
	                    if (enrollmentDate == null || enrollmentDate.trim().isEmpty()) {
	                        System.out.println("❌ Error: Enrollment date cannot be empty.");
	                        continue;
	                    }


	                    String enrollMsg = enrollmentService.enrollStudent(studentId, courseId,enrollmentDate);
	                    System.out.println("\n" + enrollMsg);
	                    break;

	                case 2:
	                    // 2. View Student Enrollments Flow
	                    System.out.println("\n--- View Student Enrollments ---");
	                    if (StudentServiceImpl.getStudentsList().isEmpty()) {
	                        System.out.println("Error: There are no students registered in the system.");
	                        continue;
	                    }

	                    System.out.println("Available Students:");
	                    studentService.listStudents();
	                    System.out.print("Enter Student ID to check enrollments: ");
	                    int viewStudentId = Integer.parseInt(br.readLine().trim());

	                    enrollmentService.viewStudentEnrollments(viewStudentId);
	                    break;

	                case 3:
	                    // 3. Update Enrollment Status Flow
	                    System.out.println("\n--- Update Enrollment Status ---");
	                    System.out.print("Enter the Enrollment ID to update: ");
	                    String enrollIdInput = br.readLine();
	                    if (enrollIdInput == null || enrollIdInput.trim().isEmpty()) {
	                        System.out.println("Error: Enrollment ID cannot be empty.");
	                        continue;
	                    }
	                    int enrollmentId = Integer.parseInt(enrollIdInput.trim());

	                    System.out.println("Allowed statuses: " + Enrollment.ALLOWED_STATUS);
	                    System.out.print("Enter new status: ");
	                    String newStatus = br.readLine();
	                    if (newStatus == null || newStatus.trim().isEmpty()) {
	                        System.out.println("Error: Status string cannot be empty.");
	                        continue;
	                    }

	                    String updateMsg = enrollmentService.updateEnrollmentStatus(enrollmentId, newStatus.trim());
	                    System.out.println("\n" + updateMsg);
	                    break;

	                case 4:
	                    // 4. Return back to Master Menu loop
	                    System.out.println("Returning to Main Menu...");
	                    flag = false;
	                    break;

	                default:
	                    System.out.println("\n Error: Choice must be between 1 and 4 only. Please try again.");
	                    continue;
	            }

	        } catch (NumberFormatException e) {
	            System.out.println("\n Error: Input contains invalid characters or symbols. Please input numbers only.");
	        }catch (com.project.exception.TrainerException | 
	                 com.project.exception.StudentException | 
	                 com.project.exception.CourseException | 
	                 com.project.exception.EnrollmentException e) {
	            
	            System.out.println("\n System Validation Error: " + e.getMessage());
	            System.out.println("Please try your selection again.");
	            continue;
	            
	        } catch (IOException e) {
	            System.out.println("\n Critical error running inner input loop stream: " + e.getMessage());
	            flag = false;
	        } catch (Exception e) {
	            System.out.println("\n App Error: " + e.getMessage());
	            System.out.println("Please try your selection again.");
	        }
	    }
	}


}
