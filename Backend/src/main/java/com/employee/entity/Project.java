package com.employee.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "projects")
public class Project {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "projectId")
    private Long projectId;

    @Column(name = "projectName", nullable = false)
    private String projectName;
    
    @Column(name = "clientName", nullable = false)
    private String clientName;
    
    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;
    
    @Column(name = "contactPerson", nullable = false)
    private String contactPerson;
    
    @Column(name = "contactNo", nullable = false)
    private String contactNo;
    
    @Column(name = "emailId", nullable = false)
    private String emailId;

    @Column(name = "leadByEmployee", nullable = false)
    private String leadByEmployee;

}
