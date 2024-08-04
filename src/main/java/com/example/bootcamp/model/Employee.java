package com.example.bootcamp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
@Builder
@ToString
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int employeeId;
    @Column(name = "surname")
    @Size(min = 1, max = 60)
    @Pattern(
            regexp = "^[a-zA-Z\\s]+$",
            message = "Field must contain only Latin letters"
    )
    private String surname;
    @Column(name = "name")
    @Size(min = 1, max = 20)
    @Pattern(
            regexp = "^[a-zA-Z\\s]+$",
            message = "Field must contain only Latin letters"
    )
    private String name;
    @Column(name = "patronymic")
    @Size(min = 1, max = 40)
    @Pattern(
            regexp = "^[a-zA-Z\\s]+$",
            message = "Field must contain only Latin letters"
    )
    private String patronymic;
    @Column(name = "email", unique = true)
    @Size(min = 1, max = 50)
    @Email(message = "Email should be valid")
    private String email;
    @Column(name = "title")
    @Size(min = 1, max = 40)
    @Pattern(
            regexp = "^[a-zA-Z\\s]+$",
            message = "Field must contain only Latin letters"
    )
    private String title;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    private Set<Project> projects = new HashSet<>();

    public Employee() {
    }
}



