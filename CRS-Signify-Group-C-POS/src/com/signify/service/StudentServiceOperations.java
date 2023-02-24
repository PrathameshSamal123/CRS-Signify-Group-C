/**
 * 
 */
package com.signify.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

import com.signify.bean.Student;
import com.signify.bean.User;
import com.signify.dao.AdminDAOImplementation;
import com.signify.dao.AdminDAOInterface;
import com.signify.dao.StudentDAOImplementation;
import com.signify.dao.StudentDAOInterface;
import com.signify.exception.CourseAlreadyRegisteredException;
import com.signify.exception.CourseDoesNotExistException;
import com.signify.exception.CourseLimitFullException;
import com.signify.exception.FailToDropCourseException;
import com.signify.exception.GradeCardNotAvailableException;
import com.signify.exception.MaxCourseRegisterLimitException;
import com.signify.exception.NoRegisteredCourseException;
import com.signify.exception.StudentRegistrationFailedException;
import com.signify.utils.DBUtils;

/**
 * @author Dell
 *
 */

public class StudentServiceOperations implements StudentInterface {

       Connection conn=null;
	   public int gen() {
		    Random r = new Random( System.currentTimeMillis() );
		    return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
		}
	
	
	
	/**Service to Register
	 *
	 */
	public void register(Student newStudentObject) {

			try
		{
             conn=DBUtils.getConnection();
			StudentDAOInterface userDao = new StudentDAOImplementation(conn);
			boolean registered = userDao.registerDAO(newStudentObject);
			
			if (registered) {
				System.out.println("\nSuccess !! You are registered.....\n");
				System.out.print("------------------------------------------");
				System.out.println("\nYour User ID is: "+newStudentObject.getUserId());
				System.out.println("\nYour Password: "+newStudentObject.getPassword());
				System.out.println("------------------------------------------\n");
				
            }
			
			else 
            {
                System.out.println("\nFailed to register user\n");
            }
		}
		catch (StudentRegistrationFailedException e) {
         
        }
		
		catch (SQLException ex) {
            ex.printStackTrace();
        }
		
	}
	
	
	/**
	 * Method to View Grade Card
	 */
    public void viewGradeCard(User user, int semester) {

		try 
		{
			conn=DBUtils.getConnection();
			StudentDAOInterface userDao = new StudentDAOImplementation(conn);
			userDao.viewGradeCardDAO(user, semester);
			
            }
		
		
		catch (SQLException ex) {
            ex.printStackTrace();
        }
		
		catch(GradeCardNotAvailableException e)
		{
			
		}
    }



	/**
	 * Method to Pay Fee By Card
	 */
	public void payFeeByCard(User user, int referenceId,int cardNumber,int cardType,String cardHolderName,int cvv,String expiryDate, int semester) {
		
		
		try 
		{
//				Class.forName("com.mysql.jdbc.Driver");
			conn=DBUtils.getConnection();
			StudentDAOInterface userDao = new StudentDAOImplementation(conn);
			userDao.payFeeByCardDAO(user, referenceId, cardNumber, cardType, cardHolderName, cvv, expiryDate, semester);
			
            }
		
		
		catch (SQLException ex) {
            ex.printStackTrace();
        }
	}

	
	/**
	 * Methdod to Pay Fee by netbanking
	 */
	
		public void  payFeeByNetbanking(User user, int referenceId, int semester, int modeOfTransfer, int accountNumber, String ifscCode, String bankName)
		{
			try 
			{
			
				conn=DBUtils.getConnection();
				StudentDAOInterface userDao = new StudentDAOImplementation(conn);
				userDao.payFeeByNetbankingDAO(user, referenceId, semester, modeOfTransfer, accountNumber, ifscCode, bankName);
				
	            }
			
			
			catch (SQLException ex) {
	            ex.printStackTrace();
	        }
		}
	

	/**
     * Method to Drop Course
     */	
	public void addCourse(User user, int semester, String courseCode) {
		

		try 
		{
			conn=DBUtils.getConnection();
			StudentDAOInterface userDao = new StudentDAOImplementation(conn);
			boolean added = userDao.addCourseDAO(user,semester,courseCode);
			
			if (added) {
				System.out.println("\nSuccess !! Course Added .....\n");
				
            } else {
                System.out.println("\nFailed to add course\n");
            }
		}
		catch(CourseDoesNotExistException e1)
		{
			
		}
		catch (MaxCourseRegisterLimitException e) {
        }
		catch (CourseLimitFullException ex) {
        }
		catch(CourseAlreadyRegisteredException e2) {
			
		}
		
		
		catch (SQLException ex) {
            ex.printStackTrace();
        }

		}
	
	
	/**
	 * View registered courses of a student
	 */
	public void dropCourse(User user, String courseCode) {

		try 
		{
			conn=DBUtils.getConnection();
			StudentDAOInterface userDao = new StudentDAOImplementation(conn);
			boolean added = userDao.dropCourseDAO(user, courseCode);
			
			if (added) {
				System.out.println("\nSuccess !! Course Dropped .....\n");

				
            } else {

            }
		}
		catch(FailToDropCourseException e){
			}
		catch (SQLException ex) {
            ex.printStackTrace();
        }

			
	
		}

	
	
	/**
	 * View registered courses of a student
	 */
	public void viewRegisteredCourses(User user) {
		
		System.out.println("\nYour Registered Courses:");
		System.out.println("==============================================");
		
		try 
		{
			conn=DBUtils.getConnection();
			StudentDAOInterface userDao = new StudentDAOImplementation(conn);
			userDao.showRegisteredCoursesDAO(user);
			
		}
		
		catch (SQLException ex) {
	        ex.printStackTrace();
	    }
		catch(NoRegisteredCourseException e) {
			
		}
		
		
		
		
	}
	
	
	/**
	 * View List of Available Student
	 */
	public void viewAvailableCourses()
	{
		System.out.println("\nAvailable Courses:");
		System.out.println("==============================================");
		
		try 
		{
			conn=DBUtils.getConnection();
			StudentDAOInterface userDao = new StudentDAOImplementation(conn);
			userDao.viewAvailableCoursesDAO();
			
		}
		
		catch (SQLException ex) {
	        ex.printStackTrace();
	    }
		
	}
	
	
	/**
	 * View Fees to Pay (Payment Details)
	 */
	public void viewFeeToPay(User user, int semester)
	{
		System.out.println("\n==============================================");
		
		try 
		{
			conn=DBUtils.getConnection();
			StudentDAOInterface userDao = new StudentDAOImplementation(conn);
			userDao.viewFeeToPayDAO(user, semester);
			
		}
		
		catch (SQLException ex) {
	        ex.printStackTrace();
	    }
		
		
	}
	

	
	
	
	
	
}
