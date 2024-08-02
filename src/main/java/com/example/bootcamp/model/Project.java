package com.example.bootcamp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project")
@Builder
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;
    @Column(name = "name")
    @Size(min = 1, max = 60)
    @Pattern(
            regexp = "^[a-zA-Z\\s]+$",
            message = "Field must contain only Latin letters"
    )
    private String name;
    @Column(name = "description")
    @Size(min = 1, max = 150)
    @Pattern(
            regexp = "^[a-zA-Z\\s]+$",
            message = "Field must contain only Latin letters"
    )
    private String description;
    @ManyToMany(
            mappedBy = "projects",
            fetch = FetchType.EAGER
    )
    private Set<Employee> employees = new HashSet<>();
    public Project() {}
}
