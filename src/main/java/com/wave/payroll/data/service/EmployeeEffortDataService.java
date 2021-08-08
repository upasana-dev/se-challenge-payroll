package com.wave.payroll.data.service;

import java.util.List;

import com.wave.payroll.model.EmployeeEffort;
import com.wave.payroll.service.info.dto.EmployeeEffortData;

/**
 * Data-centric service that performs functions relating to
 * {@link EmployeeEffort}
 */
public interface EmployeeEffortDataService {

	/**
	 * Updates the specified employee with the specified employee job effort data in
	 * the database
	 * 
	 * @param employeeBusinessId The ID of the employee for which the data is to be
	 *                           updated
	 * @param effortDataList     The corresponding job effort data depicted by
	 *                           {@link EmployeeEffortData}
	 */
	public void updateEmployeeEffortData(Long employeeBusinessId, List<EmployeeEffortData> effortDataList);

}
