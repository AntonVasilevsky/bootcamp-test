package com.example.bootcamp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    @Size(min = 1, max = 60)
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Field must contain only Latin letters")
    @NotBlank
    private String surname;
    @Size(min = 1, max = 20)
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Field must contain only Latin letters")
    @NotBlank
    private String name;
    @Size(min = 1, max = 40)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Field must contain only Latin letters")
    @NotBlank
    private String patronymic;
    @Size(min = 1, max = 50)
    @Email(message = "Email should be valid")
    @NotBlank
    private String email;
    @Size(min = 1, max = 40)
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Field must contain only Latin letters")
    @NotBlank
    private String title;
}
