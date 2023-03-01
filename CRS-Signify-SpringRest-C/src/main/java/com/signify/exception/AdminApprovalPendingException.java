package com.signify.exception;

/*
 * Exception for admin approval pending
 */
public class AdminApprovalPendingException extends Exception {

	public AdminApprovalPendingException() {
		// TODO Auto-generated constructor stub
		
		System.out.println("\nYou have not been approved by ADMIN....\n");
	}

}
