package com.example.bootcamp.controller;

import com.example.bootcamp.dto.ProjectDto;
import com.example.bootcamp.model.Project;
import com.example.bootcamp.service.ProjectService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
@Log4j2
public class ProjectController {
    private final ProjectService projectService;
    private final ModelMapper modelMapper;

    public ProjectController(ProjectService projectService, ModelMapper modelMapper) {
        this.projectService = projectService;
        this.modelMapper = modelMapper;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addProject(@RequestBody @Valid ProjectDto dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(bindingResult.getAllErrors()
                            .toString());
        }
        Project project = projectService.toEntity(dto);
        projectService.add(project);
        log.info("new project added\n{}", dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
