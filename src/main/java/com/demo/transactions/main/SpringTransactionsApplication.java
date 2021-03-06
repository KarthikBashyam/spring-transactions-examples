package com.demo.transactions.main;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.demo.entity.Address;
import com.demo.entity.Department;
import com.demo.entity.Employee;
import com.demo.service.EmployeeService;

@SpringBootApplication(scanBasePackages = { "com.demo.*" })
@EnableJpaRepositories(basePackages = "com.demo.repository")
@EntityScan(basePackages = "com.demo.entity")
@EnableTransactionManagement
//@EnableJms
public class SpringTransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTransactionsApplication.class, args);
	}

	@Bean
	CommandLineRunner startup(EmployeeService employeeService) {
		return args -> {
			System.out.println("===============  SPRING TRANSACTIONS TESTING ====================");
			Employee emp = new Employee("KARTHIK", null, BigDecimal.valueOf(100));
			emp.setAddress(Arrays.asList(new Address("TORONTO")));
			emp.setDepartment(new Department("IT"));
			employeeService.saveEmployee(emp);
		};
	}

}
