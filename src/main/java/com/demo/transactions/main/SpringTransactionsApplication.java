package com.demo.transactions.main;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.demo.entity.Employee;
import com.demo.repository.EmployeeRepository;

@SpringBootApplication(scanBasePackages = { "com.demo.*" })
@EnableJpaRepositories(basePackages = "com.demo.repository")
@EntityScan(basePackages = "com.demo.entity")
@EnableTransactionManagement
public class SpringTransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTransactionsApplication.class, args);
	}

	@Bean
	CommandLineRunner startup(EmployeeRepository employeeRepository) {
		return args -> {
			System.out.println("===============  SPRING TRANSACTIONS TESTING ====================");
			employeeRepository.save(new Employee("KARTHIK", "CANADA", BigDecimal.valueOf(100)));
		};
	}

}
