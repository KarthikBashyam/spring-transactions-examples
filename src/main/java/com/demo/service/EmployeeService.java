package com.demo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.Employee;
import com.demo.repository.EmployeeDAO;
import com.demo.repository.EmployeeRepository;

/**
 * The default transaction propagation is REQUIRED.
 * 
 * @author Karthik
 *
 */
@Service
@Transactional(readOnly = true)
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeDAO employeeDAO;

	public List<Employee> findAllEmployees() {
		return employeeRepository.findAll();
	}

	/**
	 * The update method in DAO will be committed or Rollback at the end of
	 * updateEmployeeSalary method.
	 * 
	 * @param empId
	 * @param salary
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateEmployeeSalary(Long empId, BigDecimal salary) throws Exception {
		Employee emp = employeeDAO.findById(empId);
		emp.setSalary(salary);
		employeeDAO.updateEmployeeSalary(emp);
		mimicException();
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void updateEmployeeName(Long empId, String name) throws Exception {
		Employee emp = employeeDAO.findById(empId);
		emp.setName(name);
		employeeDAO.updateEmployeeName(emp);
		mimicException();
	}

	private void mimicException() throws Exception {
		throw new Exception("Mimicking Exception");
	}

}
