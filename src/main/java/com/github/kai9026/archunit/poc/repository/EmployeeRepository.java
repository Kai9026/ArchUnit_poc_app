package com.github.kai9026.archunit.poc.repository;

import java.util.List;

import com.github.kai9026.archunit.poc.repository.entity.Employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findAll();
}
