package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.Project;

@Transactional(readOnly = true)
public interface ProjectRepository extends JpaRepository<Project, Long> {

	@Query("select p from Project p where p.id like :id")
	Project findProjectById(@Param("id") Integer id);

}
