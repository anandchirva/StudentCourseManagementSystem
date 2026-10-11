package com.project.service;

import java.util.ArrayList;
import java.util.List;

import com.project.entity.Person;
import com.project.entity.Student;
import com.project.exception.CourseException;
import com.project.exception.StudentException;
import com.project.util.Constants;
import com.project.util.StudentCourseUtils;

public class StudentServiceImpl implements StudentService{
	
	private static final List<Student> students= new ArrayList<>();
	private static final List<Person> persons=new ArrayList<>();

	@Override
	public String addStudent(String firstName, String lastName, String emailId,String batch) {
		// TODO Auto-generated method stub
		String msg="";
		Student student= new Student(firstName,lastName,emailId);
		//Adding Person as a student
		int personId = StudentCourseUtils.generatePersonId();
		student.setPersonId(personId);
		//Adding Person to List
		persons.add(student);
		
		//Setting the values of Student
		int id= StudentCourseUtils.generateStudentId();
		student.setStudentId(id);
		student.setBatch(batch);
		student.setActive(true);
		student.setTypeOfPerson("STUDENT");
		boolean flag=students.add(student);
		if(flag==true) {
			msg="Student added successfully with ID "+id;
		}
		else {
			msg="There is an error in adding Student.!";
			throw new StudentException(msg);
		}
		return msg;
	}

	@Override
	public String removeStudent(int studentId) {
		// TODO Auto-generated method stub
		String msg="";
		Student st=null;
		int index=-1;
		if (students == null || students.isEmpty()) {
	        throw new StudentException("There are no students added to the list.!");
	    }
		if(students!= null && students.size()>0) {
			for(Student s:students) {
				if(studentId==s.getStudentId()) {
					index=students.indexOf(s);
					break;
				}
			}
			if(index !=-1) {
				st=students.get(index);
				if(st.isActive()) {
					st.setActive(false);
					msg="Student removed with Id "+studentId+" successfully.";
				}
				else {
					msg="Student with Id"+studentId+" is already removed from the list and is inactive";
					throw new StudentException(msg);
				}
				
			}
			else {
				msg="No Student exists with ID "+studentId+". Verify the Id once again.!";
				throw new StudentException(msg);
			}
		}
		else {
			msg = "There are no students added to the list.!";
			throw new StudentException(msg);
		}
		return msg;
	}

	@Override
	public String updateStudent(Student s) {
		// TODO Auto-generated method stub
		int index=-1;
		String msg="";
		for(Student st:students) {
			if(st.getStudentId()==s.getStudentId()) {
				index=students.indexOf(st);
			}
		}
		if(index != -1) {
			students.set(index, s);
			msg="Student Record updated successfully.!";
		}
		else {
			msg="There is an error on updating the student record.! Verify the student fields again.! ";
			throw new StudentException(msg);
		}
		return msg;
	}

	@Override
	public void listStudents() {
		// TODO Auto-generated method stub
		if(students.size() >0) {
			displayStudentsHeader();
			displayStudentsData();
		}else {
			displayStudentsHeader();
			System.out.println("There are no students added in the system.");
			System.out.println(Constants.SINGLELINE);
		}
		
	}
	
	private void displayStudentsData() {
		// TODO Auto-generated method stub
		for(int i=0;i<students.size();i++) {
			Student s= students.get(i);
			int j=i+1;
			System.out.println(j+"   |"+s.getStudentId()+" |"+s.getFirstName()+" |"+s.getLastName()+" |"+s.getEmailId()+" |"+s.getBatch()+" |"+s.isActive());
		}
		System.out.println(Constants.SINGLELINE);
		
	}

	private void displayStudentsHeader() {
		// TODO Auto-generated method stub
		System.out.println(Constants.SINGLELINE);
		System.out.println(Constants.STUDENTSDISPLAYHEADER);
		System.out.println(Constants.SINGLELINE);
		System.out.println("S.No|"+Constants.STUDENTID+"|"+Constants.FIRSTNAME+"|"+Constants.LASTNAME+"|"+Constants.EMAILID+"|"+Constants.BATCH+"|"+Constants.ACTIVE);
		System.out.println(Constants.SINGLELINE);
	}

	public boolean studentIfExists(int studentId) {
		boolean flag=false;
		long count=0;
		if(students.size()>0) {
			count=students.stream().filter(s->(s.getStudentId()==studentId)).count();
			if(count>0) {
				flag=true;
			}
			else
				flag=false;
		}
		else {
			flag=false;
		}
		return flag;
	}
	
	public static List<Person> getPersonsList(){
		return persons;
	}
	
	public static List<Student> getStudentsList(){
		return students;
	} 
}
