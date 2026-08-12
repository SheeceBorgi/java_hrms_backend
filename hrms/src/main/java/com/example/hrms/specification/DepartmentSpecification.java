package com.example.hrms.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.hrms.constant.CommonStatus;
import com.example.hrms.model.Department;
import com.example.hrms.model.Employee;

public class DepartmentSpecification {
	public static Specification<Department> containsName(String name) {
		return (root, query, criteriaBuilder) -> {
			if (name == null || name.isBlank())
				return criteriaBuilder.conjunction();
			String pattern = "%" + name.trim().toLowerCase() + "%";
			return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), pattern);
		};
	}

	public static Specification<Department> hasStatus(CommonStatus status) {
		return (root, query, criteriaBuilder) -> {
			if (status == null) {
				return criteriaBuilder.conjunction();
			}

			return criteriaBuilder.equal(root.get("status"), status);
		};
	}
}
