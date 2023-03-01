/**
 * 
 */
package com.signify.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.stereotype.Service;

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

@Service
public class ProfessorServiceOperations implements ProfessorInterface{

	   
	   Connection conn=null;
 
	 
	
	
	/**
	 *
	 *  View all courses a professor is assigned
	 */
	public ResultSet viewMyCourses(User user) {
		
		try 
		{
			conn=DBUtils.getConnection();
			ProfessorDAOInterface userDao = new ProfessorDAOImplementation();
			ResultSet myCourses = userDao.viewMyCoursesDAO(user);
			return myCourses ;
			
		}
		
		catch (SQLException ex) {
            ex.printStackTrace();
        }
		
		return null; 
	}
	
	/**
	 * 
	 * View Enrolled students in a course
	 * 
	 */
	
	public ResultSet viewEnrolledStudents(User user, String courseCode) throws CourseDoesNotExistException,ProfessorDoesNotTeachCourseException {

		
		try 
		{
			conn=DBUtils.getConnection();
			ProfessorDAOInterface userDao = new ProfessorDAOImplementation();
			ResultSet myStudents = userDao.viewEnrolledStudentsDAO(courseCode, user);
			return myStudents; 
		}
		
		catch (SQLException ex) {
            ex.printStackTrace();
        }
//		catch (CourseDoesNotExistException e) {
////			throw new CourseDoesNotExistException(); 
//        }
		
//		catch (ProfessorDoesNotTeachCourseException e) {
//        }
//		
		return null;
	}
	
	
	/**
	 *  Method to Add Grades of a Student for a Course
	 *  
	 */
	public boolean addGrades(User user, String courseCode, int studentId, String grade) throws ValidCourseIdOrCourseCodeException,ProfessorDoesNotTeachCourseException {
			
		try
		{
			conn=DBUtils.getConnection();
			ProfessorDAOInterface userDao = new ProfessorDAOImplementation();
			boolean isAdded = userDao.addGrades(user, courseCode, studentId, grade);
			return isAdded ; 
//			if(isAdded)
//			{
//				System.out.println("\nGrade Added Successfully");
//			}
		}
//		
//		catch (ValidCourseIdOrCourseCodeException e) {
//        }
//		catch (ProfessorDoesNotTeachCourseException e2) {
//        }
//		
		catch (SQLException ex) {
            ex.printStackTrace();
        }
		
		return false; 
		
	}
	

}
