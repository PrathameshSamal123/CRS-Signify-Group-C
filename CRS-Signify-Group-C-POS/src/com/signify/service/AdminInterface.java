/**
 * 
 */
package com.signify.service;

import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;


/**
 * @author Harismitha L
 *
 */
public interface AdminInterface {
	
	/**
	 * Add professor 
	 * @param newProfessorObject
	 * @return boolean value
	 */
	public boolean addProfessor(Professor newProfessorObject);
	
	/**
	 * Approve Student
	 * @param AdminChoice
	 */
	
	public void approveStudent(int AdminChoice);
	
	/**
	 * Add course through course object
	 * @param newCourseObject
	 * @return boolean value
	 */
	public boolean addCourse(Course newCourseObject);
	
	/**
	 * Delete Course based on CourseCode
	 * @param courseToDelete
	 * @return boolean value
	 */
	public boolean deleteCourse(String courseToDelete);
	
	/**
	 * View Course Details
	 */
	public void  viewCourseDetails();
	
	
	/**
	 * View All Professors
	 */
	public void viewProfessor() ;
	
	/**
	 * Assign course to Professor
	 * @param courseID
	 * @param profID
	 * @return boolean if course was assigned to prof or not
	 */
	public boolean assignCourse(String courseID, int profID) ;
	
	/**
	 * Add another ADMIN by passing new ADMIN object
	 * @param admin
	 * @return boolean value
	 */
	public boolean addAdmin(Admin admin);
}
