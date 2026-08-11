package com.example.hrms.dto;

import com.example.hrms.validation.ValidCommonPageRequestFilter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@ValidCommonPageRequestFilter
public class CommonPageRequest {
    @Positive
    private int page;
    @Positive
    private int length;
    @NotBlank
    private String sortBy;
    @NotBlank
    private String sortDirection;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

}
