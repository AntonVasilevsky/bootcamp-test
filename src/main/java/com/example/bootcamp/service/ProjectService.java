package com.example.bootcamp.service;

import com.example.bootcamp.dto.EmployeeDto;
import com.example.bootcamp.dto.ProjectDto;
import com.example.bootcamp.model.Employee;
import com.example.bootcamp.model.Project;
import com.example.bootcamp.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements DtoConverter<Project, ProjectDto> {
    private final ProjectRepository repository;
    private final ModelMapper modelMapper;

    public ProjectService(ProjectRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }
    public Optional<Project> findByName(String name) {
        return repository.findByName(name);
    }
    public List<Project> findAll() {
        return repository.findAll();
    }
    public void deleteOne(Project p) {
        repository.delete(p);
    }
    public void add(Project p) {
        repository.save(p);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Project toEntity(ProjectDto dto) {
        return modelMapper.map(dto, Project.class);    }

    @Override
    public ProjectDto toDto(Project project) {
        return modelMapper.map(project, ProjectDto.class);
    }

    @Override
    public List<Project> manyToEntity(List<ProjectDto> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
                .toList();
    }

    @Override
    public List<ProjectDto> manyToDto(List<Project> entityList) {
        return entityList.stream()
                .map(this::toDto)
                .toList();
    }
}
