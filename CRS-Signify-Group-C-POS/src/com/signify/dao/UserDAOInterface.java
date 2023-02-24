/**
 * 
 */
package com.signify.dao;

import java.sql.SQLException;

import com.signify.bean.User;
import com.signify.exception.AdminApprovalPendingException;

/**
 * @author Acer
 *
 */
public interface UserDAOInterface {
	
	/**
	 * Login into CRS
	 * @param userId
	 * @param password
	 * @return
	 * @throws SQLException
	 * @throws AdminApprovalPendingException
	 */
	public User loginDAO(int userId, String password) throws SQLException, AdminApprovalPendingException;
	
	/**
	 * Update the User Password
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean updatePasswordDAO(int userId, String password);

}
