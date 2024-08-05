package com.example.bootcamp.controller;

import com.example.bootcamp.dto.ProjectDto;
import com.example.bootcamp.dto.SimpleEmployeeDto;
import com.example.bootcamp.model.Employee;
import com.example.bootcamp.model.Project;
import com.example.bootcamp.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProjectControllerTest {
    @MockBean
    private ProjectService projectService;
    @Autowired
    private ProjectController projectController;
    private Employee e1;
    private Employee e2;
    private Employee e3;
    private Project projectNoEmployees;
    private Project projetOneEmployee;
    @BeforeEach
    void setup() {
        e1 = Employee.builder()
                .name("e1")
                .surname("surname1")
                .patronymic("patron1")
                .build();
        e2 = Employee.builder()
                .name("e2")
                .surname("surname2")
                .patronymic("patron2")
                .build();
        e3 = Employee.builder()
                .name("e3")
                .surname("surname3")
                .patronymic("patron3")
                .build();
        projectNoEmployees = Project.builder()
                .projectId(1)
                .name("projectNoEmployees")
                .description("no employee")
                .employees(new HashSet<>())
                .build();

        projetOneEmployee = Project.builder()
                .projectId(3)
                .name("projetOneEmplyee")
                .description("with 1 employee")
                .employees(Set.of(e1))
                .build();
    }

    @Test
    void showAll_should_return_empty_list() {
        int expectedSize = 0;
        ProjectDto projectDto = ProjectDto.builder()
                .name("projectNoEmployees")
                .description("no employee")
                .employees(Collections.EMPTY_LIST)
                .build();
        when(projectService.findAll()).thenReturn(List.of(projectNoEmployees));
        when(projectService.toDto(projectNoEmployees)).thenReturn(projectDto);

        int actual = projectController.showAll().size();

        assertThat(actual)
                .isEqualTo(expectedSize);
    }

    @Test
    void showAll_should_return_list_size_one() {
        int expectedSize = 1;
        SimpleEmployeeDto simpleEmployeeDto = SimpleEmployeeDto.builder()
                .name("e1")
                .surname("surname1")
                .patronymic("patron1")
                .build();
        ProjectDto projectDto = ProjectDto.builder()
                .name("projetOneEmplyee")
                .description("with 1 employee")
                .employees(List.of(simpleEmployeeDto))
                .build();
        when(projectService.findAll()).thenReturn(List.of(projetOneEmployee));
        when(projectService.toDto(projetOneEmployee)).thenReturn(projectDto);


        int actual = projectController.showAll().size();

        assertThat(actual)
                .isEqualTo(expectedSize);
    }

}