package com.example.hrms.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.hrms.constant.EmployeeStatus;
import com.example.hrms.dto.EmployeeCreateRequest;
import com.example.hrms.dto.EmployeeResponse;
import com.example.hrms.dto.EmployeeUpdateRequest;
import com.example.hrms.dto.PageResponse;
import com.example.hrms.exception.DuplicateEmailException;
import com.example.hrms.exception.EmployeeNotFoundException;
import com.example.hrms.exception.IllegalEmployeeIdException;
import com.example.hrms.mapper.EmployeeMapper;
import com.example.hrms.model.Employee;
import com.example.hrms.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
	private final EmployeeRepository employeeRepository;
	private final EmployeeMapper employeeMapper;

	public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
		this.employeeRepository = employeeRepository;
		this.employeeMapper = employeeMapper;
	}

	public EmployeeResponse createEmployee(EmployeeCreateRequest request) {
		Optional<Employee> existingEmployee = employeeRepository.findByEmail(request.getEmail());
		if (existingEmployee.isPresent()) {
			throw new DuplicateEmailException();
		}

		Employee newEmployee = new Employee(request.getFirstName(), request.getLastName(), request.getEmail(),
				request.getPhone(), request.getDepartment(), request.getDesignation(), request.getJoiningDate(),
				request.getSalary());

		Employee savedEmployee = employeeRepository.save(newEmployee);

		return employeeMapper.toResponse(savedEmployee);
	}

	public PageResponse<EmployeeResponse> findAllEmployees(Pageable pageable) {
		Page<Employee> employees = employeeRepository.findAllByStatus(EmployeeStatus.ACTIVE, pageable);
		Page<EmployeeResponse> employeeResponses = employees.map(employeeMapper::toResponse);

		return new PageResponse<>(employeeResponses.getContent(), employeeResponses.getNumber(),
				employeeResponses.getSize(), employeeResponses.getTotalElements(), employeeResponses.getTotalPages(),
				employeeResponses.isFirst(), employeeResponses.isLast());
	}

	public EmployeeResponse getEmployeeById(Long id) {
		if (id == null || id <= 0) {
			throw new IllegalEmployeeIdException();
		}
		Employee employee = employeeRepository.findByIdAndStatus(id, EmployeeStatus.ACTIVE)
				.orElseThrow(EmployeeNotFoundException::new);

		return employeeMapper.toResponse(employee);
	}

	@Transactional
	public EmployeeResponse updateEmployeeById(Long id, EmployeeUpdateRequest request) {
		if (id == null || id <= 0) {
			throw new IllegalEmployeeIdException();
		}
		Employee employee = employeeRepository.findByIdAndStatus(id, EmployeeStatus.ACTIVE)
				.orElseThrow(EmployeeNotFoundException::new);
		employee.setDepartment(request.getDepartment());
		employee.setDesignation(request.getDesignation());
		employee.setFirstName(request.getFirstName());
		employee.setLastName(request.getLastName());
		employee.setPhone(request.getPhone());
		return employeeMapper.toResponse(employee);
	}

	@Transactional
	public EmployeeResponse deleteEmployeeById(Long id) {
		if (id == null || id <= 0) {
			throw new IllegalEmployeeIdException();
		}
		Employee employee = employeeRepository.findByIdAndStatus(id, EmployeeStatus.ACTIVE)
				.orElseThrow(EmployeeNotFoundException::new);
		employee.setStatus(EmployeeStatus.INACTIVE);
		return employeeMapper.toResponse(employee);
	}

}
