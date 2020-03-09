package com.demo.repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.Employee;

@Repository
@Transactional(readOnly = true)
public class EmployeeDAO {
	
	private static Log logger = LogFactory.getLog(EmployeeDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public Employee findById(Long id) {
		return entityManager.find(Employee.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployees() {
		Query query = entityManager.createQuery("SELECT e FROM Employee e");
		return (List<Employee>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> getEmpNameByCity(String city) {
		Query query = entityManager.createQuery("SELECT a.employee FROM Address a WHERE a.city=:city");
		query.setParameter("city", city);
		List<Employee> list = (List<Employee>) query.getResultList();
		logger.info("Employee List Size:" + list.size());
		return list;
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

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveEmployee(Employee emp) {
		entityManager.persist(emp);		
	}
	
	//@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Transactional(propagation = Propagation.REQUIRED)
	public void createAddress(Employee emp) {
		
		/*Employee employee = entityManager.find(Employee.class,1l);
		employee.setAddress(new Address("TORONTO"));
		entityManager.persist(employee);*/
		
		//Query query = entityManager.createQuery("UPDATE Employee e set e.country='TESTING'");
		
		Query query = entityManager.createNativeQuery("UPDATE Employee e set e.country='TESTING'");
		query.executeUpdate();
		entityManager.flush();
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void printAddress(Employee emp) {
		Employee employee = entityManager.find(Employee.class,1l);
		//System.out.println("========================>"+employee.getAddress().getCity());
		System.out.println("========================>"+employee.getCountry());
		
	}

}
