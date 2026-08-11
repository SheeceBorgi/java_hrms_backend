package com.example.hrms.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.hrms.dto.CommonPageRequest;

public class PageableUtil {
	public static Pageable createPageable(CommonPageRequest commonPageRequest) {
		Sort pageSort = Sort.by(commonPageRequest.getSortDirection().equalsIgnoreCase("desc") ? Sort.Direction.DESC
				: Sort.Direction.ASC, commonPageRequest.getSortBy());
		return PageRequest.of(commonPageRequest.getPage() - 1, commonPageRequest.getLength(), pageSort);
	}
}
