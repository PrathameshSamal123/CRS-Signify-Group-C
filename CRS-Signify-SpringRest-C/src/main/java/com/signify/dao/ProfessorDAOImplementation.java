/**
 * 
 */
package com.signify.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.stereotype.Repository;

import com.signify.bean.Student;
import com.signify.bean.User;
import com.signify.constants.SQLConstants;


import com.signify.exception.*;
import com.signify.utils.DBUtils;
/**
 * @author Acer
 *
 */

@Repository
public class ProfessorDAOImplementation implements ProfessorDAOInterface {
	
//	JDBC driver name and database URL
	 private Connection conn;
	public ProfessorDAOImplementation() {
		this.conn = DBUtils.getConnection();
    }
	  
	@Override
	
	/**
	 * View Courses assigned to the professor
	 * @param user
	 * @throws SQLException
	 */
	public ResultSet viewMyCoursesDAO(User user) throws SQLException
	{
		
//		String sql = "SELECT t1.courseCode, t2.courseName from courseprof t1 JOIN course t2 on t1.courseCode = t2.courseCode where instructorUserId=?";
		PreparedStatement stmt = conn.prepareStatement(SQLConstants.VIEW__MY_COURSES_PROFESSOR);
		stmt.setInt(1, user.getUserId());
		
		ResultSet rs = stmt.executeQuery();
//		DBTablePrinter.printResultSet(rs);
//		
//		rs--> empty
//		rs.next() --> False
//		
		if(rs.next()==false)
		{
			return null;
		}
		
		ResultSet rs1 = stmt.executeQuery();
		return rs1; 
//		
		
		
	}
	
	
	/**
	 * View Enrolled Students in a CourseCode for a professor
	 * @param courseCode
	 * @param user
	 * @throws SQLException
	 * @throws CourseDoesNotExistException
	 * @throws ProfessorDoesNotTeachCourseException
	 */
	public ResultSet viewEnrolledStudentsDAO(String courseCode, User user) throws SQLException, CourseDoesNotExistException, ProfessorDoesNotTeachCourseException
	{
		
		//t1.courseCode, t3.instructorName,
//		String sql1 = "SELECT courseCode, instructorUserId from courseProf where courseCode=?  ";
		
		// CHECK IF COURSE EXISTS 
		PreparedStatement stmt1 = conn.prepareStatement(SQLConstants.CHECK_COURSE_EXISTS);
		stmt1.setString(1, courseCode);
		
		ResultSet rs1 = stmt1.executeQuery();
		
		boolean isCourseExist = false;
		
		 if (rs1.next()) {
			 isCourseExist = true;
//            System.out.println("Value: " + value);
        	}
		 
		 if(isCourseExist==false)
		 {
			 throw new CourseDoesNotExistException();
//			 System.out.println("Course Not Found...");
//			 return;
		 }
		 
		 
		 
//		 String sql2 = "SELECT courseCode, instructorUserId from courseProf where courseCode=? and instructorUserId=?  ";
			PreparedStatement stmt2 = conn.prepareStatement(SQLConstants.CHECK_DOES_PROF_TEACH_COURSE);
			stmt2.setString(1, courseCode);
			stmt2.setInt(2, user.getUserId());
			
			ResultSet rs2 = stmt2.executeQuery();
			
			boolean doesProfTeach = false;
			
			 if (rs2.next()) {
				 doesProfTeach = true;
//	            System.out.println("Value: " + value);
	        	}
			 
			 if(doesProfTeach==false)
			 {
				 
				 throw new ProfessorDoesNotTeachCourseException();
				 //ystem.out.println("\nEntered course is not assigned to you...");
//				 return
			 }
		 
		 
		 
//		stmt.setInt(2, user.getUserId());
		
		
//		String sql = "SELECT  t1.studentId, t2.name, t2.branch,t2.batch ,COALESCE(grade,\"Not Graded\") AS `GRADE`  from registeredcourse t1 JOIN student t2 on t1.studentId=t2.studentId\r\n"
//				+ "JOIN courseprof t3 on t1.courseCode=t3.courseCode WHERE t1.courseCode = ? and t3.instructorUserId=?";
		PreparedStatement stmt = conn.prepareStatement(SQLConstants.VIEW_ENROLLED_STUDENTS_PROF);
		stmt.setString(1, courseCode);
		stmt.setInt(2, user.getUserId());
		
		ResultSet rs = stmt.executeQuery();
		return rs ;
//		DBTablePrinter.printResultSet(rs);
		
		
	}
	
	
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
	public boolean addGrades(User user, String courseCode, int studentId, String grade) throws SQLException, ValidCourseIdOrCourseCodeException, ProfessorDoesNotTeachCourseException
	{
		
		String sql2 = "SELECT courseCode, studentId from registeredcourse WHERE studentId=? and courseCode=?";
		PreparedStatement stmt2 = conn.prepareStatement(SQLConstants.IS_STUDENT_REGISTERED);
		stmt2.setInt(1, studentId);
		stmt2.setString(2, courseCode);
		
		ResultSet rs2 = stmt2.executeQuery();
		boolean isStudentRegistered = false; 

		if (rs2.next()) {
			isStudentRegistered = true; 
          
       	}
		
		if(isStudentRegistered==false)
		{
//			 System.out.println("\nEnter valid studentId or courseCode");
			throw new ValidCourseIdOrCourseCodeException();
//			 return false;
		}
		
		/////////////////////////////////////
		

		 String sql3 = "SELECT courseCode, instructorUserId from courseProf where courseCode=? and instructorUserId=?  ";
		PreparedStatement stmt3= conn.prepareStatement(sql3);
		stmt3.setString(1, courseCode);
		stmt3.setInt(2, user.getUserId());
		
		ResultSet rs3 = stmt3.executeQuery();
		
		boolean doesProfTeach = false;
		
		 if (rs3.next()) {
			 doesProfTeach = true;
//	            System.out.println("Value: " + value);
        	}
		 
		 if(doesProfTeach==false)
		 {
			 throw new ProfessorDoesNotTeachCourseException();
//			 return false;
		 }
		
		
		String sql = "UPDATE registeredcourse SET grade=? WHERE studentId=? and courseCode=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, grade);
		stmt.setInt(2, studentId);
		stmt.setString(3, courseCode);
		
		int rows1 = stmt.executeUpdate();
		
		return rows1>0;
	}
	
	
	
	

	
	
	

	
	

}


