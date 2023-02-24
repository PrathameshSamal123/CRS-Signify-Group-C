/**
 * 
 */
package com.signify.dao;

import java.sql.SQLException;

import com.signify.bean.Student;
import com.signify.bean.User;
import com.signify.exception.CourseAlreadyRegisteredException;
import com.signify.exception.CourseDoesNotExistException;
import com.signify.exception.CourseLimitFullException;
import com.signify.exception.FailToDropCourseException;
import com.signify.exception.GradeCardNotAvailableException;
import com.signify.exception.MaxCourseRegisterLimitException;
import com.signify.exception.NoRegisteredCourseException;
import com.signify.exception.StudentRegistrationFailedException;

/**
 * @author Acer
 *
 */
public interface StudentDAOInterface {
	
	/**
	 * Method to Register
	 * @param student
	 * @return
	 * @throws SQLException
	 * @throws StudentRegistrationFailedException
	 */
	public boolean registerDAO(Student student) throws SQLException, StudentRegistrationFailedException;
	
	/**
	 * ADD courses for the semester
	 * @param user
	 * @param semester
	 * @param courseCode
	 * @return
	 * @throws SQLException
	 * @throws MaxCourseRegisterLimitException
	 * @throws CourseLimitFullException
	 * @throws CourseDoesNotExistException
	 * @throws CourseAlreadyRegisteredException
	 */
	public boolean addCourseDAO(User user, int semester, String courseCode) throws SQLException,MaxCourseRegisterLimitException, CourseLimitFullException,CourseDoesNotExistException,CourseAlreadyRegisteredException;
	
	/**
	 * Drop Courses 
	 * @param user
	 * @param courseCode
	 * @return
	 * @throws SQLException
	 * @throws FailToDropCourseException
	 */
	public boolean dropCourseDAO(User user, String courseCode) throws SQLException,FailToDropCourseException;
	
	/**
	 * Show Registered Courses of student
	 * @param user
	 * @throws SQLException
	 * @throws NoRegisteredCourseException
	 */
	public void showRegisteredCoursesDAO(User user) throws SQLException,NoRegisteredCourseException ;
	
	/**
	 * View Available courses student can add
	 * @throws SQLException
	 */
	public void viewAvailableCoursesDAO()  throws SQLException;
	
	/**
	 * View Student Grade Card
	 * @param user
	 * @param semester
	 * @throws SQLException
	 * @throws GradeCardNotAvailableException
	 */
	public void viewGradeCardDAO(User user, int semester)  throws SQLException,GradeCardNotAvailableException;
	
	/**
	 * View Fees to Pay 
	 * @param user
	 * @param semester
	 * @throws SQLException
	 */
	public void viewFeeToPayDAO(User user, int semester)  throws SQLException;
	
	/**
	 * Pay Fee By Card
	 * @param user
	 * @param referenceId
	 * @param cardNumber
	 * @param cardType
	 * @param cardHolderName
	 * @param cvv
	 * @param expiryDate
	 * @param semester
	 * @throws SQLException
	 */
	public void payFeeByCardDAO (User user, int referenceId,int cardNumber,int cardType,String cardHolderName,int cvv,String expiryDate, int semester)  throws SQLException;
	
	/**
	 * Pay Fee By Netbanking
	 * @param user
	 * @param referenceId
	 * @param semester
	 * @param modeOfTransfer
	 * @param accountNumber
	 * @param ifscCode
	 * @param bankName
	 * @throws SQLException
	 */
	public void  payFeeByNetbankingDAO(User user, int referenceId, int semester, int modeOfTransfer, int accountNumber, String ifscCode, String bankName)  throws SQLException;
}
