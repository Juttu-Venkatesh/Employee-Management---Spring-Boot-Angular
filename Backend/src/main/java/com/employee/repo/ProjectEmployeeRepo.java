package com.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.entity.ProjectEmployee;

public interface ProjectEmployeeRepo extends JpaRepository<ProjectEmployee, Long>{

}
