package com.wave.payroll.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wave.payroll.model.Employee;
import com.wave.payroll.model.EmployeeEffort;
import com.wave.payroll.model.constants.JobGroup;
import com.wave.payroll.service.dto.EmployeeEffortData;

/**
 * Data-centric service that performs functions relating to employee effort data
 */
@Service
public class EmployeeEffortDataServiceImpl {

	@Autowired
	private EmployeeDataService employeeDataService;

	@Transactional
	public void updateEmployeeEffortData(Long userBusinessId, List<EmployeeEffortData> effortDataList) {

		Employee employee = employeeDataService.createOrGetEmployee(userBusinessId);

		List<EmployeeEffort> employeeEffortList = new ArrayList<>();

		for (EmployeeEffortData effortData : effortDataList) {
			employeeEffortList.add(mapToEmployeeEffort(effortData));
		}

		// Save all the new employee efforts. The new list is added to the existing list
		// instead of replacing it avoid deleting the existing data
		employee.getEmployeeEfforts().addAll(employeeEffortList);

	}

	private EmployeeEffort mapToEmployeeEffort(EmployeeEffortData effortData) {
		EmployeeEffort employeeEffort = new EmployeeEffort();

		employeeEffort.setEffortDate(effortData.getEffortDate());
		employeeEffort.setHoursWorked(effortData.getHoursWorked());
		employeeEffort.setWageGroup(JobGroup.convertToJobGroup(effortData.getJobGroupName()));

		return employeeEffort;
	}

}
