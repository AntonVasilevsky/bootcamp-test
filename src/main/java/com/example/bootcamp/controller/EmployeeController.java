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


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody @Valid EmployeeDto dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(bindingResult.getAllErrors()
                            .toString());
        }
        employeeService.add(employeeService.toEntity(dto));
        log.info("new project added\n{}", dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


    @GetMapping("/all")
    public List<EmployeeDto>getAll() {
        return employeeService.manyToDto(employeeService.findAll());
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
}
