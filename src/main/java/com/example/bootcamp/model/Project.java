package com.example.bootcamp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "project")
@Builder
@AllArgsConstructor
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @Size(min = 1, max = 60)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Field must contain only Latin letters")
    private String name;
    @Column(name = "description")
    @Size(min = 1, max = 150)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Field must contain only Latin letters")
    private String description;

    public Project() {}
}
