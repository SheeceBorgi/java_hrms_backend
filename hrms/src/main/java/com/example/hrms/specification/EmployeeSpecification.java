package com.example.hrms.specification;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.example.hrms.constant.EmployeeStatus;
import com.example.hrms.model.Employee;

import jakarta.persistence.criteria.JoinType;

public class EmployeeSpecification {
	public static Specification<Employee> hasStatus(EmployeeStatus status) {
		return (root, query, criteriaBuilder) -> {
			if (status == null) {
				return criteriaBuilder.conjunction();
			}

			return criteriaBuilder.equal(root.get("status"), status);
		};
	}

	public static Specification<Employee> hasDepartment(Long departmentId) {
		return (root, query, criteriaBuilder) -> {
			if (departmentId == null)
				return criteriaBuilder.conjunction();
			return criteriaBuilder.equal(root.get("department").get("id"), departmentId);
		};
	}

	public static Specification<Employee> nameContains(String search) {
		return (root, query, criteriaBuilder) -> {
			if (search == null || search.isBlank())
				return criteriaBuilder.conjunction();
			String searchPattern = "%" + search.trim().toLowerCase() + "%";
			return criteriaBuilder.or(criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), searchPattern),
					criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), searchPattern));
		};
	}

	public static Specification<Employee> joiningDateRange(LocalDate startDate, LocalDate endDate) {
		return (root, query, criteriaBuilder) -> {
			final String joiningDateAttribute = "joiningDate";
			if (startDate != null && endDate != null) {
				return criteriaBuilder.between(root.get(joiningDateAttribute), startDate, endDate);
			}
			if (startDate != null) {
				return criteriaBuilder.greaterThanOrEqualTo(root.get(joiningDateAttribute), startDate);
			}
			if (endDate != null) {
				return criteriaBuilder.lessThanOrEqualTo(root.get(joiningDateAttribute), endDate);
			}
			return criteriaBuilder.conjunction();
		};
	}

	public static Specification<Employee> salaryRange(BigDecimal minSalary, BigDecimal maxSalary) {
		return (root, query, criteriaBuilder) -> {
			final String salaryAttribute = "salary";
			if (minSalary != null && maxSalary != null) {
				return criteriaBuilder.between(root.get(salaryAttribute), minSalary, maxSalary);
			}
			if (minSalary != null) {
				return criteriaBuilder.greaterThanOrEqualTo(root.get(salaryAttribute), minSalary);
			}
			if (maxSalary != null) {
				return criteriaBuilder.lessThanOrEqualTo(root.get(salaryAttribute), maxSalary);
			}
			return criteriaBuilder.conjunction();
		};
	}

	public static Specification<Employee> fetchDepartment(Long id) {
		return (root, query, criteriaBuilder) -> {
			if (query.getResultType() != Long.class && query.getResultType() != long.class) {
				root.fetch("department", JoinType.INNER);
			}
			return criteriaBuilder.conjunction();
		};
	}
}
