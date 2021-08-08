package com.wave.payroll.service.utils;

import java.time.LocalDate;

import com.wave.payroll.model.constants.JobGroup;
import com.wave.payroll.service.dto.PayoutPeriod;

public class PayrollComputationUtils {

	private static final int MONTHLY_MID_POINT = 15;

	public static PayoutPeriod findApplicablePayoutPeriod(LocalDate jobDate) {

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

	public static double calculateWages(double numberOfHoursWorked, JobGroup jobGroup) {
		return numberOfHoursWorked * jobGroup.getHourlyWage();
	}

}
