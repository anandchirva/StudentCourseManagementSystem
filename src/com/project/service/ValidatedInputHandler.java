package com.project.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ValidatedInputHandler {
	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public HashMap<String, Object> getTrainerInputValues() {
		HashMap<String, Object> inputValues = new HashMap<>();
        List<String> courses = new ArrayList<>();
        try{
        	String firstName = "";
            while (firstName.trim().isEmpty()) {
                System.out.print("Enter First name: ");
                firstName = br.readLine();
                if (firstName.trim().isEmpty()) {
                    System.out.println("Error: First name cannot be empty. Please try again.");
                }
            }
            inputValues.put("FirstName", firstName.trim());
            
            String lastName = "";
            while (lastName.trim().isEmpty()) {
                System.out.print("Enter Last name: ");
                lastName = br.readLine();
                if (lastName.trim().isEmpty()) {
                    System.out.println("Error: First name cannot be empty. Please try again.");
                }
            }
            inputValues.put("LastName", lastName.trim());
            
            String emailId="";
            while (true) {
                System.out.print("Enter Email Id: ");
                emailId = br.readLine().trim();
                
                // Simple regex check for an '@' symbol and a domain extension
                if (emailId.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    break;
                } else {
                    System.out.println("Error: Invalid email format. Please include a proper '@' and domain.");
                }
            }
            inputValues.put("EmailId", emailId.trim());
            
            System.out.println("Enter Courses trainer teaches (Press Enter on an empty line to finish):");
            while (true) {
                String line = br.readLine();
                
                // Check if they pressed enter on a blank line
                if (line == null || line.trim().isEmpty()) {
                    if (courses.isEmpty()) {
                        System.out.println("Error: A trainer must teach at least one course. Please enter a course name:");
                        continue;
                    }
                    break; // Exit course input loop if they have added at least one
                }
                courses.add(line.trim());
            }
            inputValues.put("Courses", courses);
        }catch(Exception e) {
        	System.out.println("Critical error reading console input: " + e.getMessage());
        }
		return inputValues;
		
	}
}
