package com.wave.payroll.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * Represents an Employee in the Payroll system. Payroll data is generated per
 * Employee
 */
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/**
	 * This is an internal ID that will not be exposed to the customer
	 */
	private Long id;

	/**
	 * This is the business identifier for the employee
	 */
	private Long businessId;

	/**
	 * Links an Employee to all their corresponding {@link EmployeeEffort}
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMPLOYEE_ID")
	@OrderBy("effortDate")
	private List<EmployeeEffort> employeeEfforts = new ArrayList<>();

	public List<EmployeeEffort> getEmployeeEfforts() {
		return employeeEfforts;
	}

	public void setEmployeeEfforts(List<EmployeeEffort> employeeEfforts) {
		this.employeeEfforts = employeeEfforts;
	}

	public Long getId() {
		return id;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public Employee(Long businessId, List<EmployeeEffort> employeeEfforts) {
		super();
		this.businessId = businessId;
		this.employeeEfforts = employeeEfforts;
	}

	public Employee(Long businessId) {
		super();
		this.businessId = businessId;
	}

	public Employee() {
		super();
	}
}
