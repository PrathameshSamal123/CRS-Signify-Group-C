/**
 * 
 */
package com.signify.client;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import java.lang.Thread;
import com.signify.client.CRSUserMenu;

import com.signify.exception.UserNotFoundException;
import com.signify.bean.Admin;
import com.signify.bean.Student;
import com.signify.client.CRSApplicationMenu;
import java.io.IOException;

import com.signify.service.AdminServiceOperations;
import com.signify.service.StudentServiceOperations;
import com.signify.service.UserServiceOperations;

/**
 * @author Dell
 *
 */
public class CRSApplicationMenu {
	
	public static StudentServiceOperations StudentServiceObj = new StudentServiceOperations();
	public static AdminServiceOperations AdminServiceObj = new AdminServiceOperations();
	public static UserServiceOperations UserServiceObj = new UserServiceOperations();
	
	/**
	 * Method to generate a random number 
	 * @return
	 */
   public static int gen() {
	    Random r = new Random( System.currentTimeMillis() );
	    return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
	}
   
   /**
    * Main Method
    * @param args
    */
	public static void main(String[] args) {

		System.out.println("WELCOME TO THE CRS APPLICATION");
		System.out.println("========================================\n");
		System.out.println("MAIN MENU");
		System.out.println("1. Login");
		System.out.println("2. Registration for Student");
		System.out.println("3. Update Password");
		System.out.println("4. Exit\n");
		

		int userChoice=0;
		
		boolean flag;
		
		do
		{
			try {
				System.out.print("Enter your Choice: ");
				Scanner scan = new Scanner(System.in);
				userChoice = scan.nextInt();
				flag=false;
			}
			catch(Exception e)
			{
				System.out.println("======================================================");
				System.out.println("Please Select from above options only, try again");
				System.out.println("======================================================");
				flag = true;
			}
		}
		
		while(flag);

		switch(userChoice) {
		
		case 1: System.out.println("\nLogin Here");
		System.out.println("============================");
		CRSUserMenu obj = new CRSUserMenu();
		boolean loginSuccess = false;
		try {
			loginSuccess = obj.displayUserMenu();			
		}
		catch (UserNotFoundException e) {
			
		}
		
		if(!loginSuccess)
		{
			main(null);
		}
		break;
		
		case 2: 
			System.out.println("\n------------------------------");
			System.out.println("Student Registration");
			System.out.println("------------------------------\n");
			Student newStudentObject = new Student();


			int userId = gen();
			
			System.out.print("Enter your Name:  ");
			Scanner scan1 = new Scanner(System.in);
			String  studentName = scan1.nextLine();
			
			System.out.print("Enter your Password: ");
			Scanner scan2 = new Scanner(System.in);
			String studentPassword = scan2.nextLine();
			
//			System.out.print("Enter Student ID: ");
			Scanner scan3 = new Scanner(System.in);
			int studentId = -1;
			boolean validInput = false;
			 while (!validInput)
			{
				try {
					System.out.print("Enter Student ID: ");
					studentId = scan3.nextInt();
					validInput=true;
				}
				catch(InputMismatchException  e)
				{
					System.out.println("\nInvalid input. Please enter a valid Student ID\n");
					scan3.nextLine();
				}
			}
			
			
			System.out.print("Enter Branch: ");
			Scanner scan4 = new Scanner(System.in);
			String studentBranch = scan4.nextLine();
			
			
			
//			System.out.print("Enter Batch: ");
			Scanner scan5 = new Scanner(System.in);
//			int studentBatch = scan5.nextInt();
			
			
			int studentBatch = -1;
			validInput = false;
			 while (!validInput)
			{
				try {
					System.out.print("Enter Batch: ");
					studentBatch = scan5.nextInt();
					validInput=true;
				}
				catch(InputMismatchException  e)
				{
					System.out.println("\nInvalid input. Please enter a valid batch (Integer)\n");
					scan5.nextLine();
				}
			}
			
			newStudentObject.setUserId(userId);
			newStudentObject.setName(studentName);
			newStudentObject.setPassword(studentPassword);
			newStudentObject.setRole(2);
			newStudentObject.setStudentId(studentId);
			newStudentObject.setRegistered(false);
			newStudentObject.setBranch(studentBranch);
			newStudentObject.setBatch(studentBatch);
			newStudentObject.setRegistered(false);
			
		StudentServiceObj.register(newStudentObject);
		main(null);
		break;
		
		case 3: 
		UserServiceObj.updatePassword();
		main(null);
		
		break;
		
		case 4: CRSApplicationMenu exitobj= new CRSApplicationMenu();
		exitobj.main(null);
		break;
		
		default: 
			System.out.println("Wrong Choice Selected, press ENTER to retry.");
		try{System.in.read();}
		catch(Exception e) {}
		main(null);
		}
	
//		scan.close();
	}

}
