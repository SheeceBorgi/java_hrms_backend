package com.example.hrms.dto;

import java.time.LocalDateTime;

public class ApiSuccessResponse<T> {
	private LocalDateTime timestamp;
	private String message;
	private boolean success;
	private T data;

	public ApiSuccessResponse(LocalDateTime timestamp, String message, boolean success, T data) {
		this.timestamp = timestamp;
		this.message = message;
		this.success = success;
		this.data = data;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public boolean getSuccess() {
		return success;
	}

	public T getData() {
		return data;
	}
}
