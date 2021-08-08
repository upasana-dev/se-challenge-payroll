package com.wave.payroll.report.service;

import java.util.List;
import java.util.Map;

import com.wave.payroll.model.EmployeeEffort;
import com.wave.payroll.service.report.dto.PayoutPeriod;

/**
 * Data-centric service targetted at performing data-specific logic related to
 * employee payroll report generation
 *
 */
public interface PayrollReportDataService {

	/**
	 * Computes the total wages across payout periods
	 * 
	 * @param employeeEfforts The history of efforts across different periods of
	 *                        times
	 * @return A mapping of the total work put in for different payout periods
	 */
	public Map<PayoutPeriod, Double> computeWagesByPayoutPeriod(List<EmployeeEffort> employeeEfforts);

}
