package com.example.hrms.controller;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrms.dto.ApiSuccessResponse;
import com.example.hrms.dto.CommonPageRequest;
import com.example.hrms.dto.EmployeeCreateRequest;
import com.example.hrms.dto.EmployeeFilterRequest;
import com.example.hrms.dto.EmployeeResponse;
import com.example.hrms.dto.EmployeeUpdateRequest;
import com.example.hrms.dto.PageResponse;
import com.example.hrms.service.EmployeeService;
import com.example.hrms.util.PageableUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping
	public ResponseEntity<ApiSuccessResponse<EmployeeResponse>> createEmployee(
			@Valid @RequestBody EmployeeCreateRequest request) {
		EmployeeResponse employee = employeeService.createEmployee(request);
		ApiSuccessResponse<EmployeeResponse> response = new ApiSuccessResponse<>(LocalDateTime.now(),
				"Employee Created", true, employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<ApiSuccessResponse<PageResponse<EmployeeResponse>>> findAllEmployees(
			@ModelAttribute @Valid CommonPageRequest commonPageRequest,
			@ModelAttribute @Valid EmployeeFilterRequest filter) {

		Pageable pageRequest = PageableUtil.createPageable(commonPageRequest);

		PageResponse<EmployeeResponse> pageResponse = employeeService.findAllEmployees(filter, pageRequest);

		ApiSuccessResponse<PageResponse<EmployeeResponse>> response = new ApiSuccessResponse<PageResponse<EmployeeResponse>>(
				LocalDateTime.now(), "All Employees Fetched", true, pageResponse);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiSuccessResponse<EmployeeResponse>> getEmployeesById(@PathVariable Long id) {
		EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);
		ApiSuccessResponse<EmployeeResponse> response = new ApiSuccessResponse<EmployeeResponse>(LocalDateTime.now(),
				"Employee Fetched Successfully", true, employeeResponse);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiSuccessResponse<EmployeeResponse>> updateEmployeeById(@PathVariable Long id,
			@Valid @RequestBody EmployeeUpdateRequest request) {
		EmployeeResponse employeeResponse = employeeService.updateEmployeeById(id, request);
		ApiSuccessResponse<EmployeeResponse> response = new ApiSuccessResponse<EmployeeResponse>(LocalDateTime.now(),
				"Employee Updated Successfully", true, employeeResponse);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiSuccessResponse<EmployeeResponse>> deleteEmployeeById(@PathVariable Long id) {
		EmployeeResponse employeeResponse = employeeService.deleteEmployeeById(id);
		ApiSuccessResponse<EmployeeResponse> response = new ApiSuccessResponse<EmployeeResponse>(LocalDateTime.now(),
				"Employee Deactivated", true, employeeResponse);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
