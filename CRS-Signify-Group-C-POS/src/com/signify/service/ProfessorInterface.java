/**
 * 
 */
package com.signify.service;

import com.signify.bean.User;

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
	public void viewEnrolledStudents(User user, String courseCode);
	
	/**
	 * Add Grades 
	 * @param user
	 * @param courseCode
	 * @param studentId
	 * @param grade
	 */
	public void addGrades(User user, String courseCode, int studentId, String grade) ;
	
	/**
	 * View Professors courses 
	 * @param user
	 */
	public void viewMyCourses(User user);
}
