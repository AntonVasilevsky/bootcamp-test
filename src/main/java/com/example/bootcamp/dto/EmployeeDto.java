package com.example.bootcamp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeDto {
    @Size(min = 1, max = 60)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Field must contain only Latin letters")
    private String surname;
    @Size(min = 1, max = 20)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Field must contain only Latin letters")
    private String name;
    @Size(min = 1, max = 40)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Field must contain only Latin letters")
    private String patronymic;
    @Size(min = 1, max = 50)
    @Email(message = "Email should be valid")
    private String email;
    @Size(min = 1, max = 40)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Field must contain only Latin letters")
    private String title;
}
