package com.demo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class UpdateEmployeeDTO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1170901090768072559L;

	private Long id;

	private String name;

	private BigDecimal salary;

	public UpdateEmployeeDTO() {
		super();
	}

	public UpdateEmployeeDTO(Long id, BigDecimal salary) {
		super();
		this.id = id;
		this.salary = salary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
