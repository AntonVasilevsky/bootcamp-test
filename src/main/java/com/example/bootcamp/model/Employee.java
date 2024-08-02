package com.example.bootcamp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "employee")
@Builder
@ToString
@AllArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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

    public Employee() {
    }
}



