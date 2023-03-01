/**
 * 
 */
package com.signify.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

import org.springframework.stereotype.Service;

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
@Service
public class StudentServiceOperations implements StudentInterface {

       Connection conn=null;
	   public int gen() {
		    Random r = new Random( System.currentTimeMillis() );
		    return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
		}
	
	
	
	/**Service to Register
	 *
	 */
	public boolean register(Student newStudentObject) {

			try
		{
             conn=DBUtils.getConnection();
			StudentDAOInterface userDao = new StudentDAOImplementation();
			boolean registered = userDao.registerDAO(newStudentObject);
			return registered;
			
//			if (registered) {
//				System.out.println("\nSuccess !! You are registered.....\n");
//				System.out.print("------------------------------------------");
//				System.out.println("\nYour User ID is: "+newStudentObject.getUserId());
//				System.out.println("\nYour Password: "+newStudentObject.getPassword());
//				System.out.println("------------------------------------------\n");
//				
//            }
			
//			else 
//            {
//                System.out.println("\nFailed to register user\n");
//            }
		}
		catch (StudentRegistrationFailedException e) {
         
        }
		
		catch (SQLException ex) {
            ex.printStackTrace();
        }
			
			return false;
		
	}
	
	
	/**
	 * Method to View Grade Card
	 */
    public List<ResultSet> viewGradeCard(User user, int semester) {

		try 
		{
			conn=DBUtils.getConnection();
			StudentDAOInterface userDao = new StudentDAOImplementation();
			List<ResultSet> gradeCard = userDao.viewGradeCardDAO(user, semester);
			return gradeCard;
			
            }
		
		
		catch (SQLException ex) {
            ex.printStackTrace();
        }
		
		catch(GradeCardNotAvailableException e)
		{
			
		}
		
		return null;
    }



	/**
	 * Method to Pay Fee By Card
	 */
	public void payFeeByCard(User user, int referenceId,int cardNumber,int cardType,String cardHolderName,int cvv,String expiryDate, int semester) {
		
		
		try 
		{
//				Class.forName("com.mysql.jdbc.Driver");
			conn=DBUtils.getConnection();
			StudentDAOInterface userDao = new StudentDAOImplementation();
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
				StudentDAOInterface userDao = new StudentDAOImplementation();
				userDao.payFeeByNetbankingDAO(user, referenceId, semester, modeOfTransfer, accountNumber, ifscCode, bankName);
				
	            }
			
			
			catch (SQLException ex) {
	            ex.printStackTrace();
	        }
		}
	

	/**
     * Method to Drop Course
     */	
	public boolean addCourse(User user, int semester, String courseCode) {
		

		try 
		{
			conn=DBUtils.getConnection();
			StudentDAOInterface userDao = new StudentDAOImplementation();
			boolean added = userDao.addCourseDAO(user,semester,courseCode);
			return added;
			
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
			
		return false;
		}
	
	
	/**
	 * View registered courses of a student
	 */
	public boolean dropCourse(User user, String courseCode) {

		try 
		{
			conn=DBUtils.getConnection();
			StudentDAOInterface userDao = new StudentDAOImplementation();
			boolean dropped = userDao.dropCourseDAO(user, courseCode);
			return dropped;
			
			
		}
		catch(FailToDropCourseException e){
			}
		catch (SQLException ex) {
            ex.printStackTrace();
        }
			
		return false;
			
	
		}

	
	
	/**
	 * View registered courses of a student
	 */
	public ResultSet viewRegisteredCourses(User user) {
		
		System.out.println("\nYour Registered Courses:");
		System.out.println("==============================================");
		
		try 
		{
			conn=DBUtils.getConnection();
			StudentDAOInterface userDao = new StudentDAOImplementation();
			ResultSet registeredCourses = userDao.showRegisteredCoursesDAO(user);
			return registeredCourses;
			
		}
		
		catch (SQLException ex) {
	        ex.printStackTrace();
	    }
		catch(NoRegisteredCourseException e) {
			
		}
		
		
		
		return null;
	}
	
	
	/**
	 * View List of Available Student
	 */
	public ResultSet viewAvailableCourses()
	{
		System.out.println("\nAvailable Courses:");
		System.out.println("==============================================");
		
		try 
		{
			conn=DBUtils.getConnection();
			StudentDAOInterface userDao = new StudentDAOImplementation();
			ResultSet availableCourses = userDao.viewAvailableCoursesDAO();
			return availableCourses; 
			
		}
		
		catch (SQLException ex) {
	        ex.printStackTrace();
	    }
		
		return null;
	}
	
	
	/**
	 * View Fees to Pay (Payment Details)
	 */
	public  List<ResultSet> viewFeeToPay(User user, int semester)
	{
		System.out.println("\n==============================================");
		
		try 
		{
			conn=DBUtils.getConnection();
			StudentDAOInterface userDao = new StudentDAOImplementation();
			List<ResultSet> feeInformation = userDao.viewFeeToPayDAO(user, semester);
			return feeInformation;
		}
		
		catch (SQLException ex) {
	        ex.printStackTrace();
	    }
		
		
		return null;
		
	}
	

	
	
	
	
	
}
