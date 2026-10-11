package com.project.service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.project.entity.Enrollment;
import com.project.exception.EnrollmentException;
import com.project.util.Constants;
import com.project.util.StudentCourseUtils;

public class EnrollmentServiceImpl implements EnrollmentService{
	
	private static final List<Enrollment> enrollments = new ArrayList<>();
	
	@Override
	public String enrollStudent(int studentId, int courseId, String enrollmentDate) {
	    boolean studentExists = StudentServiceImpl.getStudentsList().stream().anyMatch(s -> s.getStudentId() == studentId);
	    boolean courseExists = CourseServiceImpl.getCoursesList().stream().anyMatch(c -> c.getCourseId() == courseId);
	    
	    // 1. THROW CUSTOM EXCEPTIONS: Validate student and course presence
	    if (!studentExists && !courseExists) {
	        throw new EnrollmentException("Enter the valid StudentId and CourseId for enrollment.");
	    }
	    if (!studentExists) {
	        throw new EnrollmentException("Enter the valid StudentId for enrollment.");
	    }
	    if (!courseExists) {
	        throw new EnrollmentException("Enter the valid CourseId for enrollment.");
	    }

	    // 2. DATE PARSING & VALIDATION ENGINE
	    try {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", java.util.Locale.ENGLISH);
	        LocalDate inputDate = LocalDate.parse(enrollmentDate.trim(), formatter);
	        LocalDate currentDate = LocalDate.now();
	        
	        // Check if date is in the past
	        if (inputDate.isBefore(currentDate)) {
	            throw new EnrollmentException("Enrollment Failed: The provided date (" + enrollmentDate + ") is in the past! It must be today or a future date.");
	        }
	    } catch (java.time.format.DateTimeParseException e) {
	        // Intercept formatting failures and wrap them into your business domain exception
	        throw new EnrollmentException("Enrollment Failed: Date format invalid! Please ensure it matches 'dd-MMM-yyyy' (e.g., 11-Oct-2026).");
	    }

	    // 3. SUCCESS PATH: Create and add the enrollment record
	    Enrollment enrollment = new Enrollment();
	    int eid = StudentCourseUtils.generateEnrollmentId();
	    enrollment.setEnrollmentId(eid);
	    enrollment.setStudentId(studentId);
	    enrollment.setCourseId(courseId);
	    enrollment.setEnrollmentDate(enrollmentDate.trim()); 
	    enrollment.setStatus("ACTIVE");
	    
	    enrollments.add(enrollment);
	    return "Student with id " + studentId + " Enrolled for the course " + courseId + " successfully.";
	}


	@Override
	public void viewStudentEnrollments(int studentId) {
		// TODO Auto-generated method stub
		
		displayEnrollmentsHeader();
		List<Enrollment> studentRecords = new ArrayList<>();
		for (Enrollment e : enrollments) {
			if (e.getStudentId() == studentId) {
				studentRecords.add(e);
			}
		}
		
		if (studentRecords.isEmpty()) {
			System.out.println(Constants.SINGLELINE);
			throw new EnrollmentException("There are no enrollments to display for Student ID: " + studentId);
		}
		for (int i = 0; i < studentRecords.size(); i++) {
			int j = i + 1;
			Enrollment e = studentRecords.get(i);
			System.out.println(j + "|" + e.getEnrollmentId() + "|" + e.getStudentId() + "|" + e.getCourseId() + "|" + e.getEnrollmentDate() + "|" + e.getStatus());
		}
		System.out.println(Constants.SINGLELINE);
	}

	private void displayEnrollmentsHeader() {
		// TODO Auto-generated method stub
		System.out.println(Constants.SINGLELINE);
		System.out.println(Constants.STUDENTSENROLLMENTHEADER);
		System.out.println("S.No "+"|"+Constants.ENROLLMENTID+"|"+Constants.STUDENTID+"|"+Constants.COURSEID+"|"+Constants.ENROLLMENTDATE+"|"+Constants.ENROLLMENTSTATUS);
		System.out.println(Constants.SINGLELINE);
	}

	@Override
	public String updateEnrollmentStatus(int enrollmentId,String status) {
		// TODO Auto-generated method stub
		
		String msg = "";
		int index = -1;
		Enrollment e = null;
		
		if (enrollments.isEmpty()) {
			msg = "There are no enrollments in the system to update.";
			throw new EnrollmentException(msg);
		}
		
		for (int i = 0; i < enrollments.size(); i++) {
			if (enrollments.get(i).getEnrollmentId() == enrollmentId) {
				index = i;
				e = enrollments.get(i);
				break;
			}
		}
		
		if (index == -1) {
			msg = "There is no enrollment with Id " + enrollmentId + " in the system.";
			throw new EnrollmentException(msg);
		}
		if (e != null) {
			// CRITICAL FIX 5: Simplified status check logic for smooth operational tracking
			if (!e.getStatus().equalsIgnoreCase(status)) {
				e.setStatus(status.toUpperCase().trim());
				enrollments.set(index, e);
				msg = "Enrollment is updated successfully.";
			} else {
				msg = "Enrollment is already in " + status.toUpperCase() + " status.";
				throw new EnrollmentException(msg);
			}
		}
		return msg;
	}
	
}
