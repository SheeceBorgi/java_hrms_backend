package com.example.hrms.exception;

public class IllegalEmployeeIdException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4094602453416922369L;

	public IllegalEmployeeIdException() {
		super("Invalid Employee ID");
	}
}
