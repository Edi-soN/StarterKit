package com.capgemini.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the employee_engagement database table.
 * 
 */
@Entity
@Table(name = "employee_engagement")
@NamedQuery(name = "EmployeeEngagement.findAll", query = "SELECT e FROM EmployeeEngagement e")
public class EmployeeEngagement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;

	private BigDecimal salary;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	// uni-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name = "id_employee")
	private Employee employee;

	// uni-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name = "id_project")
	private Project project;

	// uni-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name = "id_role")
	private Role role;

	public EmployeeEngagement(Integer id, Employee employee, Project project, Role role, Date startDate, Date endDate,
			BigDecimal salary) {
		this.id = id;
		this.employee = employee;
		this.project = project;
		this.role = role;
		this.startDate = startDate;
		this.endDate = endDate;
		this.salary = salary;
	}

	public EmployeeEngagement() {

	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}