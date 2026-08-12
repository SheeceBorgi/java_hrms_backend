package com.example.hrms.exception;

public class DuplicateDepartmentException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5650403874039038839L;

	public DuplicateDepartmentException() {
		super("Department already exists");
	}
}
