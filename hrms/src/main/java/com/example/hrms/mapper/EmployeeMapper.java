package com.example.hrms.mapper;

import org.springframework.stereotype.Component;

import com.example.hrms.dto.EmployeeResponse;
import com.example.hrms.model.Employee;

@Component
public class EmployeeMapper {
	public EmployeeResponse toResponse(Employee employee) {
		return new EmployeeResponse(employee.getId(), employee.getFirstName(), employee.getLastName(),
				employee.getEmail(), employee.getPhone(), employee.getDepartment(), employee.getDesignation(),
				employee.getJoiningDate(), employee.getSalary(), employee.getStatus());
	}
}
