package com.employee.restImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.ProjectEmployee;
import com.employee.rest.ProjectEmployeeRest;
import com.employee.service.ProjectEmployeeService;

@RestController
public class ProjectEmployeeImpl implements ProjectEmployeeRest{
	
	@Autowired
	ProjectEmployeeService projectEmployeeService;

	@Override
	public ResponseEntity<String> createEmployeeProject(ProjectEmployee employeeProject) {
		try {
			projectEmployeeService.createEmployeeProject(employeeProject);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<ProjectEmployee>> getAllEmployeeProjects() {
		 List<ProjectEmployee> employeeProjects = projectEmployeeService.getAllEmployeeProjects();
	        return ResponseEntity.ok(employeeProjects);
	}

	@Override
	public ResponseEntity<ProjectEmployee> getEmployeeProjectById(Long empProjectId) {
		Optional<ProjectEmployee> employeeProject = projectEmployeeService.getEmployeeProjectById(empProjectId);
        return employeeProject.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<ProjectEmployee> updateEmployeeProject(Long empProjectId,
			                                            ProjectEmployee updatedEmployeeProject) {
		ProjectEmployee employeeProject = projectEmployeeService.updateEmployeeProject(empProjectId, updatedEmployeeProject);
        return employeeProject != null ? ResponseEntity.ok(employeeProject) : ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<String> deleteEmployeeProject(Long empProjectId) {
		 boolean isDeleted = projectEmployeeService.deleteEmployeeProject(empProjectId);
	        return isDeleted ? ResponseEntity.ok("EmployeeProject deleted successfully") : ResponseEntity.notFound().build();
	}

	@Override
	public long getAssinmentCount() {
		return projectEmployeeService.getAssinmentCount();
	}

}
