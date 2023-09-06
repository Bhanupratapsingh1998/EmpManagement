package com.empManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.empManagement.model.Employee;
import com.empManagement.model.EmployeeAddress;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	boolean existsByEmailId(String emailId);

	@Query(value = "call get_all_employees();", nativeQuery = true) // get all employees using procedure
//	@Query(value = "SELECT * FROM employee p", nativeQuery = true)
	List<Employee> getAllEmployees();

	boolean existsByFirstNameAndEmailId(String firstName, String emailId);

	@Query(value = "call get_employee_by_id(?1);", nativeQuery = true)
	Optional<Employee> getEmployee(Long id);

//	@Transactional
//	@Modifying
//	@Query(value = "call upadte_employee_by_id(:id, :firstName, :lastName, :emailId);", nativeQuery = true)
//	void updateEmployee(@Param("id")Long id,
//			@Param("firstName")String firstName,
//			@Param("lastName")String lastName,
//			@Param("emailId")String emailId);

	@Transactional
	@Modifying
	@Query(value = "call upadte_employee_by_id(?1,?2, ?3, ?4);", nativeQuery = true)
	void updateEmployee(Long EMPLOYEE_ID, String firstName, String lastName, String emailId);

	// find employee by emailId
	Employee findByEmailId(String emailId);
}
