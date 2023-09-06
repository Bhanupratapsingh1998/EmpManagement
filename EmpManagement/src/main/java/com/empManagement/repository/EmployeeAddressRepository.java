package com.empManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.empManagement.model.EmployeeAddress;

@Repository
public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, Long> {
	
	List<EmployeeAddress> getCityByEmployeeId(Long employeeId);
	
    @Query("SELECT a.city FROM EmployeeAddress a WHERE a.employee.id = :employeeId")
	List<String> getCityByEmployeeId1(@Param("employeeId") Long employeeId);


}
