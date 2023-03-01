/**
 * 
 */
package com.signify.service;

import java.sql.ResultSet;

import com.signify.bean.User;
import com.signify.exception.CourseDoesNotExistException;
import com.signify.exception.ProfessorDoesNotTeachCourseException;
import com.signify.exception.ValidCourseIdOrCourseCodeException;

/**
 * @author Harismitha L
 *
 */
public interface ProfessorInterface {
	/**
	 * View Enrolled students
	 * @param user
	 * @param courseCode
	 */
	public ResultSet viewEnrolledStudents(User user, String courseCode) throws CourseDoesNotExistException, ProfessorDoesNotTeachCourseException;
	
	/**
	 * Add Grades 
	 * @param user
	 * @param courseCode
	 * @param studentId
	 * @param grade
	 */
	public boolean  addGrades(User user, String courseCode, int studentId, String grade) throws ValidCourseIdOrCourseCodeException,ProfessorDoesNotTeachCourseException  ;
	
	/**
	 * View Professors courses 
	 * @param user
	 */
	public ResultSet viewMyCourses(User user);
}
