package com.project.entity;

import java.util.List;

public class Enrollment {
	private int enrollmentId;
	private int studentId;
	private int courseId;
	private String enrollmentDate;
	public static final List<String>  ALLOWED_STATUS = List.of("ACTIVE", "COMPLETED", "CANCELLED");
	private String status;
	
	
	public int getEnrollmentId() {
		return enrollmentId;
	}
	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(String enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		if(ALLOWED_STATUS.contains(status.toUpperCase())) {
			this.status = status;
		}
	}
}
