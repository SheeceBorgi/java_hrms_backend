package com.example.hrms.mapper;

import org.springframework.stereotype.Component;

import com.example.hrms.dto.DepartmentResponse;
import com.example.hrms.dto.EmployeeResponse;
import com.example.hrms.model.Employee;

@Component
public class EmployeeMapper {
	public EmployeeResponse toResponse(Employee employee) {
		DepartmentResponse department = new DepartmentResponse(employee.getDepartment().getId(),
				employee.getDepartment().getName());

		return new EmployeeResponse(employee.getId(), employee.getFirstName(), employee.getLastName(),
				employee.getEmail(), employee.getPhone(), department, employee.getDesignation(),
				employee.getJoiningDate(), employee.getSalary(), employee.getStatus());
	}
}
