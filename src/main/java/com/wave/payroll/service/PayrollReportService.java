package com.wave.payroll.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wave.payroll.model.Employee;
import com.wave.payroll.repository.EmployeeRepository;
import com.wave.payroll.service.dto.EmployeeReport;
import com.wave.payroll.service.dto.PayoutPeriod;
import com.wave.payroll.service.dto.PayrollReport;

/**
 * Service performing logic specific to the generation of the payroll report
 *
 */
@Service
public class PayrollReportService {

	@Autowired
	private PayrollDataService payrollDataService;

	@Autowired
	private EmployeeRepository employeeRepository;

	public PayrollReport generatePayrollReport() {

		List<Employee> applicableEmployees = employeeRepository.findAllByOrderByBusinessId();

		List<EmployeeReport> employeereports = new ArrayList<>();

		for (Employee employee : applicableEmployees) {

			Map<PayoutPeriod, Double> employeePayrollData = payrollDataService
					.computeEmployeeEfforts(employee.getEmployeeEfforts());

			for (PayoutPeriod payoutPeriod : employeePayrollData.keySet()) {

				employeereports.add(generateEmployeeReport(payoutPeriod, employeePayrollData.get(payoutPeriod),
						employee.getBusinessId()));
			}

		}

		PayrollReport payrollReport = new PayrollReport();
		payrollReport.setEmployeeReports(employeereports);

		return payrollReport;

	}

	EmployeeReport generateEmployeeReport(PayoutPeriod payoutPeriod, double totalWages, Long employeeId) {
		return new EmployeeReport(employeeId, payoutPeriod, totalWages);
	}

}
