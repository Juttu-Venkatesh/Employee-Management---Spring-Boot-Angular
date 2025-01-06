package com.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {

	
}
