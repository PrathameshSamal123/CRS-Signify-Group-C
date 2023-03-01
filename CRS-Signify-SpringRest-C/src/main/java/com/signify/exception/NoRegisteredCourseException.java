package com.signify.exception;


/*
 * Exception for no registered courses 
 */

public class NoRegisteredCourseException extends Exception {

	public NoRegisteredCourseException() {
		// TODO Auto-generated constructor stub
		System.out.println("You have not registered for any courses yet !");
	}

}
