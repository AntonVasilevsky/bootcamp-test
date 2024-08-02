package com.example.bootcamp.controller;

import com.example.bootcamp.dto.EmployeeDto;
import com.example.bootcamp.model.Employee;
import com.example.bootcamp.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    private final ModelMapper modelMapper;

    public EmployeeController(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public HttpStatus addEmployee(@RequestBody @Valid EmployeeDto dto) {
        return HttpStatus.ACCEPTED;
    }
    @GetMapping("/all")
    public List<EmployeeDto>getAll() {
        List<Employee> all = employeeService.findAll();
        List<EmployeeDto> employeeDtos = convertAllToDto(all);
        return convertAllToDto(employeeService.findAll());
    }

    public List<EmployeeDto> convertAllToDto(List<Employee> all) {
        return all.stream()
                .map(this::convertOneToDto)
                .toList();
    }
    public EmployeeDto convertOneToDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }
    @GetMapping("/one")
    public EmployeeDto getOne() {
        Employee employee = employeeService.findAll().get(0);
        return convertOneToDto(employee);
    }

}
