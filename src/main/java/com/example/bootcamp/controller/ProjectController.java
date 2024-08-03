package com.example.bootcamp.controller;

import com.example.bootcamp.dto.ProjectDto;
import com.example.bootcamp.dto.SimpleEmployeeDto;
import com.example.bootcamp.model.Employee;
import com.example.bootcamp.model.Project;
import com.example.bootcamp.service.EmployeeService;
import com.example.bootcamp.service.ProjectService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
@Log4j2
public class ProjectController {
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    public ProjectController(
            ProjectService projectService,
            EmployeeService employeeService
    ) {
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProject(
            @RequestBody @Valid ProjectDto dto
    ) {

        Project project = projectService.toEntity(dto);
        projectService.add(project);
        log.info("new project added\n{}", dto);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/{projectId}/employees/{employeeId}")
    public ResponseEntity<String> addEmployeeToProjectById(
            @PathVariable int projectId,
            @PathVariable int employeeId
    ) {
        Optional<Project> pOptional = projectService.findById(projectId);
        if (pOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(String.format("There is no project with id:%d", projectId));
        }
        Optional<Employee> eOptional = employeeService.findById(employeeId);
        if (eOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(String.format("There is no employee with id:%d", employeeId));
        }
        projectService.addEmployeeToProject(pOptional.orElseThrow(), eOptional.orElseThrow());

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/all")
    public List<ProjectDto> showAll() {
        return projectService.findAll().stream()
                .map(project -> {
                    ProjectDto projectDto = projectService.toDto(project);
                    List<SimpleEmployeeDto> simpleEmployeeDtoList = project.getEmployees().stream()
                            .map(employeeService::toSimpleDto)
                            .toList();
                    projectDto.setEmployees(simpleEmployeeDtoList);

                    return projectDto;
                })
                .sorted(Comparator.comparing(ProjectDto::getName))
                .toList();
    }

}
