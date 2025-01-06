package com.employee.wrapper;

import java.util.List;

import lombok.Data;

@Data
public class ApiResponse<T> {


	private String message;
	
	private boolean result;
	
	private List<T> data;
	
	
	public ApiResponse(String message, boolean result, List<T> data) {
		this.message = message;
		this.result = result;
		this.data = data;
	}
}
