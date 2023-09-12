package com.empManagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.empManagement.helper.ApiResponse;
import com.empManagement.model.Employee;
import com.empManagement.model.Role;

public interface EmployeeService {

	ResponseEntity<Employee> getEmployee(Long id);

	ResponseEntity<ApiResponse> addEmployees(Employee employee);

	ResponseEntity<Map<String, String>> deleteEmployee(Long id);

	Page<Employee> getAllEmployee(int page, int size);

	List<Employee>getAllEmployees();

	ResponseEntity<ApiResponse> updateEmploee(Long id, Employee employeeDts);

	ResponseEntity<?> loginEmployee(Employee employee,Authentication authentication);

	List<String> getCitiesByEmployeeId(Long employeeId);

	ResponseEntity<ApiResponse> addRole(String email, Role role);

    Page<Employee> getEmployeeByPage(Pageable pageable);

}
