package com.signify.exception;

/*
 * Exception for maximum course register limit
 */

public class MaxCourseRegisterLimitException extends Exception {

	public MaxCourseRegisterLimitException() {
		// TODO Auto-generated constructor stub
		
		System.out.println("\nYou can't register for more than 6 courses.....");
	}

}
