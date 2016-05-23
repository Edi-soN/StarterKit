package com.capgemini.service;

import java.util.List;

import com.capgemini.model.Employee;

public interface EmployeeService {

	public Employee createEmployee(Employee employee);

	public Employee updateEmployee(Employee employee);
	
	public void deleteEmployee(Employee employee);
	
	public List<Employee> findEmployeeById(Integer id);
	
}
