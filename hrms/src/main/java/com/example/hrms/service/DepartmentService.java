package com.example.hrms.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.hrms.constant.CommonStatus;
import com.example.hrms.model.Department;
import com.example.hrms.repository.DepartmentRepository;

@Service
public class DepartmentService {
	private final DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	public Optional<Department> findByIdAndStatus(Long id) {
		return departmentRepository.findByIdAndStatus(id, CommonStatus.ACTIVE);
	}
}
