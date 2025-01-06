package com.employee.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.ProjectEmployee;
import com.employee.repo.ProjectEmployeeRepo;
import com.employee.service.ProjectEmployeeService;

@Service
public class ProjectEmployeeServiceImpl implements ProjectEmployeeService{

	@Autowired
	ProjectEmployeeRepo projectEmployeeRepo;
	
	@Override
	public ProjectEmployee createEmployeeProject(ProjectEmployee employeeProject) {
		return projectEmployeeRepo.save(employeeProject);
	}

	@Override
	public List<ProjectEmployee> getAllEmployeeProjects() {
		 return projectEmployeeRepo.findAll();
	}

	@Override
	public Optional<ProjectEmployee> getEmployeeProjectById(Long empProjectId) {
		 return projectEmployeeRepo.findById(empProjectId);
	}

	@Override
	public ProjectEmployee updateEmployeeProject(Long empProjectId, ProjectEmployee updatedEmployeeProject) {
		if (projectEmployeeRepo.existsById(empProjectId)) {
            updatedEmployeeProject.setEmpProjectId(empProjectId);
            return projectEmployeeRepo.save(updatedEmployeeProject);
        }
        return null;
	}

	@Override
	public boolean deleteEmployeeProject(Long empProjectId) {
		 if (projectEmployeeRepo.existsById(empProjectId)) {
			 projectEmployeeRepo.deleteById(empProjectId);
	            return true;
	        }
	        return false;
	    }

	@Override
	public long getAssinmentCount() {
		return projectEmployeeRepo.count();
	}
}


