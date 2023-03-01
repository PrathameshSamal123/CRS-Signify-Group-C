/**
 * 
 */
package com.signify.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.signify.bean.User;
import com.signify.exception.CourseDoesNotExistException;
import com.signify.exception.ProfessorDoesNotTeachCourseException;
import com.signify.exception.ValidCourseIdOrCourseCodeException;

/**
 * @author Acer
 *
 */
public interface ProfessorDAOInterface {
	
	/**
	 * View Courses assigned to the professor
	 * @param user
	 * @throws SQLException
	 */
	public ResultSet viewMyCoursesDAO(User user) throws SQLException;
	
	/**
	 * View Enrolled Students in a CourseCode for a professor
	 * @param courseCode
	 * @param user
	 * @throws SQLException
	 * @throws CourseDoesNotExistException
	 * @throws ProfessorDoesNotTeachCourseException
	 */
	public ResultSet viewEnrolledStudentsDAO(String courseCode, User user) throws SQLException, CourseDoesNotExistException, ProfessorDoesNotTeachCourseException ;
	
	/**
	 * Add Grades for a student in a course
	 * @param user
	 * @param courseCode
	 * @param studentId
	 * @param grade
	 * @return
	 * @throws SQLException
	 * @throws ValidCourseIdOrCourseCodeException
	 * @throws ProfessorDoesNotTeachCourseException
	 */
	public boolean addGrades(User user, String courseCode, int studentId, String grade) throws SQLException, ValidCourseIdOrCourseCodeException,ProfessorDoesNotTeachCourseException;

}
