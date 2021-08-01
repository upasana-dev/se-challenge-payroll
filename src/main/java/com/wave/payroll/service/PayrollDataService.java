package com.wave.payroll.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wave.payroll.model.EmployeeEffort;
import com.wave.payroll.service.dto.PayoutPeriod;
import com.wave.payroll.service.utils.PayrollComputationUtils;

/**
 * Data-centric service targetted at performing data-specific logic related to
 * employee payroll
 *
 */
@Service
public class PayrollDataService {

	/**
	 * Computes the total work put in across payout periods
	 * 
	 * @param employeeEfforts The history of efforts across different periods of
	 *                        times
	 * @return A mapping of the total work put in for different payout periods
	 */
	public Map<PayoutPeriod, Double> computeEmployeeEfforts(List<EmployeeEffort> employeeEfforts) {

		// Keeps track of the total worked hours for each payout period
		Map<PayoutPeriod, Double> payoutPeriodEfforts = new HashMap<>();

		for (EmployeeEffort effort : employeeEfforts) {

			PayoutPeriod payoutPeriod = PayrollComputationUtils.findApplicablePayoutPeriod(effort.getEffortDate());

			// In case it is possible for employees to operate across job groups, the wages
			// are calculated individually and then summed up
			double wageForDay = PayrollComputationUtils.calculateWages(effort.getHoursWorked(), effort.getWageGroup());

			calculateTotalWagesForPayoutPeriod(payoutPeriodEfforts, payoutPeriod, wageForDay);

		}

		return payoutPeriodEfforts;
	}

	void calculateTotalWagesForPayoutPeriod(Map<PayoutPeriod, Double> payoutPeriodEfforts, PayoutPeriod payoutPeriod,
			double wageForDay) {
		double totalWages;

		if (!payoutPeriodEfforts.containsKey(payoutPeriod)) {
			// This is the first entry for this payout period, so the effort will be added
			// as is

			totalWages = wageForDay;

		} else {
			totalWages = wageForDay + payoutPeriodEfforts.get(payoutPeriod);
		}

		payoutPeriodEfforts.put(payoutPeriod, totalWages);
	}
}
