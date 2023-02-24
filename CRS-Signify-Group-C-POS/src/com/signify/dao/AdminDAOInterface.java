/**
 * 
 */
package com.signify.dao;

import java.sql.SQLException;

import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.exception.FailedToAddProfessorException;

/**
 * @author Acer
 *
 */
public interface AdminDAOInterface {
	/**
	 * Method to Add Course 
	 * @param course
	 * @return
	 * @throws SQLException
	 */
	public boolean addCourseDAO(Course course) throws SQLException;
	
	/**
	 * Method to add professor
	 * @param professor
	 * @return
	 * @throws SQLException
	 * @throws FailedToAddProfessorException
	 */
	public boolean addProfessorDAO(Professor professor) throws SQLException,FailedToAddProfessorException;
	
	/**
	 * Method to delete a existing course
	 * @param courseCode
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteCourseDAO(String courseCode ) throws SQLException ;
	
	/**
	 * View Existing Courses
	 * @throws SQLException
	 */
	public void viewCourseDAO() throws SQLException;

	/*
	 * View Professor List
	 * @throws SQLException
	 */
	public void viewProfessorDAO() throws SQLException;
	
	
	/**
	 * Add Another ADMIN
	 * @param admin
	 * @return
	 * @throws SQLException
	 */
	public boolean addAdminDAO(Admin admin) throws SQLException;
	
	/**
	 * Approve Students
	 * @param AdminChoice
	 * @throws SQLException
	 */
	public void approveStudentDAO(int AdminChoice) throws SQLException;
	
	/**
	 * Assign Courses to Professor
	 * @param courseCode
	 * @param professorId
	 * @return
	 * @throws SQLException
	 */
	public boolean assignCourseDAO(String courseCode, int professorId) throws SQLException;


}
