package com.wave.payroll.data.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wave.payroll.data.service.EmployeeDataService;
import com.wave.payroll.model.Employee;
import com.wave.payroll.repository.EmployeeRepository;

/**
 * Service targetted at data operations relating to {@link Employee}
 */

@Service
public class EmployeeDataServiceImpl implements EmployeeDataService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee createOrGetEmployee(Long businessId) {

		Employee employee;

		Optional<Employee> employeeOptional = employeeRepository.findByBusinessId(businessId);

		if (employeeOptional.isPresent()) {
			employee = employeeOptional.get();
		} else {
			employee = employeeRepository.save(new Employee(businessId));
		}

		return employee;
	}

}
