package com.example.hrms.exception;

public class ProfileAlreadyExistException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -209526745485941405L;

	public ProfileAlreadyExistException() {
		super("Employee Profile Already Exists");
	}
}
