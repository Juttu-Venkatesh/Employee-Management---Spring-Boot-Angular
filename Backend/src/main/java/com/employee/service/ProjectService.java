package com.employee.service;


import java.util.List;
import java.util.Optional;

import com.employee.entity.Project;

public interface ProjectService {

	Project createProject(Project project);

	List<Project> getAllProjects();

	Optional<Project> getProjectById(Long projectId);

	Project updateProject(Long projectId, Project updatedProject);

	boolean deleteProject(Long projectId);

	long getProductCount();
}
