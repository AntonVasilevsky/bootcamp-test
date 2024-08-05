package com.example.bootcamp.controller;

import com.example.bootcamp.dto.ProjectDto;
import com.example.bootcamp.dto.SimpleEmployeeDto;
import com.example.bootcamp.model.Employee;
import com.example.bootcamp.model.Project;
import com.example.bootcamp.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ProjectControllerTest {

    @MockBean
    private ProjectService projectService;
    @Autowired
    private ProjectController projectController;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private ObjectMapper objectMapper;
    @MockBean
    private ModelMapper modelMapper;

    private MockMvc mockMvc;
    private Employee e1;
    private Employee e2;
    private Project projectNoEmployees;
    private Project projectOneEmployee;
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

        projectNoEmployees = Project.builder()
                .projectId(1)
                .name("projectNoEmployees")
                .description("no employee")
                .employees(new HashSet<>())
                .build();

        projectOneEmployee = Project.builder()
                .projectId(3)
                .name("projectOneEmployee")
                .description("with 1 employee")
                .employees(Set.of(e1))
                .build();
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
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
                .name("projectNoEmployees")
                .description("with 1 employee")
                .employees(List.of(simpleEmployeeDto))
                .build();
        when(projectService.findAll()).thenReturn(List.of(projectOneEmployee));
        when(projectService.toDto(projectOneEmployee)).thenReturn(projectDto);

        int actual = projectController.showAll().size();

        assertThat(actual)
                .isEqualTo(expectedSize);
    }
    @Test
    void addOne_should_return_accepted() {
        Project project = Project.builder()
                .name("projectNoEmployees")
                .description("no employee")
                .build();
        ProjectDto dto = ProjectDto.builder()
                .name("projectNoEmployees")
                .description("no employee")
                .build();
        when(modelMapper.map(any(ProjectDto.class), eq(Project.class)))
                .thenReturn(project);
        when(projectService.add(any(Project.class)))
                .thenReturn(project);

        try {
            String string = objectMapper.writeValueAsString(dto);
            System.out.println(string);
            mockMvc.perform(post("/projects/add")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isAccepted());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}