package com.example.hrms.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.hrms.constant.EmployeeStatus;

public class EmployeeResponse {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private DepartmentResponse department;
	private String designation;
	private LocalDate joiningDate;
	private BigDecimal salary;
	private EmployeeStatus status;
	private EmployeeProfileResponse employeeProfile;

	public EmployeeResponse(Long id, String firstName, String lastName, String email, String phone,
			DepartmentResponse department, String designation, LocalDate joiningDate, BigDecimal salary,
			EmployeeStatus employeeStatus, EmployeeProfileResponse employeeProfile) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.department = department;
		this.designation = designation;
		this.joiningDate = joiningDate;
		this.salary = salary;
		this.status = employeeStatus;
		this.employeeProfile = employeeProfile;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public DepartmentResponse getDepartment() {
		return department;
	}

	public String getDesignation() {
		return designation;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public EmployeeStatus getStatus() {
		return status;
	}

	public EmployeeProfileResponse getEmployeeProfile() {
		return employeeProfile;
	}

}
