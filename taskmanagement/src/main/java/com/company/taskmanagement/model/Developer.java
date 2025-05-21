package com.company.taskmanagement.model;

import jakarta.persistence.*;
import lombok.*;
import com.company.taskmanagement.model.enums.Seniority;


@Entity
@Table(name = "developers", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Seniority seniority;


}