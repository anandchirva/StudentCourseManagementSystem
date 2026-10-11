package com.project.service;

public interface EnrollmentService {
	public String enrollStudent(int studentId, int courseId,String enrollmentDate);
	public void viewStudentEnrollments(int studentId);
	public String updateEnrollmentStatus(int enrollmentId,String status);
}
