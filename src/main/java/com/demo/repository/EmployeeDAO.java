package com.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.Employee;

@Repository
@Transactional(readOnly = true)
public class EmployeeDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public Employee findById(Long id) {
		return entityManager.find(Employee.class, id);
	}

	/**
	 * This update will be committed only at the end of the calling method in
	 * service layer. If there is an exception in service layer after calling this
	 * method then transaction will be rolled back. Use propagation REQUIRES_NEW to
	 * initiate a new transaction to persist this object irrespective of failure in
	 * service layer.
	 * 
	 * @param employee
	 */
	public void updateEmployeeSalary(Employee employee) {
		entityManager.merge(employee);
	}

	/**
	 * Name will be updated even if there is an exception in service layer.
	 * 
	 * @param employee
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateEmployeeName(Employee employee) {
		entityManager.merge(employee);
	}

}
