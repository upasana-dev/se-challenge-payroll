package com.wave.payroll.data.service;

import com.wave.payroll.model.Employee;

public interface EmployeeDataService {

	/**
	 * Retrieves an {@link Employee} based on its business ID from the database. If
	 * none are found, a new Employee is created and returned
	 * 
	 * @param businessId The business ID based on which the retrieval is made
	 * @return Corresponding Employee
	 */
	public Employee createOrGetEmployee(Long businessId);

}
