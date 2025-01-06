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

import com.employee.entity.ProjectEmployee;

@RequestMapping("/EmployeeManagement")
@CrossOrigin(origins = "http://localhost:4200")
public interface ProjectEmployeeRest {
	
	@PostMapping("/CreateEmployeeProject")
	ResponseEntity<String> createEmployeeProject(@RequestBody ProjectEmployee employeeProject);
	
	@GetMapping("/GetAllEmployeeProjects")
	ResponseEntity<List<ProjectEmployee>> getAllEmployeeProjects();
	
	@GetMapping("/GetEmployeeProjectById/{empProjectId}")
    ResponseEntity<ProjectEmployee> getEmployeeProjectById(@PathVariable Long empProjectId);
	
	@PutMapping("/UpdateAssignment/{empProjectId}")
    ResponseEntity<ProjectEmployee> updateEmployeeProject(@PathVariable Long empProjectId, 
    		                               @RequestBody ProjectEmployee updatedEmployeeProject);
	
	@DeleteMapping("/DeleteAssignment/{empProjectId}")
    ResponseEntity<String> deleteEmployeeProject(@PathVariable Long empProjectId);
	
	@GetMapping("AssignmentCount")
	public long getAssinmentCount();

}
