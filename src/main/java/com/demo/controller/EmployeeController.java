package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.UpdateEmployeeDTO;
import com.demo.entity.Employee;
import com.demo.messaging.MessageSender;
import com.demo.service.EmployeeService;
import com.demo.service.EmployeeTransTemplateService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeTransTemplateService employeeTransTemplateService;

	@Autowired
	private MessageSender messageSender;

	@GetMapping(path = "/")
	public String hello() {
		return "Welcome to Spring";
	}

	@GetMapping(path = "/employees")
	public List<Employee> findAllEmployees() {
		return employeeService.findAllEmployees();
	}

	@PostMapping(path = "/update/salary")
	public void updateEmployeeSalary(@RequestBody UpdateEmployeeDTO dto) throws Exception {
		employeeService.updateEmployeeSalary(dto);
	}

	@PostMapping(path = "/update/salary/template")
	public void updateEmployeeSalaryWithTemplate(@RequestBody UpdateEmployeeDTO dto) throws Exception {
		employeeTransTemplateService.updateEmployeeSalary(dto);
	}

	@PostMapping(path = "/update/name")
	public void updateEmployeeName(@RequestBody UpdateEmployeeDTO dto) throws Exception {
		employeeService.updateEmployeeName(dto);
	}

	@PostMapping(path = "/message")
	public void sendMessage() {
		messageSender.sendMessage("This is sample messge");
	}

}
