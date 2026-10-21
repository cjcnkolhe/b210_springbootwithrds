package com.example.employee.service;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    private EmployeeDto toDto(Employee e) {
        EmployeeDto d = new EmployeeDto();
        d.setId(e.getId());
        d.setName(e.getName());
        d.setEmail(e.getEmail());
        d.setPhone(e.getPhone());
        d.setDepartment(e.getDepartment());
        d.setSalary(e.getSalary());
        d.setCity(e.getCity());
        d.setJoiningDate(e.getJoiningDate());
        return d;
    }

    private Employee toEntity(EmployeeDto d) {
        Employee e = new Employee();
        e.setName(d.getName());
        e.setEmail(d.getEmail());
        e.setPhone(d.getPhone());
        e.setDepartment(d.getDepartment());
        e.setSalary(d.getSalary());
        e.setCity(d.getCity());
        e.setJoiningDate(d.getJoiningDate());
        return e;
    }

    @Override
    public EmployeeDto create(EmployeeDto dto) {
        return toDto(repository.save(toEntity(dto)));
    }

    @Override
    public List<EmployeeDto> getAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public EmployeeDto getById(Long id) {
        return repository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
    }

    @Override
    public EmployeeDto update(Long id, EmployeeDto dto) {
        Employee e = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
        e.setName(dto.getName());
        e.setEmail(dto.getEmail());
        e.setPhone(dto.getPhone());
        e.setDepartment(dto.getDepartment());
        e.setSalary(dto.getSalary());
        e.setCity(dto.getCity());
        e.setJoiningDate(dto.getJoiningDate());
        return toDto(repository.save(e));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Employee not found: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<EmployeeDto> search(String name) {
        return repository.findByNameContainingIgnoreCase(name).stream().map(this::toDto).toList();
    }
}
