package com.wave.payroll.report.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wave.payroll.model.Employee;
import com.wave.payroll.report.service.PayrollReportDataService;
import com.wave.payroll.report.service.PayrollReportService;
import com.wave.payroll.repository.EmployeeRepository;
import com.wave.payroll.service.report.dto.EmployeeReport;
import com.wave.payroll.service.report.dto.PayoutPeriod;
import com.wave.payroll.service.report.dto.PayrollReport;

@Service
public class PayrollReportServiceImpl implements PayrollReportService {

	@Autowired
	private PayrollReportDataService payrollDataService;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public PayrollReport generatePayrollReport() {

		List<Employee> applicableEmployees = employeeRepository.findAllByOrderByBusinessId();

		List<EmployeeReport> employeeReportList = new ArrayList<>();

		for (Employee employee : applicableEmployees) {

			Map<PayoutPeriod, Double> employeePayrollData = payrollDataService
					.computeWagesByPayoutPeriod(employee.getEmployeeEfforts());

			for (PayoutPeriod payoutPeriod : employeePayrollData.keySet()) {

				employeeReportList.add(generateEmployeeReport(payoutPeriod, employeePayrollData.get(payoutPeriod),
						employee.getBusinessId()));
			}

		}

		PayrollReport payrollReport = new PayrollReport();
		payrollReport.setEmployeeReports(employeeReportList);

		return payrollReport;

	}

	EmployeeReport generateEmployeeReport(PayoutPeriod payoutPeriod, double totalWages, Long employeeId) {
		return new EmployeeReport(employeeId, payoutPeriod, EmployeeReport.convertAmountPaid(totalWages));
	}

}
