package com.wave.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wave.payroll.service.PayrollReportService;
import com.wave.payroll.service.dto.PayrollReport;

@RestController
@RequestMapping("/payroll")
public class PayrollController {

	@Autowired
	private PayrollReportService reportService;

	@GetMapping("/report")
	public PayrollReport generatePayrollReport() {
		return reportService.generatePayrollReport();
	}

}
