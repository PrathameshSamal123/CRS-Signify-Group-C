package com.signify.exception;


/*
 * Exception for prof doesnt teach course 
 */

public class ProfessorDoesNotTeachCourseException extends Exception{

	public ProfessorDoesNotTeachCourseException() {
		// TODO Auto-generated constructor stub
		System.out.println("\nEntered course is not assigned to you...");
	}

}
