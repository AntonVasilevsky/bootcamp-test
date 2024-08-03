package com.example.bootcamp.service;

import com.example.bootcamp.dto.EmployeeDto;
import com.example.bootcamp.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private EmployeeService projectService;

    @Test
    void toDtoShouldReturnCorrectDto() {
        Employee testEmployee = Employee.builder()
                .name("AA")
                .build();
        EmployeeDto dto = EmployeeDto.builder()
                .name("AA")
                .build();
        when(modelMapper.map(testEmployee, EmployeeDto.class)).thenReturn(dto);

        EmployeeDto actual = projectService.toDto(testEmployee);

        assertThat(dto)
                .isEqualTo(actual);
    }
}