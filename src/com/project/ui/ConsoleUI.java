package com.project.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.project.service.MenuHandler;
import com.project.service.TrainerService;
import com.project.service.TrainerServiceImpl;
import com.project.service.ValidatedInputHandler;
import com.project.util.Constants;

public class ConsoleUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*This is the UI for user input and appearance*/
		System.out.println(Constants.ANSI_BOLD_BLUE+Constants.SINGLELINE);
		System.out.println(Constants.MAINHEADER);
		System.out.println(Constants.SINGLELINE);
		boolean flag=true;
		int menuChoice=0;
		while(flag) {
			menuChoice = MenuHandler.mainMenu();
			switch(menuChoice) {
			case 1:
				MenuHandler.displayTrainersMenu();
				break;
			case 2:
				MenuHandler.displayStudentsMenu();
				break;
			case 3:
				MenuHandler.displayCoursesMenu();;
				break;
			case 4:
				MenuHandler.displayEnrollmentsMenu();
				break;
			case 5:
				 flag=false;
				 break;
			default:
				  System.out.println("Enter Valid Option");
				  menuChoice=MenuHandler.mainMenu();
				  break;
			}
			
		}
	}
}
