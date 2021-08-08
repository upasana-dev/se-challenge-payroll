package com.wave.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wave.payroll.info.service.PayrollInfoService;
import com.wave.payroll.report.service.PayrollReportService;
import com.wave.payroll.service.dto.PayrollReport;

@RestController
@RequestMapping("/payroll")
public class PayrollController {

	@Autowired
	private PayrollReportService reportService;

	@Autowired
	private PayrollInfoService employeeEffortService;

	@GetMapping("/report")
	public PayrollReport generatePayrollReport() {
		return reportService.generatePayrollReport();
	}

	@PostMapping("/data")
	public void importPayrollData(@RequestParam("file") MultipartFile importData) {

		if (importData == null) {
			throw new RuntimeException("Invalid file passed in parameter!");
		}
		employeeEffortService.importEmployeeEffortInfo(importData);
	}

}
