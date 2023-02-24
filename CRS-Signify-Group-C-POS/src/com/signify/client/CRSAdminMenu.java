/**
 * 
 */
package com.signify.client;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.User;
import com.signify.client.CRSApplicationMenu;
import com.signify.service.AdminInterface;
import com.signify.service.AdminServiceOperations;
import com.signify.service.ProfessorServiceOperations;
import com.signify.service.StudentServiceOperations;

/**
 * @author Acer
 *
 */
public class CRSAdminMenu {
	

	AdminInterface adminObject = new AdminServiceOperations();
	
	/**
	 * Method to display student approval menu
	 * @param user
	 */
	public void displayStudentApprovalMenu(User user)
	{
		System.out.println("\nSTUDENT APPROVAL MENU"+
				"\n--------------------------------------------"
				+ "\nPress 1 to Display List of Unapproved Students\n"
				+ "Press 2 to Approve All students\n"
				+ "Press 3 to Approve students with Student ID\n--------------------------------------------\n");
		
		
		Scanner scan1 = new Scanner(System.in);
		int userChoice = -1;
		boolean validInput = false;
		 while (!validInput) {
	            try {
	            	System.out.print("Enter your Choice: ");
	            	userChoice = scan1.nextInt();
	                if (userChoice == 1 || userChoice == 2 || userChoice ==3) {
	                    validInput = true;
	                } else {
	                    throw new InputMismatchException();
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Invalid choice. Please select from above options\n");
	                scan1.nextLine(); // consume the invalid input
	            }
	        }
		 
		 
		 adminObject.approveStudent(userChoice);
	}
	
	
	/**
	 * Method to display admin menu
	 * @param user
	 */
	public void displayAdminMenu(User user) {
		// TODO Auto-generated method stub
		System.out.print("\n================================\n");
		  System.out.println("Logged in as "+user.getName());
		  System.out.println("================================");	
		
      System.out.println("\nWELCOME TO ADMIN MENU");
      System.out.println("===========================\n");
      System.out.println("ADMIN MENU");
		System.out.println("1. Approve the student registration");
		System.out.println("2. Add Course");
		System.out.println("3. Remove Course");
		System.out.println("4. View Course");
		System.out.println("5. Add Professor");
		System.out.println("6. View Professor");
		System.out.println("7. Add Admin");
		System.out.println("8. Assign Course to Professor");
		System.out.println("9. Exit\n");

		int adminChoice = 0;
		
		boolean flag;
		
		do
		{
			try {
				System.out.print("Enter your Choice: ");
				Scanner scan = new Scanner(System.in);
				adminChoice = scan.nextInt();
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
		
		switch(adminChoice) {
		
		case 1: 
			displayStudentApprovalMenu(user);
			displayAdminMenu(user);
	
		break;
		
		case 2: 
//			System.out.println("Course Added");
			Course newCourseObject = new Course();
			Scanner scan = new Scanner(System.in);
			System.out.print("Enter Course Code:  ");
			String courseCode = scan.nextLine();
			
			System.out.print("Enter Course Name:  ");
			Scanner scan1 = new Scanner(System.in);
			String  courseName = scan1.nextLine();
			
			System.out.println("Is Course Being Offered ? ");
			System.out.println("1. For Yes: Press 1 ");
			System.out.println("2. For No: Press 2");
			Scanner scan2 = new Scanner(System.in);
			int courseOffered = scan2.nextInt();
			
			
			System.out.print("Enter Course Fee:  ");
			Scanner scan3 = new Scanner(System.in);
			int  courseFee = scan3.nextInt();
			
			boolean isOffered = false; 
			
			if(courseOffered == 1)
			{
				isOffered = true;
			}
			else
			{
				isOffered = false;
			}

			
			newCourseObject.setCourseCode(courseCode);
			newCourseObject.setName(courseName);
			newCourseObject.setOffered(isOffered);
			newCourseObject.setCourseFee(courseFee);
			boolean isCourseAdded = adminObject.addCourse(newCourseObject);
			displayAdminMenu(user);

		break;
		
		case 3: 
			System.out.print("Enter Course ID to delete: ");
			Scanner scan4 = new Scanner(System.in);
			String courseToDelete = scan4.nextLine();
			boolean isCourseDeleted = adminObject.deleteCourse(courseToDelete);
			displayAdminMenu(user);

		break;
		
		case 4:
			adminObject.viewCourseDetails();
			System.out.println("======================================================");
			displayAdminMenu(user);
		break;
		
		case 5: 
			Professor newProfessorObject = new Professor();
			
			scan = new Scanner(System.in);
			System.out.print("Enter Name of Professor:  ");
			String professorName = scan.nextLine();
			
			scan1 = new Scanner(System.in);
			System.out.print("Enter user ID:  ");
			int userId = scan1.nextInt();
			
			System.out.print("Enter Password: ");
			 scan2 = new Scanner(System.in);
			String userPassword = scan2.nextLine();
			
			System.out.print("Enter Designation: ");
			scan3 = new Scanner(System.in);
			String professorDesignation = scan3.nextLine();
			
			System.out.print("Enter Department: ");
			scan4 = new Scanner(System.in);
			String professorDepartment = scan4.nextLine();
			
			System.out.print("Enter DOJ: ");
			Scanner scan5 = new Scanner(System.in);
			String professorDoj = scan5.nextLine();

			
			newProfessorObject.setName(professorName);
			newProfessorObject.setUserId(userId);
			newProfessorObject.setPassword(userPassword);
			newProfessorObject.setDesignation(professorDesignation);
			newProfessorObject.setDepartment(professorDepartment);
			newProfessorObject.setDOJ(professorDoj);
			
				
			boolean isProfAdded = adminObject.addProfessor(newProfessorObject);
			displayAdminMenu(user);
		break;
		
		case 6:
			adminObject.viewProfessor();
			displayAdminMenu(user);
		break;
		
		case 7:
			
			Admin newAdminObject = new Admin();
			
			Scanner scan6 = new Scanner(System.in);
			System.out.print("Enter Admin Name:  ");
			String adminName = scan6.nextLine();
			
//			Scanner scan1 = new Scanner(System.in);
			System.out.print("Enter Admin User ID:   ");
			userId = scan6.nextInt();
			
//			Scanner scan3 = new Scanner(System.in);
			System.out.print("Enter Admin Password:  ");
			String adminPassword = scan6.next();


			newAdminObject.setName(adminName);
			newAdminObject.setUserId(userId);
			newAdminObject.setPassword(adminPassword);
			newAdminObject.setRole(1);
//			newAdminObject.setDOJ(sqlDate);
			
			
			adminObject.addAdmin(newAdminObject);
			displayAdminMenu(user);
		break;
		
		case 8:
//			System.out.println("Report Card --> ");
			
			Scanner scan7 = new Scanner(System.in);
			System.out.print("Enter Course ID:   ");
			String courseID = scan7.nextLine();
			
			System.out.print("Enter Professor ID to assign:  ");
			int profID = scan7.nextInt();
			
			adminObject.assignCourse(courseID, profID);
			displayAdminMenu(user);
		break;
		
		case 9: System.out.println("\nRedirecting to Main Menu ");
		CRSApplicationMenu exitobj= new CRSApplicationMenu();
		exitobj.main(null);
		break;
		
		default: System.out.println("Wrong Choice Selected, press ENTER to redirect to Professor Menu.");
		try{System.in.read();}
		catch(Exception e) {}
//		displayAdminMenu(object,index,obj,profObj);
		}
		
		
		
//		scan.close();
	}

	

}
