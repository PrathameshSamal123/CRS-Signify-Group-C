/**
 * 
 */
package com.signify.client;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import com.signify.bean.User;
import com.signify.client.CRSApplicationMenu;
import com.signify.exception.InvalidInputToIntScannerException;
import com.signify.service.StudentInterface;
import com.signify.service.StudentServiceOperations;

/**
 * @author Acer
 *
 */
public class CRSStudentMenu {
	
	StudentInterface studentObject = new StudentServiceOperations();
	
	/**
	 * Method to generate random number 
	 * @return
	 */
	   public int gen() {
		    Random r = new Random( System.currentTimeMillis() );
		    return ((1 + r.nextInt(2)) * 1000000 + r.nextInt(1000000));
		}
	   
	   /**
	    * Method to check if input is integer
	    * @param input
	    * @return
	    */
	   public static boolean isInteger(String input) {
	        try {
	            Integer.parseInt(input);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
	
	   /**
	    * Method to display semester registration menu
	    * @param user
	    */
	public void displaySemesterRegistrationMenu(User user)
	{
		System.out.println("\nSEMESTER REGISTRATION PORTAL");
		System.out.println("---------------------------------------");
		
		System.out.println("1. Add Course");
		System.out.println("2. Drop Course");
		System.out.println("3. View Available Course Details");
		System.out.println("4. Exit");
		

		int studentChoice=0;
		
		boolean flag;
		
		do
		{
			try {
				System.out.print("Enter your Choice: ");
				Scanner scan = new Scanner(System.in);
				studentChoice = scan.nextInt();
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
		
		Scanner scan = new Scanner(System.in);
		
		
		boolean flag2 = false;
		switch(studentChoice) {
			 
			case 1: 

			int  semester = -1;
			boolean validInput = false;
			 while (!validInput)
			{
				try {
					System.out.print("Enter Semester:  ");
					semester = scan.nextInt();
					validInput=true;
				}
				catch(InputMismatchException  e)
				{
					System.out.println("Invalid input. Please enter an integer.");
					scan.nextLine();
				}
			}
			
			System.out.print("Enter Course Code to ADD:  ");
			Scanner scan1 = new Scanner(System.in);
			String  courseCode = scan1.nextLine();

			studentObject.addCourse(user,semester,courseCode);
			displayStudentMenu(user);
			break;
		
			case 2: 

			System.out.print("Enter Course Code to Drop:  ");
			scan = new Scanner(System.in);
			courseCode = scan.nextLine();
			studentObject.dropCourse(user, courseCode);
			displayStudentMenu(user);
			break;
			
			case 3: 
			
//				System.out.println("\nCourse Registered");
			studentObject.viewAvailableCourses();
			displayStudentMenu(user);
			break;

			case 4: 
				
//				System.out.println("\nCourse Registered");
			displayStudentMenu(user);
			break;
			
			default: System.out.println("Wrong Choice Selected, press ENTER to redirect to SEMESTER REGISTRATION Menu.");
			
			try{System.in.read();}
			catch(Exception e) {}
			displaySemesterRegistrationMenu(user);
		}
	}
	
	
	/**
	 * Method to Display Pay by card menu
	 * @param user
	 */
	public void displayPayByCardMenu(User user)
	{
		System.out.println("\nPAYMENT BY CARD");
		System.out.println("---------------------------------------");
		
		Scanner sc = new Scanner(System.in);
		int semester = -1;
		boolean validInput = false;
		 while (!validInput)
		{
			try {
				System.out.print("Enter Semester:  ");
				semester = sc.nextInt();
				validInput=true;
			}
			catch(InputMismatchException  e)
			{
				System.out.println("Invalid input. Please enter Valid Semester.");
				sc.nextLine();
			}
		}
		
		Scanner scan = new Scanner(System.in);
		int cardNumber = -1;
		validInput = false;
		 while (!validInput)
		{
			try {
				System.out.print("Enter Card Number: ");
				cardNumber = sc.nextInt();
				validInput=true;
			}
			catch(InputMismatchException  e)
			{
				System.out.println("Invalid input. Enter Valid Card Number");
				sc.nextLine();
			}
		}
		
	
		System.out.print("\nEnter Card Type ");
		System.out.print("\n---------------------------------\n");
		System.out.print("Press 1 for DEBIT CARD\n");
		System.out.print("Press 2 for CREDIT CARD\n");
		System.out.print("---------------------------------\n");
		
		Scanner scan1 = new Scanner(System.in);
		int cardType = -1;
		
		validInput = false;
		 while (!validInput) {
	            try {
	            	System.out.print("Enter your Choice: ");
	                cardType = scan1.nextInt();
	                if (cardType == 1 || cardType == 2) {
	                    validInput = true;
	                } else {
	                    throw new InputMismatchException();
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Invalid input. Please select from above options");
	                scan1.nextLine(); // consume the invalid input
	            }
	        }
	
		
		System.out.print("Enter Card Holder Name: ");
		Scanner scan2 = new Scanner(System.in);
		String cardHolderName = scan2.nextLine();
		
		Scanner scan3 = new Scanner(System.in);
		int cvv = -1;
		validInput = false;
		 while (!validInput)
		{
			try {
				System.out.print("Enter CVV ");
				cvv = scan3.nextInt();
				validInput=true;
			}
			catch(InputMismatchException  e)
			{
				System.out.println("Invalid input. Enter Valid CVV");
				scan3.nextLine();
			}
		}
		
		
		System.out.print("Enter Expiry Date: ");
		Scanner scan4 = new Scanner(System.in);
		String expiryDate = scan4.nextLine();
		
		int referenceId = gen();
		
		
		studentObject.payFeeByCard(user ,referenceId, cardNumber, cardType, cardHolderName, cvv, expiryDate, semester);
		
		System.out.println("\nFEE PAID ! ");
		System.out.println("-----------------------------------------");
		
		
		
		
		
		
//		System.out.println("1. View Fee Amount to Pay");
//		System.out.println("2. Pay by Card");
//		System.out.println("3. Exit");
		
		
	}
	
	
	public void displayPayByNetbankingMenu(User user)
	{
		System.out.println("\nPAYMENT BY NET-BANKING");
		System.out.println("---------------------------------------");
		
		System.out.print("Enter Semester: ");
		Scanner sc = new Scanner(System.in);
		int semester = sc.nextInt();
		
		
		System.out.print("\nEnter Mode of Transfer ");
		System.out.print("\n---------------------------------\n");
		System.out.print("Press 1 for NEFT\n");
		System.out.print("Press 2 for RTGS\n");
		System.out.print("Press 3 for IMPS\n");
		System.out.print("---------------------------------\n");
		System.out.print("Enter your Choice: ");
		Scanner scan1 = new Scanner(System.in);
		int modeOfTransfer = scan1.nextInt();
		
		System.out.print("Enter Account Number ");
		Scanner scan2 = new Scanner(System.in);
		int accountNumber = scan2.nextInt();
		
		System.out.print("Enter IFSC Code: ");
		Scanner scan3 = new Scanner(System.in);
		String ifscCode= scan3.nextLine();
		
		System.out.print("Enter Bank Name:  ");
		Scanner scan4 = new Scanner(System.in);
		String bankName = scan4.nextLine();
		
		int referenceId = gen();
		
		
		studentObject.payFeeByNetbanking(user, referenceId, semester, modeOfTransfer, accountNumber, ifscCode, bankName);
		
		System.out.println("\nFEE PAID ! ");
		System.out.println("-----------------------------------------");
		
		
		
		
		
		
//		System.out.println("1. View Fee Amount to Pay");
//		System.out.println("2. Pay by Card");
//		System.out.println("3. Exit");
		
		
	}
	
	public void displayPayFeeMenu(User user)
	{
		System.out.println("\nFEE PAYMENT PORTAL");
		System.out.println("---------------------------------------");
		
		System.out.println("1. View Fee Payment Status");
		System.out.println("2. Pay by Card");
		System.out.println("3. Pay by Netbanking");
		System.out.println("4. Exit");
		
		int studentChoice=0;
		
		boolean flag;
		
		do
		{
			try {
				System.out.print("Enter your Choice: ");
				Scanner scan = new Scanner(System.in);
//				System.out.println("Enter Integer Value only");
				studentChoice = scan.nextInt();
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
		
		switch(studentChoice) {
			 
			case 1: 
			System.out.print("Enter Semester: ");
			Scanner scan = new Scanner(System.in);
//				System.out.println("Enter Integer Value only");
			int semester = scan.nextInt();
//			System.out.println("\nCourse Registered");
			studentObject.viewFeeToPay(user, semester);
			displayPayFeeMenu(user);
			break;
		
			case 2: 
				
//				System.out.println("\nCourse Registered");
//			studentObject.payFeeByCard(user, studentChoice, studentChoice, studentChoice, null, studentChoice, null, semester);
			displayPayByCardMenu(user);
			displayPayFeeMenu(user);
			break;
			
			case 3: 
				
//				System.out.println("\nCourse Registered");
			displayPayByNetbankingMenu(user);
			displayStudentMenu(user);
			break;

		
		}

	}
	
	
	
	
	public void displayStudentMenu(User user) {
		// TODO Auto-generated method stub
	  System.out.print("\n================================\n");
	  System.out.println("Logged in as "+user.getName());
	  System.out.println("================================");
		
      System.out.println("\nWELCOME TO STUDENT MENU");
      System.out.println("===========================\n");
      System.out.println("STUDENT MENU");
		System.out.println("1. SEMESTER REGISTRATION");
		System.out.println("2. View Grade Card");
		System.out.println("3. Pay Fees");
		System.out.println("4. View Registered Courses");
		System.out.println("5. Exit");
		
		
//		StudentInterface studentObject = new StudentServiceOperations();
		
//		System.out.print("Enter your Choice:");
//		Scanner scan = new Scanner(System.in);
//		int studentChoice = scan.nextInt();
		
		
		
		int studentChoice=0;
		
		boolean flag;
		
		do
		{
			try {
				System.out.print("Enter your Choice: ");
				Scanner scan = new Scanner(System.in);
//				System.out.println("Enter Integer Value only");
				studentChoice = scan.nextInt();
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
		
		switch(studentChoice) {
		
		case 1: 
//		System.out.println("\nCourse Registered");
		displaySemesterRegistrationMenu(user);
//		studentObject.registerCourses();
//		displayStudentMenu(object,index);
		break;
	
		case 2: 
//			System.out.println("\nCourse Added");
		System.out.print("\nEnter Semester:  ");
		Scanner scan = new Scanner(System.in);
		int  semester = scan.nextInt();
		studentObject.viewGradeCard(user,semester);
		
		displayStudentMenu(user);
		break;
		
		case 3: 
			displayPayFeeMenu(user);
//			System.out.println("\nCourse Dropped");
//			studentObject.dropCourse();
			displayStudentMenu(user);
		break;
		
		case 4: 
//			System.out.println("\nGrades -->");
			studentObject.viewRegisteredCourses(user);;
			displayStudentMenu(user);
		break;
	
	
		
		case 5: System.out.println("\nRedirecting to Main Menu ");
			CRSApplicationMenu exitobj= new CRSApplicationMenu();
		exitobj.main(null);
		break;
		
		default: System.out.println("Wrong Choice Selected, press ENTER to redirect to Student Menu.");
		
		try{System.in.read();}
		catch(Exception e) {}
		displayStudentMenu(user);
		}
		
//		scan.close();
	}

}
