package com.example.hrms.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.hrms.constant.CommonStatus;
import com.example.hrms.dto.DepartmentCreateRequest;
import com.example.hrms.dto.DepartmentResponse;
import com.example.hrms.dto.DepartmentUpdateRequest;
import com.example.hrms.dto.PageResponse;
import com.example.hrms.exception.DepartmentNotFoundException;
import com.example.hrms.exception.DuplicateDepartmentException;
import com.example.hrms.mapper.DepartmentMapper;
import com.example.hrms.model.Department;
import com.example.hrms.repository.DepartmentRepository;
import com.example.hrms.specification.DepartmentSpecification;

import jakarta.transaction.Transactional;

@Service
public class DepartmentService {
	private final DepartmentRepository departmentRepository;
	private final DepartmentMapper departmentMapper;

	public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
		this.departmentRepository = departmentRepository;
		this.departmentMapper = departmentMapper;
	}

	public DepartmentResponse create(DepartmentCreateRequest request) {
		if (departmentRepository.findByNameIgnoreCase(request.getName()).isPresent()) {
			throw new DuplicateDepartmentException();
		}

		Department department = new Department(request.getName(), request.getDescription());
		Department savedDepartment = departmentRepository.save(department);

		return departmentMapper.toResponse(savedDepartment);
	}

	public DepartmentResponse getById(Long id) {
		Department department = departmentRepository.findByIdAndStatus(id, CommonStatus.ACTIVE)
				.orElseThrow(DepartmentNotFoundException::new);
		return departmentMapper.toResponse(department);
	}

	public PageResponse<DepartmentResponse> getAll(String name, CommonStatus status, Pageable pageable) {
		Specification<Department> departmentSpecification = Specification
				.where(DepartmentSpecification.hasStatus(status)).and(DepartmentSpecification.containsName(name));

		Page<Department> departments = departmentRepository.findAll(departmentSpecification, pageable);

		Page<DepartmentResponse> departmentResponses = departments.map(departmentMapper::toResponse);

		return new PageResponse<>(departmentResponses.getContent(), departmentResponses.getNumber(),
				departmentResponses.getSize(), departmentResponses.getTotalElements(),
				departmentResponses.getTotalPages(), departmentResponses.isFirst(), departmentResponses.isLast());
	}

	@Transactional
	public DepartmentResponse updateById(Long id, DepartmentUpdateRequest request) {
		Department department = departmentRepository.findByIdAndStatus(id, CommonStatus.ACTIVE)
				.orElseThrow(DepartmentNotFoundException::new);

		Optional<Department> existing = departmentRepository.findByNameIgnoreCase(request.getName());

		if (existing.isPresent() && !existing.get().getId().equals(id))
			throw new DuplicateDepartmentException();

			department.setName(request.getName());
			department.setDescription(request.getDescription());

		return departmentMapper.toResponse(department);

	}

	@Transactional
	public DepartmentResponse deactivateById(Long id) {
		Department department = departmentRepository.findByIdAndStatus(id, CommonStatus.ACTIVE)
				.orElseThrow(DepartmentNotFoundException::new);
		department.setStatus(CommonStatus.INACTIVE);

		return departmentMapper.toResponse(department);
	}
}
