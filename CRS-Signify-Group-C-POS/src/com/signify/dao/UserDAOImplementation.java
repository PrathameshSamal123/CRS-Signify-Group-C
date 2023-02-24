/**
 * 
 */
package com.signify.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.signify.bean.User;
import com.signify.exception.AdminApprovalPendingException;

/**
 * @author Acer
 *
 */
public class UserDAOImplementation implements UserDAOInterface {
	
	   
		private Connection conn;
		
		
		public UserDAOImplementation(Connection conn) {
	        this.conn = conn;
	    }
	
	public User loginDAO(int userId, String password) throws SQLException, AdminApprovalPendingException
	{
		    PreparedStatement stmt = null;
		    PreparedStatement stmt2 = null;
		    ResultSet rs = null;
		    ResultSet rs2 = null;
		    User user = null;
		    boolean success = false;
		    boolean isRegistered = false;

		    try {

		        stmt = conn.prepareStatement("SELECT * FROM user WHERE userId=? AND password=?");
		        stmt.setLong(1, userId);
		        stmt.setString(2, password);

		        // Step 3: Execute the query
		        rs = stmt.executeQuery();

		        if (rs.next()) {

		        	user = new User();
		        	success = true;
		            user.setUserId(rs.getInt(1));
		            user.setPassword(rs.getString(2));
		            user.setName(rs.getString(3));
		            user.setRole(rs.getInt(4));
		        }
		        
		        
		        stmt2 = conn.prepareStatement("SELECT isRegistered FROM student WHERE userId=?");
		        stmt2.setLong(1, userId);
		        rs2 = stmt2.executeQuery();
		        
		        if(rs2.next())
		        {
		        	isRegistered = rs2.getBoolean(1);
		        	
		        	if(isRegistered == false)
		        	{
		        		throw new AdminApprovalPendingException();

		        	}
		        }
		        if(!success) {
		        	return null;
		        }
		        

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } 
		    return user;
		}
	
	public boolean updatePasswordDAO(int userId, String password)
	{
		  
//		  	Connection conn = null;
		    PreparedStatement stmt = null;
		    PreparedStatement stmt2 = null;
		    ResultSet rs = null;
		    ResultSet rs1 = null;
		    User user = null;
		    boolean success = false;

		    try {
		        stmt = conn.prepareStatement("SELECT * FROM user WHERE userId=? AND password=?");
		        stmt.setLong(1, userId);
		        stmt.setString(2, password);

		        // Step 3: Execute the query
		        rs = stmt.executeQuery();

		        // Step 4: Process the result
		        if (rs.next()) {
		        	Scanner scan = new Scanner(System.in);
		        	System.out.print("Enter New Password: ");
		        	String newPassword = scan.nextLine();
		        	
		        	stmt2 = conn.prepareStatement("UPDATE user SET password = ? WHERE userId=? ");
			        stmt2.setString(1, newPassword);
			        stmt2.setInt(2, userId);
			        stmt2.executeUpdate();

		        	success = true;
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            rs.close();
		            stmt.close();
		            conn.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		  
		  return success; 
		  
	
	}
}