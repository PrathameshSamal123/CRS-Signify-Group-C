package com.signify.exception;


/*
 * Exception for failing to add professor
 */

public class FailedToAddProfessorException extends Exception {

	public FailedToAddProfessorException() {
		// TODO Auto-generated constructor stub
		System.out.println("\nFailed to add...Professor with same user ID already exists\n");
	}

}
