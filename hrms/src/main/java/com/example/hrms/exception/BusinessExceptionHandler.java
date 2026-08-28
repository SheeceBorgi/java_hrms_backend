package com.example.hrms.exception;

import java.time.LocalDateTime;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.hrms.dto.ApiErrorResponse;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BusinessExceptionHandler {

	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<ApiErrorResponse> handleDuplicateEmailException(DuplicateEmailException exception) {
		ApiErrorResponse response = new ApiErrorResponse(LocalDateTime.now(), exception.getMessage(), false, null);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException exception) {
		ApiErrorResponse response = new ApiErrorResponse(LocalDateTime.now(), exception.getMessage(), false, null);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(IllegalEmployeeIdException.class)
	public ResponseEntity<ApiErrorResponse> handleIllegalEmployeeIdException(IllegalEmployeeIdException exception) {
		ApiErrorResponse response = new ApiErrorResponse(LocalDateTime.now(), exception.getMessage(), false, null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(ProfileAlreadyExistException.class)
	public ResponseEntity<ApiErrorResponse> handleProfileAlreadyExistsException(
			ProfileAlreadyExistException exception) {
		ApiErrorResponse response = new ApiErrorResponse(LocalDateTime.now(), exception.getMessage(), false, null);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}
}
