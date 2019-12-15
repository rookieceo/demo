package com.example.demo.common.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import lombok.Getter;

@Getter
public class CustomPageRequest {

	private int page;

	private int size;

	private Sort.Direction direction;

	private String sortBy;

	public void setPage(int page) {
		this.page = page <= 0 ? 1 : page;
	}

	public void setSize(int size) {
		this.size = size < 1 ? Integer.MAX_VALUE : size;
	}

	public void setDirection(Sort.Direction direction) {
		this.direction = direction;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = StringUtils.isEmpty(sortBy) ? "createdAt" : sortBy;
	}

	// getter
	public PageRequest of() {
		return PageRequest.of(this.page - 1, this.size, this.direction, this.sortBy);
	}
}
