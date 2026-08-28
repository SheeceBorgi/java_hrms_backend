package com.example.hrms.service;

import org.springframework.stereotype.Service;

import com.example.hrms.constant.EmployeeStatus;
import com.example.hrms.dto.EmployeeProfileCreateRequest;
import com.example.hrms.dto.EmployeeProfileResponse;
import com.example.hrms.exception.EmployeeNotFoundException;
import com.example.hrms.exception.ProfileAlreadyExistException;
import com.example.hrms.mapper.EmployeeProfileMapper;
import com.example.hrms.model.Employee;
import com.example.hrms.model.EmployeeProfile;
import com.example.hrms.repository.EmployeeProfileRepository;
import com.example.hrms.repository.EmployeeRepository;

@Service
public class EmployeeProfileService {
	private final EmployeeProfileRepository employeeProfileRepository;
	private final EmployeeRepository employeeRepository;
	private final EmployeeProfileMapper employeeProfileMapper;

	public EmployeeProfileService(EmployeeProfileRepository employeeProfileRepository,
			EmployeeRepository employeeRepository, EmployeeProfileMapper employeeProfileMapper) {
		this.employeeProfileRepository = employeeProfileRepository;
		this.employeeRepository = employeeRepository;
		this.employeeProfileMapper = employeeProfileMapper;
	}

	public EmployeeProfileResponse createEmployeeProfile(EmployeeProfileCreateRequest employeeProfileRequest) {
		Employee employee = employeeRepository
				.findByIdAndStatus(employeeProfileRequest.getEmployeeId(), EmployeeStatus.ACTIVE)
				.orElseThrow(EmployeeNotFoundException::new);

		if (employeeProfileRepository.findByEmployeeId(employee.getId()).isPresent()) {
			throw new ProfileAlreadyExistException();
		}

		EmployeeProfile newEmployeeProfile = new EmployeeProfile(employee, employeeProfileRequest.getDateOfBirth(),
				employeeProfileRequest.getGender(), employeeProfileRequest.getAddress(),
				employeeProfileRequest.getEmergencyContact());
		EmployeeProfile savedEmployeeProfile = employeeProfileRepository.save(newEmployeeProfile);

		return employeeProfileMapper.toResponse(savedEmployeeProfile);
	}
}
