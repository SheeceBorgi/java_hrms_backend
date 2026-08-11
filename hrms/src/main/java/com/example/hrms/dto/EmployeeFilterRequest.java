package com.example.hrms.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.hrms.constant.EmployeeStatus;
import com.example.hrms.validation.ValidEmployeeFilter;

import jakarta.validation.constraints.PositiveOrZero;

@ValidEmployeeFilter
public class EmployeeFilterRequest {
    private String department;
    private String search;
    private EmployeeStatus status;
    private LocalDate joiningStartDate;
    private LocalDate joiningEndDate;
    @PositiveOrZero
    private BigDecimal minSalary;
    @PositiveOrZero
    private BigDecimal maxSalary;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    public LocalDate getJoiningStartDate() {
        return joiningStartDate;
    }

    public void setJoiningStartDate(LocalDate joiningStartDate) {
        this.joiningStartDate = joiningStartDate;
    }

    public LocalDate getJoiningEndDate() {
        return joiningEndDate;
    }

    public void setJoiningEndDate(LocalDate joiningEndDate) {
        this.joiningEndDate = joiningEndDate;
    }

    public BigDecimal getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(BigDecimal minSalary) {
        this.minSalary = minSalary;
    }

    public BigDecimal getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(BigDecimal maxSalary) {
        this.maxSalary = maxSalary;
    }

}
