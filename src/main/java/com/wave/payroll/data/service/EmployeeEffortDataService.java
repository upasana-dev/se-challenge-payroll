package com.wave.payroll.data.service;

import java.util.List;

import com.wave.payroll.service.dto.EmployeeEffortData;

public interface EmployeeEffortDataService {

	public void updateEmployeeEffortData(Long userBusinessId, List<EmployeeEffortData> effortDataList);

}