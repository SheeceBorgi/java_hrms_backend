package com.example.hrms.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.hrms.constant.EmployeeStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "employees", uniqueConstraints = {
		@UniqueConstraint(name = "uk_employee_email", columnNames = { "email" }) })
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false, unique = true)
	private String email;

	private String phone;
	private String department;
	private String designation;
	private LocalDate joiningDate;
	private BigDecimal salary;

	@Enumerated(EnumType.STRING)
	private EmployeeStatus status;

	protected Employee() {
	}

	public Employee(String firstName, String lastName, String email, String phone, String department,
			String designation, LocalDate joiningDate, BigDecimal salary) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.department = department;
		this.designation = designation;
		this.joiningDate = joiningDate;
		this.salary = salary;
		this.status = EmployeeStatus.ACTIVE;
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

	public String getDepartment() {
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

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setStatus(EmployeeStatus status) {
		this.status = status;
	}

}
