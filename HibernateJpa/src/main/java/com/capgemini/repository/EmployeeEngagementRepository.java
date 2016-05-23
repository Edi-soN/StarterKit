package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.EmployeeEngagement;

@Transactional(readOnly = true)
public interface EmployeeEngagementRepository extends JpaRepository<EmployeeEngagement, Long> {

	@Query("select ee from EmployeeEngagement ee where ee.id like :id")
	List<EmployeeEngagement> findEmployeeEngagementById(@Param("id") Integer id);

	@Query("select ee from EmployeeEngagement ee join fetch ee.employee e join fetch ee.project p join fetch ee.role r"
			+ " where e.pesel like :employee and p.name like :project and r.name like :role")
	List<EmployeeEngagement> findEmployeeEngagementByEmployeeProjectRole(@Param("employee") String employee,
			@Param("project") String project, @Param("role") String role);

}
