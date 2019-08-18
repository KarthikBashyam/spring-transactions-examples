package com.demo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class UpdateSalaryDTO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1170901090768072559L;

	private Long id;

	private BigDecimal salary;

	public UpdateSalaryDTO() {
		super();
	}

	public UpdateSalaryDTO(Long id, BigDecimal salary) {
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

}
