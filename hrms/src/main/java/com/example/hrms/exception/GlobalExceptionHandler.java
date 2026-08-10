package com.example.hrms.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.example.hrms.dto.ApiErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<>();
		exception.getBindingResult().getFieldErrors().stream()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		ApiErrorResponse errorResponse = new ApiErrorResponse(LocalDateTime.now(), "Validation Failed", false, errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(NoResourceFoundException exception) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(LocalDateTime.now(), exception.getMessage(), false, null);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiErrorResponse> handleDataParseError(HttpMessageNotReadableException exception) {
		Map<String, String> errors = new HashMap<>();
		if (exception.getMessage() != null && exception.getMessage().contains("java.time.LocalDate")) {
			errors.put("message", "Invalid request body: Date format must be YYYY-MM-DD.");
		} else {
			errors.put("message", "Malformed JSON request payload.");
		}
		ApiErrorResponse errorResponse = new ApiErrorResponse(LocalDateTime.now(), errors.get("message"), false, null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(HandlerMethodValidationException.class)
	public ResponseEntity<ApiErrorResponse> handleMethodValidationException(
			HandlerMethodValidationException exception) {
		Map<String, String> errors = new HashMap<>();
		exception.getParameterValidationResults().forEach(result -> {
			result.getResolvableErrors().forEach(error -> {
				String field = result.getMethodParameter().getParameterName();

				errors.put(field, error.getDefaultMessage());
			});
		});
		ApiErrorResponse errorResponse = new ApiErrorResponse(LocalDateTime.now(), "Validation Failed", false, errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolationException(
			DataIntegrityViolationException exception) {
		Map<String, String> errors = new HashMap<>();
		ApiErrorResponse errorResponse = new ApiErrorResponse(LocalDateTime.now(), "Data Integrity Violation", false,
				errors);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}
}
