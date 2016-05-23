package com.capgemini.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Employee;
import com.capgemini.model.EmployeeEngagement;
import com.capgemini.model.Project;
import com.capgemini.model.Role;
import com.capgemini.repository.EmployeeEngagementRepository;
import com.capgemini.repository.ProjectRepository;
import com.capgemini.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	EmployeeEngagementRepository employeeEngagementRepository;

	@Override
	public Project createProject(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public Project findProjectById(Integer id) {
		return projectRepository.findProjectById(id);
	}

	public void deleteProject(Project project) {
		projectRepository.delete(project);
	}

	@Override
	public EmployeeEngagement addEmployeeToProject(EmployeeEngagement employeeEngagement) {
		return employeeEngagementRepository.save(employeeEngagement);
	}

	@Override
	public EmployeeEngagement removeEmployeeFromProject(EmployeeEngagement employeeEngagement) {
		employeeEngagement.setEndDate(new Date());
		return employeeEngagementRepository.save(employeeEngagement);
	}

	public List<EmployeeEngagement> findEmployeeEngagementById(Integer id) {
		return findEmployeeEngagementById(id);
	}

	public List<EmployeeEngagement> findEmployeeEngagementByEmployeeProjectRole(Employee employee, Project project,
			Role role) {
		return employeeEngagementRepository.findEmployeeEngagementByEmployeeProjectRole(employee.getPesel(),
				project.getName(), role.getName());
	}

	@Override
	public void deleteEmployeeEngagement(EmployeeEngagement employeeEngagement) {
		employeeEngagementRepository.delete(employeeEngagement);
	}
	
	@Override
	public void saveEmployeeEngagement(EmployeeEngagement employeeEngagement) {
		employeeEngagementRepository.save(employeeEngagement);
	}

}
