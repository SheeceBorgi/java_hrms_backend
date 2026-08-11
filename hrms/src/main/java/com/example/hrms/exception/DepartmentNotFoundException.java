package com.example.hrms.exception;

public class DepartmentNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4662966637858372L;

	public DepartmentNotFoundException() {
		super("Department not found");
	}
}
