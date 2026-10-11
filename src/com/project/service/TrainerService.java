package com.project.service;

import java.util.List;

import com.project.entity.Trainer;

public interface TrainerService {
	public String addTrainer(String firstName, String lastName, String emailId,List<String> coursesTaught);
	public String removeTrainer(int trainerId);
	public String updateTrainer(Trainer t);
	public void listTrainers();
}
