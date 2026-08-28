package com.example.hrms.dto;

import java.time.LocalDate;

public class EmployeeProfileResponse {
	private Long id;
	private Long employeeId;
	private LocalDate dateOfBirth;
	private String gender;
	private String address;
	private String emergencyContact;

	public EmployeeProfileResponse(Long id, Long employeeId, LocalDate dateOfBirth, String gender, String address,
			String emergencyContact) {
		this.id = id;
		this.employeeId = employeeId;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.address = address;
		this.emergencyContact = emergencyContact;
	}

	public Long getId() {
		return id;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public String getAddress() {
		return address;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

}
