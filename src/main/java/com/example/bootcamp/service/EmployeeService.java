package com.example.bootcamp.service;

import com.example.bootcamp.model.Employee;
import com.example.bootcamp.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }
    public Optional<Employee> findByName(String name) {
        return repository.findByName(name);
    }
    public List<Employee> findAll() {
        return repository.findAll();
    }
    public void deleteOne(Employee e) {
        repository.delete(e);
    }
    public void add(Employee e) {
        repository.save(e);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
