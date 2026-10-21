package com.example.employee.service;

import com.example.employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto create(EmployeeDto dto);
    List<EmployeeDto> getAll();
    EmployeeDto getById(Long id);
    EmployeeDto update(Long id, EmployeeDto dto);
    void delete(Long id);
    List<EmployeeDto> search(String name);
}
