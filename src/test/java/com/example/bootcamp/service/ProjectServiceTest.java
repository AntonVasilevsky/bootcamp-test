package com.example.bootcamp.service;

import com.example.bootcamp.dto.ProjectDto;
import com.example.bootcamp.model.Employee;
import com.example.bootcamp.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private ProjectService projectService;
    private Employee e1;
    private Project projectOneEmployee;

    @BeforeEach
    void setup() {
        e1 = Employee.builder()
                .name("e1")
                .surname("surname1")
                .patronymic("patron1")
                .build();

        projectOneEmployee = Project.builder()
                .projectId(3)
                .name("projectOneEmployee")
                .description("with 1 employee")
                .employees(Set.of(e1))
                .build();
    }

    @Test
    void toDtoShouldReturnProjectDto() {
        ProjectDto expected = ProjectDto.builder()
                .name("projcetOneEmplyee")
                .description("with 1 employee")
                .build();
        when(modelMapper.map(projectOneEmployee, ProjectDto.class)).thenReturn(expected);

        ProjectDto actual = projectService.toDto(projectOneEmployee);

        assertThat(expected)
                .isEqualTo(actual);
    }
}