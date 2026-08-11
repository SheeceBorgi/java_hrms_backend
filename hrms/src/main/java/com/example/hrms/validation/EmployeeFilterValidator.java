package com.example.hrms.validation;

import com.example.hrms.dto.EmployeeFilterRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeFilterValidator implements ConstraintValidator<ValidEmployeeFilter, EmployeeFilterRequest> {
    @Override
    public boolean isValid(EmployeeFilterRequest filter, ConstraintValidatorContext context) {

        if (filter == null) {
            return true;
        }

        if (filter.getMinSalary() != null && filter.getMaxSalary() != null
                && filter.getMinSalary().compareTo(filter.getMaxSalary()) > 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Minimum salary cannot be greater than maximum salary")
                    .addPropertyNode("minSalary")
                    .addConstraintViolation();
            return false;
        }

        if (filter.getJoiningStartDate() != null && filter.getJoiningEndDate() != null) {
            if (filter.getJoiningStartDate().isAfter(filter.getJoiningEndDate())) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Joining start date cannot be after joining end date")
                        .addPropertyNode("joiningDate")
                        .addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
