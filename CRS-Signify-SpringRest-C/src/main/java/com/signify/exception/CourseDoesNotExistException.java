package com.signify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/*
 * Exception for course does not exists
 */

public class CourseDoesNotExistException extends Exception{

	public  CourseDoesNotExistException() {
		// TODO Auto-generated constructor stub
		 System.out.println("\nCourse Not Found...Please Enter Valid Course Code\n");

	}

}
