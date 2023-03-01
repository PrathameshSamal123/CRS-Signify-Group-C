/**
 * 
 */
package com.signify.service;

import java.sql.ResultSet;
import java.util.List;

import com.signify.bean.Student;
import com.signify.bean.User;

/**
 * @author Harismitha L
 *
 */
public interface StudentInterface {

	/**
	 * Method to register new student
	 * @param newStudentObject
	 */
	public boolean register(Student newStudentObject) ;
	
	
	/**
	 * Method to View Student Grade Card
	 * @param user
	 * @param semester
	 */
	public List<ResultSet> viewGradeCard(User user, int semester);
	
	/**
	 * Method to add course 
	 * @param user
	 * @param semester
	 * @param courseCode
	 */
    public boolean addCourse(User user, int semester, String courseCode) ;
    
    /**
     * Method to Drop Course
     * @param user
     * @param courseCode
     */
	public boolean dropCourse(User user, String courseCode) ;
	
	
	/**
	 * View registered courses of a student
	 * @param user
	 */
	public ResultSet viewRegisteredCourses(User user) ;
	
	/**
	 * View List of Available Student
	 */
	public ResultSet viewAvailableCourses();
	
	/**
	 * View Fees to Pay (Payment Details)
	 * @param user
	 * @param semester
	 */
	public  List<ResultSet> viewFeeToPay(User user, int semester);
	
	/**
	 * Method to Pay Fee By Card
	 * @param user
	 * @param referenceId
	 * @param cardNumber
	 * @param cardType
	 * @param cardHolderName
	 * @param cvv
	 * @param expiryDate
	 * @param semester
	 */
	public void payFeeByCard(User user, int referenceId,int cardNumber,int cardType,String cardHolderName,int cvv,String expiryDate, int semester);
	
	/**
	 * Methdod to Pay Fee by netbanking
	 * @param user
	 * @param referenceId
	 * @param semester
	 * @param modeOfTransfer
	 * @param accountNumber
	 * @param ifscCode
	 * @param bankName
	 */
	public void  payFeeByNetbanking(User user, int referenceId, int semester, int modeOfTransfer, int accountNumber, String ifscCode, String bankName);
	


}
