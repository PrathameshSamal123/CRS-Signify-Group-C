/**
 * 
 */
package com.signify.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.signify.bean.User;
import com.signify.dao.ProfessorDAOImplementation;
import com.signify.dao.ProfessorDAOInterface;
import com.signify.dao.StudentDAOImplementation;
import com.signify.dao.StudentDAOInterface;
import com.signify.exception.CourseDoesNotExistException;
import com.signify.exception.ProfessorDoesNotTeachCourseException;
import com.signify.exception.ValidCourseIdOrCourseCodeException;
import com.signify.utils.DBUtils;

/**
 * @author Dell
 *
 */
public class ProfessorServiceOperations implements ProfessorInterface{

	   
	   Connection conn=null;
 
	 
	
	
	/**
	 *
	 *  View all courses a professor is assigned
	 */
	public void viewMyCourses(User user) {
	
		System.out.println("\n Your Course List:");
		
		
		try 
		{
//			Class.forName("com.mysql.jdbc.Driver");
			conn=DBUtils.getConnection();
			ProfessorDAOInterface userDao = new ProfessorDAOImplementation(conn);
			userDao.viewMyCoursesDAO(user);
			
		}
		
		catch (SQLException ex) {
            ex.printStackTrace();
        }
	}
	
	/**
	 * 
	 * View Enrolled students in a course
	 * 
	 */
	
	public void viewEnrolledStudents(User user, String courseCode) {

		
		try 
		{
//			Class.forName("com.mysql.jdbc.Driver");
			conn=DBUtils.getConnection();
			ProfessorDAOInterface userDao = new ProfessorDAOImplementation(conn);
			userDao.viewEnrolledStudentsDAO(courseCode, user);
			
		}
		
		catch (SQLException ex) {
            ex.printStackTrace();
        }
		catch (CourseDoesNotExistException e) {
        }
		catch (ProfessorDoesNotTeachCourseException e) {
        }
	}
	
	
	/**
	 *  Method to Add Grades of a Student for a Course
	 *  
	 */
	public void addGrades(User user, String courseCode, int studentId, String grade) {
			
		try
		{
			conn=DBUtils.getConnection();
			ProfessorDAOInterface userDao = new ProfessorDAOImplementation(conn);
			boolean isAdded = userDao.addGrades(user, courseCode, studentId, grade);
			
			if(isAdded)
			{
				System.out.println("\nGrade Added Successfully");
			}
		}
		
		catch (ValidCourseIdOrCourseCodeException e) {
        }
		catch (ProfessorDoesNotTeachCourseException e2) {
        }
		
		catch (SQLException ex) {
            ex.printStackTrace();
        }
		
	}
	

}
