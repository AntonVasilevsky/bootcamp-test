package com.example.bootcamp.controller;

import com.example.bootcamp.dto.EmployeeDto;
import com.example.bootcamp.model.Employee;
import com.example.bootcamp.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/employee")
@Log4j2
public class EmployeeController {
    private final EmployeeService employeeService;

    private final ModelMapper modelMapper;

    public EmployeeController(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody @Valid EmployeeDto dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
           // getReadableStringErrors(allErrors);

            log.info(allErrors.toString());
            return ResponseEntity.badRequest().body(allErrors.toString());
        }
        log.info(dto);
        Employee employee = convertOneToEntity(dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    private String getReadableStringErrors(List<ObjectError> allErrors) {
        // todo mb finish later
        ObjectError objectError = allErrors.get(0);
        String errorMessage = objectError.getDefaultMessage();

        Object[] arguments = objectError.getArguments();
        DefaultMessageSourceResolvable argument = arguments[0] instanceof DefaultMessageSourceResolvable ?  (DefaultMessageSourceResolvable) arguments[0] : null;
        String defaultMessage = argument.getDefaultMessage();

        return defaultMessage + ":" + errorMessage;
    }

    private Employee convertOneToEntity(EmployeeDto dto) {
        return modelMapper.map(dto, Employee.class);
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
