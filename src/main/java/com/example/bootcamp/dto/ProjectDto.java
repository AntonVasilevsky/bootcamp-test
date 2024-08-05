package com.example.bootcamp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    @Size(min = 1, max = 60)
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Field must contain only Latin letters")
    @NotBlank
    private String name;
    @Size(min = 1, max = 150)
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Field must contain only Latin letters")
    @NotBlank
    private String description;
    private List<SimpleEmployeeDto> employees = new ArrayList<>();
}
