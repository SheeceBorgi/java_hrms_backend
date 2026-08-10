package com.example.hrms.dto;

import java.time.LocalDateTime;

public class ApiErrorResponse {
	private LocalDateTime timestamp;
	private String message;
	private boolean success;
	private Object data;

	public ApiErrorResponse(LocalDateTime timestamp, String message, boolean success, Object data) {
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

	public Object getData() {
		return data;
	}
}
