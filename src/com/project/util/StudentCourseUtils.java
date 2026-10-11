package com.project.util;

public class StudentCourseUtils {
	
	public static Integer personId=0;
	public static Integer studentId=0;
	public static Integer trainerId=0;
	public static Integer enrollmentId=0;
	
	public static synchronized Integer generatePersonId() {
		personId=personId+1;
		return personId;
	}
	
	public static synchronized Integer generateStudentId() {
		studentId=studentId+1;
		return studentId;
	}
	
	public static synchronized Integer generateTrainerId() {
		trainerId=trainerId+1;
		return trainerId;
	}
	public static synchronized Integer generateEnrollmentId() {
		enrollmentId=enrollmentId+1;
		return enrollmentId;
	}
}
