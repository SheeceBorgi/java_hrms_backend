package com.example.hrms.mapper;

import org.springframework.stereotype.Component;

import com.example.hrms.dto.DepartmentResponse;
import com.example.hrms.model.Department;

@Component
public class DepartmentMapper {
	public DepartmentResponse toResponse(Department department) {
		return new DepartmentResponse(department.getId(), department.getName());
	}
}
