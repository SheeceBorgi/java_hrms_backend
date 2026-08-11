package com.example.hrms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.hrms.constant.EmployeeStatus;
import com.example.hrms.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
	Optional<Employee> findByEmail(String email);

	Optional<Employee> findByIdAndStatus(Long id, EmployeeStatus status);
}
