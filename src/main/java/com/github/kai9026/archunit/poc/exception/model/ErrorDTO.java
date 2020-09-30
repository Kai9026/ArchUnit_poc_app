package com.github.kai9026.archunit.poc.exception.model;

import java.io.Serializable;
import java.util.Map;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO implements Serializable {

    private static final long serialVersionUID = -1533790332809954683L;
    
    private String message;
    private Map<String, String> errors;

}
