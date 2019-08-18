package com.demo.exxeptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.demo.controller.*")
public class EmployeeExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String handleException(Exception e) {
		return "Exception :" + e.getMessage();
	}

}
