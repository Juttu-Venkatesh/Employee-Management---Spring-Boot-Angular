package com.employee.restImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Project;
import com.employee.rest.ProjectRest;
import com.employee.service.ProjectService;

@RestController
public class ProjectRestImpl implements ProjectRest{

	@Autowired
	ProjectService  projectService;

	@Override
	public ResponseEntity<Project> createProject(Project project) {
		try {
			Project createdProject = projectService.createProject(project);
	        return ResponseEntity.ok(createdProject);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Project>> getAllProjects() {
		try {
			List<Project> projects = projectService.getAllProjects();
	        return ResponseEntity.ok(projects);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Project> getProjectById(Long projectId) {
		 Optional<Project> project = projectService.getProjectById(projectId);
	        return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<Project> updateProject(Long projectId, Project updatedProject) {
		 Project project = projectService.updateProject(projectId, updatedProject);
	     return project != null ? ResponseEntity.ok(project) : ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<String> deleteProject(Long projectId) {
		 boolean isDeleted = projectService.deleteProject(projectId);
	        return isDeleted ? ResponseEntity.ok("Project deleted successfully") : ResponseEntity.notFound().build();
	}

	@Override
	public long getProductCount() {
		return projectService.getProductCount();
	}

	
}
