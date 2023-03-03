package com.empManagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.empManagement.helper.ApiResponse;
import com.empManagement.model.Employee;

public interface EmployeeService {

	ResponseEntity<Employee> getEmployee(Long id);

	ResponseEntity<ApiResponse> addEmployees(Employee employee);

	ResponseEntity<Map<String, String>> deleteEmployee(Long id);

	Page<Employee> getAllEmployee(int page, int size);

	List<Employee>getAllEmployees();

	ResponseEntity<ApiResponse> updateEmploee(Long id, Employee employeeDts);

	void updateEmploeeById(Long id, Employee employeeDts);

}
