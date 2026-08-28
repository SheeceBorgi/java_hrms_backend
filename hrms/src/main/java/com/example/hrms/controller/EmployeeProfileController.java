package com.example.hrms.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrms.dto.ApiSuccessResponse;
import com.example.hrms.dto.EmployeeProfileCreateRequest;
import com.example.hrms.dto.EmployeeProfileResponse;
import com.example.hrms.service.EmployeeProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employee-profiles")
public class EmployeeProfileController {

	private final EmployeeProfileService employeeProfileService;

	public EmployeeProfileController(EmployeeProfileService employeeProfileService) {
		this.employeeProfileService = employeeProfileService;
	}

	@PostMapping
	public ResponseEntity<ApiSuccessResponse<EmployeeProfileResponse>> createEmployeeProfile(
			@Valid @RequestBody EmployeeProfileCreateRequest employeeProfileCreateRequest) {
		EmployeeProfileResponse employeeProfile = employeeProfileService
				.createEmployeeProfile(employeeProfileCreateRequest);
		ApiSuccessResponse<EmployeeProfileResponse> response = new ApiSuccessResponse<EmployeeProfileResponse>(
				LocalDateTime.now(), "Employee profile created successfully", true, employeeProfile);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
