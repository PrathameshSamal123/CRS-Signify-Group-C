/**
 * 
 */
package com.signify.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;

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
			AdminDAOInterface userDao = new AdminDAOImplementation(conn);
			boolean professorAdded = userDao.addProfessorDAO(newProfessorObject);
			
			if (professorAdded) {
				System.out.println("\nSuccess !! Professor Added.....Redirecting to Main Menu\n");
				flag =  true;
            } else {
                System.out.println("Failed to add Professor");
                flag= false;
            }
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
			AdminDAOInterface userDao = new AdminDAOImplementation(conn);
			boolean courseAdded = userDao.addAdminDAO(newAdminObject);
			
			if (courseAdded) {
				System.out.println("\n ADMIN Added.....Redirecting to Main Menu\n");
				flag =  true;
            } else {
                System.out.println("Failed to add Admin");
                flag= false;
            }
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
			AdminDAOInterface userDao = new AdminDAOImplementation(conn);
			boolean courseAdded = userDao.assignCourseDAO(courseID, profID);
			
			if (courseAdded) {
				System.out.println("\n Course Assigned to Professor.....Redirecting to Main Menu");
				flag =  true;
            } else {
                System.out.println("\nFailed to assign Course...TRY AGAIN");
                flag= false;
            }
		}
		
		catch (SQLException ex) {
            ex.printStackTrace();
        }
		
		return flag;
		
		 
	}
	
	
	/**
	 * Method to View All Professors
	 */
	public void viewProfessor() {
		
		try 
		{
			conn=DBUtils.getConnection();
			AdminDAOInterface userDao = new AdminDAOImplementation(conn);
			userDao.viewProfessorDAO();
		}
		
		catch (SQLException ex) {
            ex.printStackTrace();
        }

		 
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
			AdminDAOInterface userDao = new AdminDAOImplementation(conn);
			boolean courseAdded = userDao.addCourseDAO(newCourseObject);
			
			if (courseAdded) {
				System.out.println("\nSuccess !! Course  Added.....Redirecting to Main Menu\n");
				flag =  true;
            } else {
                System.out.println("Failed to add Course");
                flag= false;
            }
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
			AdminDAOInterface userDao = new AdminDAOImplementation(conn);
			boolean courseDelete = userDao.deleteCourseDAO(courseToDelete);
			
			if (courseDelete) {
				System.out.println("\nSuccess !! Course Deleted....Redirecting to Main Menu\n");
				flag =  true;
	        } else {
	            System.out.println("Failed to Delete Course");
	            flag= false;
	        }
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
	public void viewCourseDetails() {

		
		try
		{
			
			conn=DBUtils.getConnection();
			AdminDAOInterface userDao = new AdminDAOImplementation(conn);
			userDao.viewCourseDAO();
			
		}
		
		catch (SQLException ex) {
	        ex.printStackTrace();
	    }
		
			
		}
	
	   
	   /**
	    * Method to Approve Student Registration
	    */
	public void approveStudent(int userChoice) {

		try 
		{
			conn=DBUtils.getConnection();
			AdminDAOInterface userDao = new AdminDAOImplementation(conn);
			userDao.approveStudentDAO(userChoice);
		
		}
		
		catch (SQLException ex) {
         ex.printStackTrace();
     }
		
		 
	}


}
