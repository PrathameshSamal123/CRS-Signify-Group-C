/**
 * 
 */
package com.signify.dao;
import com.signify.dao.DBTablePrinter;
import com.signify.exception.FailedToAddProfessorException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;

/**
 * @author Acer
 *
 */
public class AdminDAOImplementation implements AdminDAOInterface{
	
	 private Connection conn;
		public AdminDAOImplementation(Connection conn) {
	        this.conn = conn;
	    }
	
		

	/**
	 * Method to add professor
	 * @param professor
	 * @return
	 * @throws SQLException
	 * @throws FailedToAddProfessorException
	 */
	public boolean addProfessorDAO(Professor professor) throws SQLException,FailedToAddProfessorException
	{

		
			   
	    	String sql2 = "INSERT IGNORE INTO professor (userId, name, department, designation, doj) VALUES (?, ?, ?,?,?)";
			PreparedStatement stmt2 = conn.prepareStatement(sql2);
	        stmt2.setLong(1, professor.getUserId());
	        stmt2.setString(2, professor.getName());
	        stmt2.setString(3, professor.getDepartment());
	        stmt2.setString(4, professor.getDesignation());
	        stmt2.setString(5, professor.getDOJ());
//	        java.sql.Date sqlDate = new java.sql.Date(professor.getDOJ().getTime());
//	        stmt2.setDate(4,sqlDate);
	        
	        int rows2 = stmt2.executeUpdate();
	        
	        if(rows2==0)
	        {
	        	throw new FailedToAddProfessorException();
	        }
	        
	        
	        
	        String sql = "INSERT IGNORE INTO user (userId, password, name, roleId) VALUES (?, ?, ?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setLong(1, professor.getUserId());
	        stmt.setString(2, professor.getPassword());
	        stmt.setString(3, professor.getName());
	        stmt.setLong(4, 3);
	        
	        int rows1 = stmt.executeUpdate();
	        
	        
	        
	        stmt.close();
	        stmt2.close();
	        conn.close();
	        
	        return rows2 > 0;
	}
	
	
	
	/**
	 * Method to Add Course 
	 * @param course
	 * @return
	 * @throws SQLException
	 */
	public boolean addCourseDAO(Course course) throws SQLException
	{
		String sql = "INSERT IGNORE INTO course (courseCode, courseName, isOffered, courseFee) VALUES (?, ?, ?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, course.getCourseCode());
		stmt.setString(2, course.getName());
		stmt.setBoolean(3, course.getOffered());
		stmt.setInt(4, course.getCourseFee());
		
		 int rows1 = stmt.executeUpdate();
		 stmt.close();
		 
		 return rows1 > 0;
		 
		
	}
	
	
	/**
	 * Method to delete a existing course
	 * @param courseCode
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteCourseDAO(String courseCode) throws SQLException
	{
		String sql = "DELETE FROM course WHERE courseCode =?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, courseCode);

		
		 int rows1 = stmt.executeUpdate();
		 stmt.close();
		 
		 return rows1 > 0;
		 
		
	}
	
	

	/**
	 * Add Another ADMIN
	 * @param admin
	 * @return
	 * @throws SQLException
	 */
	public boolean addAdminDAO(Admin admin) throws SQLException
	{
		String sql = "INSERT IGNORE INTO admin (userId, name, DOJ) VALUES (?, ?, CURRENT_DATE)";
		 PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setLong(1, admin.getUserId());
	        stmt.setString(2, admin.getName());
	        
//	        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//	        stmt.setDate(3,sqlDate);
	        
	        
	        String sql2 = "INSERT IGNORE INTO user (userId, password, name, roleId) VALUES (?, ?, ?,?)";
			 PreparedStatement stmt2 = conn.prepareStatement(sql2);
	        stmt2.setLong(1, admin.getUserId());
	        stmt2.setString(2, admin.getPassword());
	        stmt2.setString(3, admin.getName());
	        stmt2.setLong(4, 1);
	        
	        
	        int rows1 = stmt.executeUpdate();
	        int rows2 = stmt2.executeUpdate();
	        
	        stmt.close();
	        stmt2.close();
	        conn.close();
	        
	        return rows1 > 0;
	}
	
	
	
