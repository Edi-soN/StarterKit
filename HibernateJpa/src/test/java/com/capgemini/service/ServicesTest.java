package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.model.Department;
import com.capgemini.model.Employee;
import com.capgemini.model.EmployeeEngagement;
import com.capgemini.model.Project;
import com.capgemini.model.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "ServicesTest-context.xml")

public class ServicesTest {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ProjectService projectService;

	@Test
	//@Ignore
	public void testShouldFindEmployee() {
		// given
		List<Employee> empList = employeeService.findEmployeeById(1);
		// when
		int numberOfEmp = empList.size();
		// then
		assertEquals(1, numberOfEmp);
	}

	@Test
	//@Ignore
	public void testShouldUpdateEmployee() {
		// given
		Date date = new Date();
		String pesel = "00000000001";
		Department department = new Department(1, "Department1");
		Employee employee = new Employee(1, "Jan", "Kowalski1", pesel, date, department);
		// when
		String employeeSurname = employeeService.findEmployeeById(employeeService.updateEmployee(employee).getId())
				.get(0).getSurname();
		// then
		assertEquals("Kowalski1", employeeSurname);
		// clean up
		employee.setSurname("Kowalski");
		employeeService.updateEmployee(employee);
	}

	@Test
	//@Ignore
	public void testShouldInsertEmployee() {
		// given
		Date date = new Date();
		String pesel = "10000000000";
		Employee employee = new Employee(null, "Roman", "Polansky", pesel, date, null);
		// when
		boolean isEmployeeAdded = employeeService.findEmployeeById(employeeService.createEmployee(employee).getId())
				.isEmpty();
		// then
		assertFalse(isEmployeeAdded);
		employeeService.deleteEmployee(employee);
	}

	// ----------------------------------------------------------------------------------

	@Test
	//@Ignore
	public void testShouldCreateProject() {
		// given
		Project project = new Project(null, "ProjectTest", "inner", null);
		// when
		Project addedProject = projectService.createProject(project);
		String addedProjectName = projectService.findProjectById(addedProject.getId()).getName();
		// then
		assertEquals("ProjectTest", addedProjectName);
		// clean up
		projectService.deleteProject(project);
	}

	@Test
	//@Ignore
	public void shouldFindEmployeeEngagementByMany() {
		// given
		Date date = new Date();
		String pesel = "00000000001";
		Department department = new Department(1, "Department1");
		Employee employee = new Employee(1, "Jan", "Kowalski", pesel, date, department);
		Project project = new Project(1, "Project1", "inner", employee);
		Role role = new Role(1, "DEV");
		// when
		boolean isEmpty = projectService.findEmployeeEngagementByEmployeeProjectRole(employee, project, role).isEmpty();
		// then
		assertFalse(isEmpty);
	}

	@Test
	//@Ignore
	public void shouldAddEmployeeToProject() {
		// given
		Date date = new Date();
		String pesel = "00000000001";
		Department department = new Department(1, "Department1");
		Employee employee = new Employee(1, "Jan", "Kowalski", pesel, date, department);
		Project project = new Project(1, "Project1", "inner", employee);
		Role role = new Role(1, "DEV");
		EmployeeEngagement employeeEngagement = new EmployeeEngagement(null, employee, project, role, new Date(), null,
				null);
		// when
		projectService.addEmployeeToProject(employeeEngagement);
		boolean isEmpty = projectService.findEmployeeEngagementByEmployeeProjectRole(employee, project, role).isEmpty();
		// then
		assertFalse(isEmpty);
		// clean up
		// projectService.deleteEmployeeEngagement(addedEmployeeEngagement);
	}

	@Test
	//@Ignore
	public void shouldRemoveEmployeeFromProject() {
		// given
		Date date = new Date();
		String pesel = "00000000001";
		Department department = new Department(1, "Department1");
		Employee employee = new Employee(1, "Jan", "Kowalski", pesel, date, department);
		Employee manager = new Employee(4, "Adam", "Kowalski", "00000000004", date, new Department(4, "Department4"));
		Project project = new Project(1, "Project4", "outer", manager);
		Role role = new Role(2, "PL");
		EmployeeEngagement employeeEngagement = projectService
				.findEmployeeEngagementByEmployeeProjectRole(employee, project, role).get(0);
		// when
		EmployeeEngagement removedEmployeeEngagement = projectService.removeEmployeeFromProject(employeeEngagement);
		boolean isNotNull = projectService.findEmployeeEngagementByEmployeeProjectRole(employee, project, role).get(0)
				.getEndDate() == null;
		// then
		assertFalse(isNotNull);
		// clean up
		removedEmployeeEngagement.setEndDate(null);
		projectService.saveEmployeeEngagement(removedEmployeeEngagement);
	}

}
