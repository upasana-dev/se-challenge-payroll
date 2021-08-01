package com.wave.payroll.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wave.payroll.model.EmployeeEffort;

@Repository
public interface EmployeeEffortRepository extends CrudRepository<EmployeeEffort, Long> {

	// List<EmployeeEffort> findBy

}
