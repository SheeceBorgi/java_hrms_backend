package com.example.hrms.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hrms.constant.EmployeeStatus;
import com.example.hrms.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findByEmail(String email);

	Page<Employee> findAllByStatus(EmployeeStatus status, Pageable pageable);

	Optional<Employee> findByIdAndStatus(Long id, EmployeeStatus status);

}
