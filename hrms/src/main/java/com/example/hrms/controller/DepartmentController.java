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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrms.constant.CommonStatus;
import com.example.hrms.dto.ApiSuccessResponse;
import com.example.hrms.dto.CommonPageRequest;
import com.example.hrms.dto.DepartmentCreateRequest;
import com.example.hrms.dto.DepartmentResponse;
import com.example.hrms.dto.DepartmentUpdateRequest;
import com.example.hrms.dto.PageResponse;
import com.example.hrms.service.DepartmentService;
import com.example.hrms.util.PageableUtil;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}

	@PostMapping
	public ResponseEntity<ApiSuccessResponse<DepartmentResponse>> createDepartment(
			@Valid @RequestBody DepartmentCreateRequest request) {
		DepartmentResponse departmentResponse = departmentService.create(request);
		ApiSuccessResponse<DepartmentResponse> response = new ApiSuccessResponse<>(LocalDateTime.now(),
				"Department Created", true, departmentResponse);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiSuccessResponse<DepartmentResponse>> getActiveDepartmentById(
			@PathVariable @Positive Long id) {
		DepartmentResponse departmentResponse = departmentService.getById(id);
		ApiSuccessResponse<DepartmentResponse> response = new ApiSuccessResponse<>(LocalDateTime.now(),
				"Department Fetched", true, departmentResponse);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping
	public ResponseEntity<ApiSuccessResponse<PageResponse<DepartmentResponse>>> getAllDepartments(
			@ModelAttribute @Valid CommonPageRequest commonPagerequest, @RequestParam(required = false) String name,
			@RequestParam(required = false) CommonStatus status) {
		Pageable pageable = PageableUtil.createPageable(commonPagerequest);
		PageResponse<DepartmentResponse> departmentResponse = departmentService.getAll(name, CommonStatus.ACTIVE,
				pageable);
		ApiSuccessResponse<PageResponse<DepartmentResponse>> response = new ApiSuccessResponse<>(LocalDateTime.now(),
				"Departments Fetched", true, departmentResponse);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiSuccessResponse<DepartmentResponse>> updateDepartmentById(@PathVariable @Positive Long id,
			@Valid @RequestBody DepartmentUpdateRequest request) {
		DepartmentResponse departmentResponse = departmentService.updateById(id, request);
		ApiSuccessResponse<DepartmentResponse> response = new ApiSuccessResponse<>(LocalDateTime.now(),
				"Department Updated", true, departmentResponse);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiSuccessResponse<DepartmentResponse>> deactivateDepartmentById(
			@PathVariable @Positive Long id) {
		DepartmentResponse departmentResponse = departmentService.deactivateById(id);
		ApiSuccessResponse<DepartmentResponse> response = new ApiSuccessResponse<>(LocalDateTime.now(),
				"Department Created", true, departmentResponse);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
