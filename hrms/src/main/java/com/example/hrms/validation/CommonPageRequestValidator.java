package com.example.hrms.validation;

import java.util.Set;

import com.example.hrms.dto.CommonPageRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CommonPageRequestValidator
        implements ConstraintValidator<ValidCommonPageRequestFilter, CommonPageRequest> {

    private static final Set<String> WHITELISTED_FIELDS = Set.of(
            "id",
            "firstName",
            "lastName",
            "email",
            "department",
            "designation",
            "joiningDate",
            "salary",
            "status");

    @Override
    public boolean isValid(CommonPageRequest request, ConstraintValidatorContext context) {
        if (request == null) {
            return true;
        }
        if (request.getSortBy() != null && !request.getSortBy().isBlank()) {
            if (!WHITELISTED_FIELDS.contains(request.getSortBy())) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Invalid sort by field")
                        .addPropertyNode("sortBy")
                        .addConstraintViolation();
                return false;
            }
        }

        if (request.getSortDirection() != null && !request.getSortDirection().isBlank()) {
            if (!request.getSortDirection().equalsIgnoreCase("asc")
                    && !request.getSortDirection().equalsIgnoreCase("desc")) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                        "Invalid sort direction. Sort direction can only be asc or desc")
                        .addPropertyNode("sortDirection")
                        .addConstraintViolation();
                return false;
            }
        }
        return true;
    }

}
