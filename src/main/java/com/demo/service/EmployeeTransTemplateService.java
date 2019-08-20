package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.demo.dto.UpdateEmployeeDTO;
import com.demo.entity.Employee;
import com.demo.messaging.MessageSender;
import com.demo.repository.EmployeeDAO;

@Service
public class EmployeeTransTemplateService {

	@Autowired
	private TransactionTemplate transactionTemplate;

	@Autowired
	private EmployeeDAO dao;

	@Autowired
	private MessageSender messageSender;

	public void updateEmployeeSalary(UpdateEmployeeDTO dto) {

		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			public void doInTransactionWithoutResult(TransactionStatus status) {

				try {
					Employee emp = dao.findById(dto.getId());
					emp.setSalary(dto.getSalary());
					dao.updateEmployeeSalary(emp);
					messageSender.sendMessage("Employee salary updated");
					mimicException();
				} catch (Exception e) {
					System.out.println("Exception while updating employee salary :" + e.getMessage());
					status.setRollbackOnly();
				}

			}

		});

	}

	private void mimicException() throws Exception {
		throw new Exception("Mimicking Exception");
	}

}
