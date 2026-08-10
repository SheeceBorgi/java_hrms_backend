package com.example.hrms.exception;

public class EmployeeNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4662966637858372L;

	public EmployeeNotFoundException() {
		super("Employee not found");
	}
}
