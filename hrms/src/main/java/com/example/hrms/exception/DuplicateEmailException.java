package com.example.hrms.exception;

public class DuplicateEmailException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5650403874039038839L;

	public DuplicateEmailException() {
		super("Email already exists");
	}
}
