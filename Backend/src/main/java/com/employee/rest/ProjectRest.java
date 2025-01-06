package com.employee.rest;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.employee.entity.Project;

@RequestMapping("/EmployeeManagement")
@CrossOrigin(origins = "http://localhost:4200")
public interface ProjectRest {

	@PostMapping("/CreateProject")
	public ResponseEntity<Project> createProject(@RequestBody Project project);
	
	@GetMapping("/GetAllProjects")
	ResponseEntity <List<Project>> getAllProjects();
	
	@GetMapping("/GetProject/{projectId}")
	public ResponseEntity<Project> getProjectById(@PathVariable Long projectId);
	
	@PutMapping("/UpdateProject/{projectId}")
	public ResponseEntity<Project> updateProject(@PathVariable Long projectId, @RequestBody
			                            Project updatedProject);
	
	@DeleteMapping("/DeleteProject/{projectId}")
	public ResponseEntity<String> deleteProject(@PathVariable Long projectId);
	
	@GetMapping("/ProductCount")
	public long getProductCount();
	
}
