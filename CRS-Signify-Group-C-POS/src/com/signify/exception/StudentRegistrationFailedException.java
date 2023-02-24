package com.signify.exception;


/*
 * Exception for student registration failed
 */

public class StudentRegistrationFailedException extends Exception {

	public StudentRegistrationFailedException() {
		// TODO Auto-generated constructor stub
		 System.out.println("Student ID already exists...Failed to register user");
	}

}
