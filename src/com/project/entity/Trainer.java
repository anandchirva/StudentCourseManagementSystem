package com.project.entity;

import java.util.List;


public class Trainer extends Person{
	
	private int trainerId;
	private List<String> coursesTaught;
	private boolean active;

	public Trainer(String firstName, String lastName, String emailId) {
		super(firstName, lastName, emailId);
		// TODO Auto-generated constructor stub
	}

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}

	public List<String> getCoursesTaught() {
		return coursesTaught;
	}

	public void setCoursesTaught(List<String> coursesTaught) {
		this.coursesTaught = coursesTaught;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
