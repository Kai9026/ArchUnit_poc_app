package com.github.kai9026.archunit.poc.mapper.impl;

import com.github.kai9026.archunit.poc.mapper.Mapper;
import com.github.kai9026.archunit.poc.model.dto.EmployeeDTO;
import com.github.kai9026.archunit.poc.repository.entity.Employee;

import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper implements Mapper<EmployeeDTO, Employee> {

	@Override
	public EmployeeDTO toDTO(Employee entity) {
        return EmployeeDTO.builder()
                            .id(entity.getId())
                            .firstName(entity.getFirstName())
                            .lastName(entity.getLastName())
                            .email(entity.getEmail())
                            .build();
    }

	@Override
	public Employee fromDTO(EmployeeDTO dto) {
        return Employee.builder()
                            .firstName(dto.getFirstName())
                            .lastName(dto.getLastName())
                            .email(dto.getEmail())
                            .telephone(dto.getTelephone())
                            .birthDate(dto.getBirthDate())
                            .seniority(dto.getSeniority())
                            .build();
            
	}
    
}