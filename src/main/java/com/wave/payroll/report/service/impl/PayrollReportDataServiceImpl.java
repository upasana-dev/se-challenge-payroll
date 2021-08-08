package com.wave.payroll.report.service.impl;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wave.payroll.model.EmployeeEffort;
import com.wave.payroll.model.constants.JobGroup;
import com.wave.payroll.report.service.PayrollReportDataService;
import com.wave.payroll.service.dto.PayoutPeriod;

/**
 * Data-centric service targetted at performing data-specific logic related to
 * employee payroll report generation
 *
 */
@Service
public class PayrollReportDataServiceImpl implements PayrollReportDataService {

	private static final int MONTHLY_MID_POINT = 15;

	@Override
	public Map<PayoutPeriod, Double> computeWagesByPayoutPeriod(List<EmployeeEffort> employeeEfforts) {

		// Keeps track of the total wages for each payout period
		Map<PayoutPeriod, Double> payoutPeriodWages = new LinkedHashMap<>();

		for (EmployeeEffort effort : employeeEfforts) {

			PayoutPeriod payoutPeriod = findApplicablePayoutPeriod(effort.getEffortDate());

			// In case it is possible for employees to operate across job groups, the wages
			// are calculated individually and then summed up
			double wageForDay = calculateWages(effort.getHoursWorked(), effort.getWageGroup());

			addWagesForPayoutPeriod(payoutPeriodWages, payoutPeriod, wageForDay);

		}

		return payoutPeriodWages;
	}

	void addWagesForPayoutPeriod(Map<PayoutPeriod, Double> payoutPeriodEfforts, PayoutPeriod payoutPeriod,
			double wageForDay) {
		double totalWages;

		if (!payoutPeriodEfforts.containsKey(payoutPeriod)) {
			// This is the first entry for this payout period, so the effort will be added
			// as is

			totalWages = wageForDay;

		} else {
			// There are existing entries so the wages are simply added to existing total
			totalWages = wageForDay + payoutPeriodEfforts.get(payoutPeriod);
		}

		payoutPeriodEfforts.put(payoutPeriod, totalWages);
	}

	PayoutPeriod findApplicablePayoutPeriod(LocalDate jobDate) {

		LocalDate periodStartDate;
		LocalDate periodEndDate;

		if (jobDate.getDayOfMonth() > MONTHLY_MID_POINT) {
			// Second half of the month
			periodStartDate = jobDate.withDayOfMonth(MONTHLY_MID_POINT + 1);
			periodEndDate = jobDate.withDayOfMonth(jobDate.lengthOfMonth());
		} else {
			// First half of the month
			periodStartDate = jobDate.withDayOfMonth(1);
			periodEndDate = jobDate.withDayOfMonth(MONTHLY_MID_POINT);
		}

		return new PayoutPeriod(periodStartDate, periodEndDate);
	}

	double calculateWages(double numberOfHoursWorked, JobGroup jobGroup) {
		return numberOfHoursWorked * jobGroup.getHourlyWage();
	}
}