	/**
	 * Assign Courses to Professor
	 * @param courseCode
	 * @param professorId
	 * @return
	 * @throws SQLException
	 */
	public boolean assignCourseDAO(String courseCode, int professorId) throws SQLException
	{
		 String sql = "SELECT name from professor where userId = ?";
		 PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setLong(1,professorId);

	        ResultSet rs = stmt.executeQuery();
	        
	        String profName = null; 
	        
	        while (rs.next()) {
	            profName = rs.getString("name");

	          }
	        
	        if(profName==null)
	        {
	        	System.out.println("\nProfessor ID doesn't exist. Please Add professor first to assign course.");
	        	return false;
	        }
	        

	        String sql2 = "INSERT IGNORE INTO courseprof (courseCode, instructorName, instructorUserId) VALUES (?, ?, ?)";
			 PreparedStatement stmt2 = conn.prepareStatement(sql2);
	        stmt2.setString(1, courseCode);
	        stmt2.setString(2, profName);
	        stmt2.setInt(3, professorId);

	        int rows2 = stmt2.executeUpdate();
	        
	        stmt.close();
	        stmt2.close();
	        conn.close();
	        
	        return rows2 > 0;
	}
	
	

	/**
	 * View Existing Courses
	 * @throws SQLException
	 */
	public void viewCourseDAO() throws SQLException
	{
		
		 String sql = "SELECT t1.courseCode, t1.courseName, t1.isOffered, t1.courseFee, COALESCE(t2.instructorUserId,'Instructor Not Assigned') AS instructorUserId "
		 		+ ",COALESCE(t2.instructorName,'Instructor Not Assigned') AS instructorName  \r\n"
			 		+ "FROM course t1\r\n"
			 		+ "LEFT JOIN courseprof t2 ON t1.courseCode = t2.courseCode;";
			 PreparedStatement stmt = conn.prepareStatement(sql);

	        ResultSet rs = stmt.executeQuery();

			DBTablePrinter.printResultSet(rs);
		
	}
	
	

	/*
	 * View Professor List
	 * @throws SQLException
	 */
	public void viewProfessorDAO() throws SQLException
	{
		DBTablePrinter.printTable(conn, "professor");

		
	}

	  
	   /**
		 * Approve Students
		 * @param AdminChoice
		 * @throws SQLException
		 */
	public void approveStudentDAO(int AdminChoice) throws SQLException
	{
		Scanner sc = new Scanner(System.in);
		int idByAdmin;
		String sql = "";
		PreparedStatement stmt = null;
			
			if (AdminChoice == 1) {
				sql = "SELECT * from student WHERE isRegistered=0";
				stmt = conn.prepareStatement(sql);
	
				ResultSet rs = stmt.executeQuery(sql);
				
				DBTablePrinter.printResultSet(rs);
				
			}
	
			else if (AdminChoice == 2) {
				sql = "UPDATE student set isRegistered=1 WHERE isRegistered=0";
				stmt = conn.prepareStatement(sql);
				int row = stmt.executeUpdate();
				
				if(row ==0)
				{
					System.out.println("No Record Found.... Try Again");
				}
			}

		else if (AdminChoice == 3) {
			System.out.print("Enter Student ID to approve: ");
			idByAdmin = sc.nextInt();
			sql = "UPDATE student set isRegistered=? WHERE userId=?";
			stmt = conn.prepareStatement(sql);
			stmt.setBoolean(1,true);
			stmt.setInt(2,idByAdmin);
			int row = stmt.executeUpdate();
			
			if(row ==0)
			{
				System.out.println("No Record Found.... Try Again");
			}
			
			else {
			System.out.println("Student Approved Successfully");
			}
		}

		
//		stmt.close();
//		conn.close();
	  
}}

