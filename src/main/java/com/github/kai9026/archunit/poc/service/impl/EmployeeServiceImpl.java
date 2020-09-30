package com.github.kai9026.archunit.poc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.github.kai9026.archunit.poc.mapper.impl.EmployeeMapper;
import com.github.kai9026.archunit.poc.model.dto.EmployeeDTO;
import com.github.kai9026.archunit.poc.repository.EmployeeRepository;
import com.github.kai9026.archunit.poc.repository.entity.Employee;
import com.github.kai9026.archunit.poc.service.EmployeeService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

	@Override
	public List<EmployeeDTO> getEmployees() {
        return this.employeeRepository.findAll().stream()
                                                    .map(this.employeeMapper::toDTO)
                                                    .collect(Collectors.toList());
	}

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO newEmployee) {
        Employee employeeToSave = this.employeeMapper.fromDTO(newEmployee);
		return this.employeeMapper.toDTO(this.employeeRepository.save(employeeToSave));
	}

	@Override
	public void updateEmployee(Long id, EmployeeDTO updatedEmployee) {
		Employee employeeToUpdate = this.employeeMapper.fromDTO(updatedEmployee);
		employeeToUpdate.setId(id);
	    this.employeeMapper.toDTO(this.employeeRepository.save(employeeToUpdate));
	}

	@Override
	public void deleteEmployee(Long id) {
        this.employeeRepository.deleteById(id);
	}
    
}
