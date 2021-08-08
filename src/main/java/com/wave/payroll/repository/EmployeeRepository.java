package com.wave.payroll.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wave.payroll.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	List<Employee> findAllByOrderByBusinessId();

	Optional<Employee> findByBusinessId(Long businessId);

}
