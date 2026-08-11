package com.example.hrms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hrms.constant.CommonStatus;
import com.example.hrms.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	Optional<Department> findByIdAndStatus(Long id, CommonStatus status);
}
