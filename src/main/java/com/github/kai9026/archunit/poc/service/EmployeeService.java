package com.github.kai9026.archunit.poc.service;

import java.util.List;

import com.github.kai9026.archunit.poc.model.dto.EmployeeDTO;

public interface EmployeeService {

	List<EmployeeDTO> getEmployees();

	EmployeeDTO createEmployee(EmployeeDTO employee);

	void updateEmployee(Long id, EmployeeDTO employeeToUpdate);

	void deleteEmployee(Long id);
    
}
