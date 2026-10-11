package com.project.entity;

public class Person {
	
	private int personId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String typeOfPerson;
	
	Person(String firstName,String lastName,String emailId){
		this.firstName=firstName;
		this.lastName= lastName;
		this.emailId=emailId;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getTypeOfPerson() {
		return typeOfPerson;
	}
	public void setTypeOfPerson(String typeOfPerson) {
		this.typeOfPerson = typeOfPerson;
	}
}
