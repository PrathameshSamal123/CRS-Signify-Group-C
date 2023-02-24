/**
 * 
 */
package com.signify.client;

import java.util.Scanner;

import com.signify.bean.User;
import com.signify.client.CRSApplicationMenu;
import com.signify.service.ProfessorInterface;
import com.signify.service.ProfessorServiceOperations;
import com.signify.service.StudentInterface;
import com.signify.service.StudentServiceOperations;

/**
 * @author Acer
 *
 */
public class CRSProfessorMenu {
	
	ProfessorInterface professorObject = new ProfessorServiceOperations();
	
	/**
	 * Method to display professor Menu
	 * @param user
	 */
	public void displayProfessorMenu(User user) {
		// TODO Auto-generated method stub
	System.out.print("\n================================\n");
	  System.out.println("Logged in as "+user.getName());
	  System.out.println("================================");
			
		
      System.out.println("\nWELCOME TO PROFESSOR MENU");
      System.out.println("===========================\n");
      System.out.println("PROFESSOR MENU");
		System.out.println("1. View My Courses");
		System.out.println("2. View Enrolled Students");
		System.out.println("3. Add Grade");
		System.out.println("4. Exit\n");

		int ProfessorChoice = 0;
		
		ProfessorInterface professorObject = new ProfessorServiceOperations();
		
		boolean flag;
		
		do
		{
			try {
				System.out.print("Enter your Choice: ");
				Scanner scan = new Scanner(System.in);
				ProfessorChoice = scan.nextInt();
				flag=false;
			}
			catch(Exception e)
			{
				System.out.println("======================================================");
				System.out.println("Please Select from above options only, try again");
				System.out.println("======================================================");
				flag = true;
			}
		}while(flag);
		
		
		switch(ProfessorChoice) {
		
		case 1: 
			professorObject.viewMyCourses(user);
		displayProfessorMenu(user) ;
	
		break;
		
		case 2: 
			
			System.out.print("\nEnter Course Code to View Students: ");
			Scanner scan = new Scanner(System.in);
			String courseCode = scan.nextLine();
			professorObject.viewEnrolledStudents(user, courseCode);
		displayProfessorMenu(user) ;
		break;
		
		case 3: 
//			System.out.println("\nGrades Added");
			
			System.out.print("\nEnter Course Code: ");
			scan = new Scanner(System.in);
			courseCode = scan.nextLine();
			
			System.out.print("\nEnter Student Id: ");
			Scanner scan1 = new Scanner(System.in);
			int studentId = scan1.nextInt();
			
			System.out.print("\nEnter Grade to Assign: ");
			Scanner scan3 = new Scanner(System.in);
			String grade= scan3.nextLine();
			professorObject.addGrades(user,courseCode,studentId,grade);
		displayProfessorMenu(user) ;
		break;
		
		
		case 4:  System.out.println("\nRedirecting to Main Menu\n");
		CRSApplicationMenu exitobj= new CRSApplicationMenu();
		exitobj.main(null);
		break;
		
		default: System.out.println("Wrong Choice Selected, press ENTER to redirect to Professor Menu.");
		try{System.in.read();}
		catch(Exception e) {}
		displayProfessorMenu(user);
		}
		
//		scan.close();
	}


}
