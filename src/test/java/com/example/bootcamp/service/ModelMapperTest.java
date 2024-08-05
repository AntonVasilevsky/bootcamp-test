package com.example.bootcamp.service;

import com.example.bootcamp.dto.EmployeeDto;
import com.example.bootcamp.dto.SimpleEmployeeDto;
import com.example.bootcamp.model.Employee;
import com.example.bootcamp.model.Project;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class ModelMapperTest {
    @Autowired
    ModelMapper modelMapper;
    private Employee e1 = Employee.builder()
            .name("A")
            .surname("B")
            .patronymic("C")
            .email("d@gmail.com")
            .projects(Set.of(new Project(1, "A", "A", new HashSet<>())))
            .build();

    @Test
    void toDto_should_Map_Employee_To_Employee_Dto_Correct() {

        EmployeeDto expectedDto = EmployeeDto.builder()
                .name("A")
                .surname("B")
                .patronymic("C")
                .email("d@gmail.com")
                .build();

        EmployeeDto actual = modelMapper.map(e1, EmployeeDto.class);

        assertThat(expectedDto)
                .isEqualTo(actual);
    }
    @Test
    void toSimpleEmployeeDto_should_map_employee_to_simpleEmployeeDto_correct() {
        SimpleEmployeeDto expectedDto = SimpleEmployeeDto.builder()
                .name("A")
                .surname("B")
                .patronymic("C")
                .build();

        SimpleEmployeeDto actual = modelMapper.map(e1, SimpleEmployeeDto.class);

        assertThat(actual)
                .isEqualTo(expectedDto);

    }

}
