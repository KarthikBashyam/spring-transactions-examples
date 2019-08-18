package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Employee;
import com.demo.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(path = "/")
	public String hello() {
		return "Welcome to Spring";
	}

	@GetMapping(path = "/employees")
	public List<Employee> findAllEmployees() {
		return employeeService.findAllEmployees();
	}

}
