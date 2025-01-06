package com.employee.entity;



import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "employee")
public class Employee {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empid", nullable = false)
    private Long employeeId;

    @Column(name = "firstname", nullable = false)
    private String firstName;
    
    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "contactno", nullable = false)
    private String contactNo;

    @Column(name = "emailid", nullable = false)
    private String emailId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "gender", nullable = false)
    private String gender;


    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "role", nullable = false)
    private String role;

    
}
