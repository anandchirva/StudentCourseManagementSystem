package com.project.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.entity.Person;

import com.project.entity.Trainer;
import com.project.exception.CourseException;
import com.project.exception.TrainerException;
import com.project.util.Constants;
import com.project.util.StudentCourseUtils;

public class TrainerServiceImpl implements TrainerService{
	
	private static final List<Trainer> trainers= new ArrayList<>();
	
	@Override
	public String addTrainer(String firstName, String lastName, String emailId, List<String> coursesTaught) {
		StudentService ss=new StudentServiceImpl();
		// TODO Auto-generated method stub
		String msg="";
		 Trainer trainer= new Trainer(firstName,lastName,emailId);
		//Adding Person as a student
		int personId = StudentCourseUtils.generatePersonId();
		trainer.setPersonId(personId);
		trainer.setCoursesTaught(coursesTaught); 
		//Adding Person to List
		List<Person> persons=((StudentServiceImpl) ss).getPersonsList();
		persons.add(trainer);
		
		//Setting the values of Student
		int id= StudentCourseUtils.generateTrainerId();
		trainer.setTrainerId(id);
		trainer.setActive(true);
		trainer.setTypeOfPerson("TRAINER");
		boolean flag=trainers.add(trainer);
		if(flag==true) {
			msg="Trainer added successfully with ID "+id;
		}
		else {
			msg="There is an error in adding Trainer.!";
			throw new TrainerException(msg);
		}
		return msg;
	}

	@Override
	public String removeTrainer(int trainerId) {
		// TODO Auto-generated method stub
		String msg="";
		Trainer t=null;
		int index=-1;
		if(trainers!= null && trainers.size()>0) {
			for(Trainer s:trainers) {
				if(trainerId==s.getTrainerId()) {
					index = trainers.indexOf(s);
				}
			}
			if(index!=-1) {
				t=trainers.get(index);
				if(t.isActive()){
					t.setActive(false);
					trainers.set(index, t);
					msg="Trainer with id"+t.getTrainerId()+" is removed.!";
				}
				else {
					msg="Trainer with id"+t.getTrainerId()+" is already removed and not active.!";
					throw new TrainerException(msg);
				}
			}
			else {
				msg="No Trainer exists with ID "+trainerId+". Verify the Id once again.!";
				throw new TrainerException(msg);
			}
		}
		else {
			msg = "There are no trainers added to the list.!";
			throw new TrainerException(msg);
		}
		return msg;
	}

	@Override
	public String updateTrainer(Trainer t) {
		// TODO Auto-generated method stub
		int index=-1;
		String msg="";
		for(Trainer st:trainers) {
			if(st.getTrainerId()==t.getTrainerId()) {
				index=trainers.indexOf(st);
			}
		}
		if(index != -1) {
			trainers.set(index, t);
			msg="Trainer Record updated successfully.!";
		}
		else {
			msg="There is an error on updating the trainer record.! Verify the trainer fields again.! ";
			throw new TrainerException(msg);
		}
		return msg;
	}

	@Override
	public void listTrainers() {
		// TODO Auto-generated method stub
		if(trainers.size() >0) {
			displayTrainersHeader();
			displayTrainersData();
		}else {
			displayTrainersData();
			System.out.println("There are no trainers added in the system.");
			System.out.println(Constants.SINGLELINE);
		}
	}

	private void displayTrainersHeader() {
		// TODO Auto-generated method stub
		System.out.println(Constants.SINGLELINE);
		System.out.println(Constants.TRAINERSDISPLAYHEADER);
		System.out.println(Constants.SINGLELINE);
		System.out.println("S.No|"+Constants.TRAINERID+"|"+Constants.FIRSTNAME+"|"+Constants.LASTNAME+"|"+Constants.EMAILID+"|"+Constants.ACTIVE+"|"+Constants.COURSESTAUGHT);
		System.out.println(Constants.SINGLELINE);
	}

	private void displayTrainersData() {
		// TODO Auto-generated method stub
		String result="";
		for(int i=0;i<trainers.size();i++) {
			Trainer s= trainers.get(i);
			int j=i+1;
			result = String.join(", ", s.getCoursesTaught());
			System.out.println(j+"   |"+s.getTrainerId()+" |"+s.getFirstName()+" |"+s.getLastName()+" |"+s.getEmailId()+" |"+s.isActive()+" |"+result);
		}
		System.out.println(Constants.SINGLELINE);
	}
	

	public static List<Trainer> getTrainersList() {
		// TODO Auto-generated method stub
		return trainers;
	}

}
