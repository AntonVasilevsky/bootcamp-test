package com.example.bootcamp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeDto {
    @Column(name = "surname")
    @Size(min = 1, max = 60)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Field must contain only Latin letters")
    private String surname;
    @Column(name = "name")
    @Size(min = 1, max = 20)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Field must contain only Latin letters")
    private String name;
    @Column(name = "patronymic")
    @Size(min = 1, max = 40)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Field must contain only Latin letters")
    private String patronymic;
    @Column(name = "email")
    @Size(min = 1, max = 50)
    @Email(message = "Email should be valid")
    private String email;
    @Column(name = "title")
    @Size(min = 1, max = 40)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Field must contain only Latin letters")
    private String title;
}
