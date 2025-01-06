package com.employee.service;

import java.util.List;
import java.util.Optional;

import com.employee.entity.ProjectEmployee;

public interface ProjectEmployeeService {

	ProjectEmployee createEmployeeProject(ProjectEmployee employeeProject);

	List<ProjectEmployee> getAllEmployeeProjects();

	Optional<ProjectEmployee> getEmployeeProjectById(Long empProjectId);

	ProjectEmployee updateEmployeeProject(Long empProjectId, ProjectEmployee updatedEmployeeProject);

	boolean deleteEmployeeProject(Long empProjectId);

	long getAssinmentCount();

}
