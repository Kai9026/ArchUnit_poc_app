package com.github.kai9026.archunit.poc.mapper;

public interface Mapper<B, E> {
    
    B toDTO(E entity);
    E fromDTO(B dto);
}
