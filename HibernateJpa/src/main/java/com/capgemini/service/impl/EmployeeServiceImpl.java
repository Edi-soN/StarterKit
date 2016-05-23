package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.Employee;
import com.capgemini.repository.EmployeeRepository;
import com.capgemini.service.EmployeeService;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired(required = true)
	private EmployeeRepository employeeRepository;

	public Employee createEmployee(Employee employee) {
		employee.setId(null);
		return employeeRepository.save(employee);
	}

	public Employee updateEmployee(Employee employee) {
		if(employee.getId().equals(null)){
			return null;
		}
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> findEmployeeById(Integer id) {
		return employeeRepository.findEmployeeById(id);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
	}
}
