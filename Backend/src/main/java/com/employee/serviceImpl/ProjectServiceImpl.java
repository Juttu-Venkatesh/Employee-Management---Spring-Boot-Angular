package com.employee.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Project;
import com.employee.repo.ProjectRepo;
import com.employee.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectRepo projectRepo;

	@Override
	public Project createProject(Project project) {
		return projectRepo.save(project);
	}

	@Override
	public List<Project> getAllProjects() {
		return projectRepo.findAll();
	}

	@Override
	public Optional<Project> getProjectById(Long projectId) {
		 return projectRepo.findById(projectId);
	}

	@Override
	public Project updateProject(Long projectId, Project updatedProject) {
		 if (projectRepo.existsById(projectId)) {
	            updatedProject.setProjectId(projectId);
	            return projectRepo.save(updatedProject);
	        }
	        return null; 
	}

	@Override
	public boolean deleteProject(Long projectId) {
		 if (projectRepo.existsById(projectId)) {
	            projectRepo.deleteById(projectId);
	            return true;
	        }
	        return false;
	}

	@Override
	public long getProductCount() {
		return projectRepo.count();
	}

	
	
//	@Override
//	public ResponseDTO createProject(Project project) {
//		try {
//			Project createProject = projectRepo.save(project);
//			return new ResponseDTO(
//			"Project Created Success",
//			true,
//			createProject
//			);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return new ResponseDTO (
//			"Error create project",
//			false,
//			null
//		   );
//		}
//	}

}
