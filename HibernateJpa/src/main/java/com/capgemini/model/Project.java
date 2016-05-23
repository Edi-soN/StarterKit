package com.capgemini.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the project database table.
 * 
 */
@Entity
@NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	@Column(name = "project_type")
	private String projectType;

	// uni-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name = "id_manager")
	private Employee employee;

	public Project() {
	}

	public Project(Integer id, String name, String projectType, Employee employee) {
		this.id = id;
		this.name = name;
		this.projectType = projectType;
		this.employee = employee;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectType() {
		return this.projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}