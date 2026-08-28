package com.example.hrms.mapper;

import org.springframework.stereotype.Component;

import com.example.hrms.dto.EmployeeProfileResponse;
import com.example.hrms.model.EmployeeProfile;

@Component
public class EmployeeProfileMapper {
	public EmployeeProfileResponse toResponse(EmployeeProfile employeeProfile) {
		return new EmployeeProfileResponse(employeeProfile.getId(), employeeProfile.getEmployee().getId(),
				employeeProfile.getDateOfBirth(), employeeProfile.getGender(), employeeProfile.getAddress(),
				employeeProfile.getEmergencyContact());
	}
}
