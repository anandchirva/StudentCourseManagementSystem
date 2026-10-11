package com.project.service;

import com.project.entity.Student;

public interface StudentService {
	
	public String addStudent(String firstName, String lastName, String emailId,String batch);
	public String removeStudent(int studentId);
	public String updateStudent(Student s);
	public void listStudents();
	

}
