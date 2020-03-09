package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dto.UpdateEmployeeDTO;
import com.demo.entity.Employee;
import com.demo.messaging.MessageSender;
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

	@Autowired
	private MessageSender messageSender;

	public List<Employee> findAllEmployees() {
		return employeeDAO.getEmployees();
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
	public void updateEmployeeSalary(UpdateEmployeeDTO dto) throws Exception {
		Employee emp = employeeDAO.findById(dto.getId());
		emp.setSalary(dto.getSalary());
		employeeDAO.updateEmployeeSalary(emp);
		// JMS message will roll-back if there is an exception before returning from
		// this method.Can use Propagation.REQUIRES_NEW to send JMS message even in case
		// of exceptions.
		messageSender.sendMessage("Employee salary updated");
		mimicException();
	}

	@Transactional(rollbackFor = Exception.class)
	public void updateEmployeeName(UpdateEmployeeDTO dto) throws Exception {
		Employee emp = employeeDAO.findById(dto.getId());
		emp.setName(dto.getName());
		employeeDAO.updateEmployeeName(emp);
		mimicException();
	}

	private void mimicException() throws Exception {
		throw new Exception("Mimicking Exception");
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveEmployee(Employee emp) {
		employeeDAO.saveEmployee(emp);
	}

	public List<Employee> getEmpNameByCity(String city) {
		return employeeDAO.getEmpNameByCity(city);
	}

}
