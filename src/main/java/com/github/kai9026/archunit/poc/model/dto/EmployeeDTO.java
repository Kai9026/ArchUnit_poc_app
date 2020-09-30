package com.github.kai9026.archunit.poc.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO implements Serializable {

    private static final long serialVersionUID = -2520463206526841019L;
    
    private Long id;
    @NotBlank
    private String firstName;
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String telephone;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    @NotNull
    private Integer seniority;
    
}
