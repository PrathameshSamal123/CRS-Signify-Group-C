/**
 * 
 */
package com.signify.service;

import com.signify.bean.User;

/**
 * @author Harismitha L
 *
 */
public interface UserInterface {
//	public void updateDetails(String name, String role) ;
	
	/**
	 * Method to Update Password
	 */
	public void updatePassword() ;
	public User login();
}
