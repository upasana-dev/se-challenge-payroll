package com.wave.payroll.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wave.payroll.model.PayrollDataReport;

@Repository
public interface PayrollDataReportRepository extends CrudRepository<PayrollDataReport, Long> {

	boolean existsByReportName(String reportName);

}
