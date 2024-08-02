package com.example.bootcamp.model;

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
@EqualsAndHashCode
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
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
    private String title;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "project",
            joinColumns = @JoinColumn(name = "projectId"),
            inverseJoinColumns = @JoinColumn(name = "employeeId")
    )
    private Set<Project> projects = new HashSet<>();

    public Employee() {
    }
}



