/**
 * 
 */
package com.signify.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.signify.bean.User;
import com.signify.dao.UserDAOImplementation;
import com.signify.dao.UserDAOInterface;
import com.signify.exception.AdminApprovalPendingException;
import com.signify.utils.DBUtils;

/**
 * @author Dell
 *
 */



public class UserServiceOperations implements UserInterface{
	
	
	/**
	 * Method to login into CRS
	 */
	Connection conn=null;
	public User login() {
		
		try {
		boolean isValidInput = false;
		int userId = -1;
		Scanner scan = new Scanner(System.in);
		while (!isValidInput) {
            System.out.print("Enter Id: ");
            if (scan.hasNextInt()) {
                userId = scan.nextInt();
                isValidInput = true;
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scan.next(); // discard the invalid input
            }
        }

		System.out.print("Enter Password:  ");
		Scanner scan2 = new Scanner(System.in);
		String userPassword = scan2.nextLine();
		conn=DBUtils.getConnection();
		UserDAOInterface userDao = new UserDAOImplementation(conn);
		
		User user = userDao.loginDAO(userId, userPassword);
		
		
		return user;
		}
		
		catch(SQLException e )
		{
			
		}
		catch(AdminApprovalPendingException ex)
		{
			
		}
		
		return null;
	
	}
	
	
	
	/**
	 * Method to Update User Password
	 */
	public void updatePassword() {
		
		boolean isValidInput = false;
		Scanner scan = new Scanner(System.in);
		int userId = 0;
		while (!isValidInput) {
			System.out.print("Enter Id:  ");
            if (scan.hasNextInt()) {
            	userId = scan.nextInt();
                isValidInput = true;
            } else {
                System.out.println("\nInvalid input. Please enter an integer.\n");
                scan.next(); // discard the invalid input
            }
        }

		System.out.print("Enter Password:  ");
		Scanner scan2 = new Scanner(System.in);
		String userPassword = scan2.nextLine();
		conn=DBUtils.getConnection();
		UserDAOInterface userDao = new UserDAOImplementation(conn);
		boolean flag = userDao.updatePasswordDAO(userId, userPassword);
		
		
		if(flag)
		{
			System.out.println("\nPassword Updated... Redirecting to Main Menu\n");
		}
		
		else
		{
			System.out.println("\nInvalid Credentials....\n");
		}
		
	}
}
