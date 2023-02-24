/**
 * 
 */

package com.signify.client;
import java.util.Scanner;

import com.signify.dao.StudentDAOImplementation;
import com.signify.dao.StudentDAOInterface;
import com.signify.dao.UserDAOImplementation;
import com.signify.dao.UserDAOInterface;
import com.signify.exception.UserNotFoundException;
import com.signify.client.CRSStudentMenu;
import com.signify.service.StudentServiceOperations;
import com.signify.service.UserInterface;
import com.signify.service.UserServiceOperations;
import com.signify.bean.User;
import com.signify.client.CRSAdminMenu;
import com.signify.client.CRSProfessorMenu;
/**
 * @author Acer
 *
 */
public class CRSUserMenu {
	
	public static UserServiceOperations UserServiceObj = new UserServiceOperations();
	
	
	/**
	 * Method to display User Menu
	 * @return
	 * @throws UserNotFoundException
	 */
	public boolean displayUserMenu() throws UserNotFoundException {
		boolean loginFlag = false;
		
		CRSStudentMenu ob1=new CRSStudentMenu();
		CRSAdminMenu ob2=new CRSAdminMenu();
		CRSProfessorMenu ob3=new CRSProfessorMenu();
		
		
		
		// USER OBJECT
		User user = UserServiceObj.login();
		
		if(user==null)
		{
			throw new UserNotFoundException();
		}

		if(user.getRole()==1)
			{
				ob2.displayAdminMenu(user);
			}
		
		if(user.getRole()==2)
		{
			ob1.displayStudentMenu(user);
		}
		
		if(user.getRole()==3)
		{
			ob3.displayProfessorMenu(user);
		}
			
		return true;
		
		
	
		
		
		
		

		

//		
		
		
		
		
//		System.out.println(loginFlag);
//		
//		if(userRole.equalsIgnoreCase("Student") && isLoggedIn==true)
//		{
//			ob1.displayStudentMenu();
//			flag = 2;
//		}
//		
//		else if(userRole.equalsIgnoreCase("Professor") && loginFlag==true)
//		{
//			ob3.displayProfessorMenu();
//			flag = 2;
//		}
//		
//		else if(userRole.equalsIgnoreCase("Admin") && loginFlag==true)
//		{
//			int i=0;
////			ob2.displayAdminMenu();
//			flag = 2;
//		}
//		
//		else
//		{
//			if (loginFlag==false)
//			{
//				System.out.println("\n Please Enter Valid Credentials\n\n");
//			}
//	
//			else {
//				System.out.println("\n Please Enter Valid User Role\n\n");
//			}
//			
//			flag=1;
//		}
//		
//		
//		}while(flag==1);
		
//		scan.close();
		
	}

	
	
	
	

}
