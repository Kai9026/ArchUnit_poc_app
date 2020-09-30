package com.github.kai9026.archunit.poc.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.github.kai9026.archunit.poc.model.dto.EmployeeDTO;
import com.github.kai9026.archunit.poc.service.EmployeeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(this.employeeService.getEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employee) {
        EmployeeDTO newEmployee = this.employeeService.createEmployee(employee);
        URI newEmployeeURI = URI.create("");
        return ResponseEntity.created(newEmployeeURI).body(newEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployeeInfo(@Valid @RequestBody EmployeeDTO employeeToUpdate,
                                                   @PathVariable Long id) {
        this.employeeService.updateEmployee(id, employeeToUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        this.employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
    
}
