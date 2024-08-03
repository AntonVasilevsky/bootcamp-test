package com.example.bootcamp.service;

import com.example.bootcamp.dto.EmployeeDto;
import com.example.bootcamp.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class ModelMapperTest {
    @Autowired
    ModelMapper modelMapper;

    @Test
    void shouldMapEmployeeToEmployeeDtoCorrect() {
        Employee employee = Employee.builder()
                .name("A")
                .surname("B")
                .patronymic("C")
                .email("d@gmail.com")
                .build();
        EmployeeDto expectedDto = EmployeeDto.builder()
                .name("A")
                .surname("B")
                .patronymic("C")
                .email("d@gmail.com")
                .build();

        EmployeeDto actual = modelMapper.map(employee, EmployeeDto.class);

        assertThat(expectedDto)
                .isEqualTo(actual);

    }
}
