package com.signify.exception;


/*
 * Exception for course already registered
 */
public class CourseAlreadyRegisteredException extends Exception{

	public CourseAlreadyRegisteredException() {
		// TODO Auto-generated constructor stub
		System.out.println("\nYou have already Registered for the entered Course Code\n");
	}

}
