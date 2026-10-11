package com.project.entity;

import com.project.util.StudentCourseUtils;

public class Student extends Person{
	int studentId;
	String batch;
	boolean active;
	
	public Student(String firstName, String lastName, String emailId) {
		super(firstName, lastName, emailId);
		// TODO Auto-generated constructor stub
	}


	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
