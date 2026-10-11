package com.project.service;

public interface CourseService {
	public String addCourse(String courseName,String courseDescription,int durationInWeeks,boolean active);
	public void viewCourses();
	public String activateCourse(int courseId);
	public String deactivateCourse(int courseId);

}
