package com.employee.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "employee_project")
public class ProjectEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empProjectId")
    private Long empProjectId;

    @Column(name = "projectId")
    private Long projectId;

    @Column(name = "employeeName")
    private String employeeName;

    @Column(name = "assignedDate", nullable = false)
    private LocalDate assignedDate;

    @Column(name = "role", nullable = false)
    private String role;
}

