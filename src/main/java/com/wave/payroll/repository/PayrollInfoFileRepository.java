package com.wave.payroll.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wave.payroll.model.PayrollInfoFile;

@Repository
public interface PayrollInfoFileRepository extends CrudRepository<PayrollInfoFile, Long> {

	boolean existsByFileName(String reportName);

}
