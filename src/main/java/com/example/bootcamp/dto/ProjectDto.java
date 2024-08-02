package com.example.bootcamp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProjectDto {
    @Size(min = 1, max = 60)
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Field must contain only Latin letters")
    private String name;
    @Size(min = 1, max = 150)
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Field must contain only Latin letters")
    private String description;
}
