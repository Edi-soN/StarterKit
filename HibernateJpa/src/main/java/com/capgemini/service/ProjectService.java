package com.capgemini.service;

import java.util.List;

import com.capgemini.model.Employee;
import com.capgemini.model.EmployeeEngagement;
import com.capgemini.model.Project;
import com.capgemini.model.Role;

public interface ProjectService {

	public Project createProject(Project project);
	
	public Project findProjectById(Integer id);
	
	public void deleteProject(Project project);

	public EmployeeEngagement addEmployeeToProject(EmployeeEngagement employeeEngagement);

	public EmployeeEngagement removeEmployeeFromProject(EmployeeEngagement employeeEngagement);
	
	public List<EmployeeEngagement> findEmployeeEngagementById(Integer id);
	
	public List<EmployeeEngagement> findEmployeeEngagementByEmployeeProjectRole(Employee employee, Project project, Role role);
	
	public void deleteEmployeeEngagement(EmployeeEngagement employeeEngagement);
	
	public void saveEmployeeEngagement(EmployeeEngagement employeeEngagement);
}
