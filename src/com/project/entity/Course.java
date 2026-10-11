package com.project.entity;

public class Course {
	private int courseId;
	private String courseName;
	private String courseDescription;
	private int durationInWeeks;
	private boolean active;
	
	
	public Course(String courseName,String courseDescription,int durationInWeeks,boolean active){
		this.courseName=courseName;
		this.courseDescription=courseDescription;
		this.durationInWeeks=durationInWeeks;
		this.active=active;
	}
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
	public int getDurationInWeeks() {
		return durationInWeeks;
	}
	public void setDurationInWeeks(int durationInWeeks) {
		this.durationInWeeks = durationInWeeks;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
}
