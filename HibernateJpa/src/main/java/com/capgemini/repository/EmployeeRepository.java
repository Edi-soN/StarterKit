package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.Employee;

@Transactional(readOnly = true)
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// @Modifying(clearAutomatically = true)
	// @Transactional(readOnly = false)
	// @Query("update Employee e set e.name = :name, e.surname = :surname,
	// e.pesel = :pesel, e.dateOfBirth = :dateOfBirth where e.id = :id")
	// void updateEmployeeData(@Param("name") String name, @Param("surname")
	// String surname, @Param("pesel") String pesel,
	// @Param("dateOfBirth") Date dateOfBirth, @Param("id") Integer id);

	@Query("select e from Employee e where e.name like :name")
	List<Employee> findEmployeeByName(@Param("name") String name);

	@Query("select e from Employee e where e.id like :id")
	List<Employee> findEmployeeById(@Param("id") Integer id);

	@Query("select e from Employee e where e.pesel like :pesel")
	List<Employee> findEmployeeByPesel(@Param("pesel") String pesel);

}
