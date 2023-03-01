/**
 * 
 */
package com.signify.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.dao.AdminDAOImplementation;
import com.signify.dao.AdminDAOInterface;
import com.signify.dao.StudentDAOImplementation;
import com.signify.dao.StudentDAOInterface;
import com.signify.exception.FailedToAddProfessorException;
import com.signify.utils.DBUtils;

/**
 * @author Dell
 *
 */

@Service
public class AdminServiceOperations implements AdminInterface {

	
	Connection conn=null;
	
	/**
	 * Add professor 
	 */
	public boolean addProfessor(Professor newProfessorObject) {

		boolean flag=false;
		try 
		{
//			Class.forName("com.mysql.jdbc.Driver");
			conn=DBUtils.getConnection();
			AdminDAOInterface userDao = new AdminDAOImplementation();
			boolean professorAdded = userDao.addProfessorDAO(newProfessorObject);
			return professorAdded;
			
//			if (professorAdded) {
//				System.out.println("\nSuccess !! Professor Added.....Redirecting to Main Menu\n");
//				flag =  true;
//            } else {
//                System.out.println("Failed to add Professor");
//                flag= false;
//            }
		}
		
		catch (FailedToAddProfessorException e)
		{
			
		}
		catch (SQLException ex) {
            ex.printStackTrace();
        }

		return flag;
		
	 
	}
	
	
	/**
	 * Method to add New Admin
	 */
	public boolean addAdmin(Admin newAdminObject) {
		
		boolean flag=false;
		try 
		{
			conn=DBUtils.getConnection();
			AdminDAOInterface userDao = new AdminDAOImplementation();
			boolean adminAdded = userDao.addAdminDAO(newAdminObject);
			return  adminAdded;
//			if (courseAdded) {
//				System.out.println("\n ADMIN Added.....Redirecting to Main Menu\n");
//				flag =  true;
//            } else {
//                System.out.println("Failed to add Admin");
//                flag= false;
//            }
		}
		
		catch (SQLException ex) {
            ex.printStackTrace();
        }
		
		return flag;
		
		
	}
	
	/**
	 * Method to assign course to professor
	 */
	public boolean assignCourse(String courseID, int profID) {

		
		boolean flag=false;
		try
		{
			conn=DBUtils.getConnection();
			AdminDAOInterface userDao = new AdminDAOImplementation();
			boolean courseAssigned = userDao.assignCourseDAO(courseID, profID);
			return courseAssigned ; 
			
//			if (courseAssigned) {
//				System.out.println("\n Course Assigned to Professor.....Redirecting to Main Menu");
//				flag =  true;
//            } else {
//                System.out.println("\nFailed to assign Course...TRY AGAIN");
//                flag= false;
//            }
		}
		
		catch (SQLException ex) {
            ex.printStackTrace();
        }
		
		return flag;
		
		 
	}
	
	
	/**
	 * Method to View All Professors
	 */
	public ResultSet viewProfessor() {
		
		try 
		{
			conn=DBUtils.getConnection();
			AdminDAOInterface userDao = new AdminDAOImplementation();
			ResultSet professorDetails = userDao.viewProfessorDAO();
			return professorDetails;
		}
		
		catch (SQLException ex) {
            ex.printStackTrace();
        }
			
		return null; 
		 
	}
	
	

	/**
	 * Method to Add a new course
	 */
	public boolean addCourse(Course newCourseObject) {

		boolean flag=false;
		try 
		{
//			Class.forName("com.mysql.jdbc.Driver");
			conn=DBUtils.getConnection();
			AdminDAOInterface userDao = new AdminDAOImplementation();
			boolean courseAdded = userDao.addCourseDAO(newCourseObject);
			return courseAdded;
			

		}
		
		catch (SQLException ex) {
            ex.printStackTrace();
        }
		
		
		return flag;
		}
	
	
	/**
	 * Method to delete course
	 */
	public boolean deleteCourse(String courseToDelete ) {
		
		
		boolean isDeleted = false;
		
		boolean flag=false;
		try 
		{
			conn=DBUtils.getConnection();
			AdminDAOInterface userDao = new AdminDAOImplementation();
			boolean courseDelete = userDao.deleteCourseDAO(courseToDelete);
			return courseDelete;
			
//			if (courseDelete) {
//				System.out.println("\nSuccess !! Course Deleted....Redirecting to Main Menu\n");
//				flag =  true;
//	        } else {
//	            System.out.println("Failed to Delete Course");
//	            flag= false;
//	        }
		}
		
		catch (SQLException ex) {
	        ex.printStackTrace();
	    }
		
		if(isDeleted==false)
		{
			System.out.println("Course Not Found... Try Again");
		}
		
		return flag;
		
	}
	
	/**
	 * Method to View All courses and its details
	 */
	public ResultSet viewCourseDetails() {

		
		try
		{
			
			conn=DBUtils.getConnection();
			AdminDAOInterface userDao = new AdminDAOImplementation();
			ResultSet courseDetails = userDao.viewCourseDAO();
			return courseDetails;
		}
		
		catch (SQLException ex) {
	        ex.printStackTrace();
	    }
		
			return null;
		}
	
	   
	   /**
	    * Method to Approve Student Registration
	    */
	public boolean approveStudent(int userChoice, int userId) {

		try 
		{
			conn=DBUtils.getConnection();
			AdminDAOInterface userDao = new AdminDAOImplementation();
			boolean approveStudent = userDao.approveStudentDAO(userChoice, userId);
			return approveStudent; 
		}
		
		catch (SQLException ex) {
         ex.printStackTrace();
     }
		
		return false; 
		
		
		
		 
	}
	
	 public ResultSet displayUnapprovedStudent()
	 {
		 try 
			{
				conn=DBUtils.getConnection();
				AdminDAOInterface userDao = new AdminDAOImplementation();
				ResultSet unapprovedStudents = userDao.displayUnapprovedStudent();
				return unapprovedStudents; 
			
			}
			
			catch (SQLException ex) {
	         ex.printStackTrace();
	     }
		 
		 return null ;
	 }


}
