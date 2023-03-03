package com.empManagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empManagement.helper.ApiResponse;
import com.empManagement.model.Employee;
import com.empManagement.service.EmployeeService;

import io.swagger.annotations.Api;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
@Api(value = "/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// get all the employee
	@GetMapping("/Employees")
	public Page<Employee> getAllEmployee(@RequestParam(required = false, defaultValue = "0") int pageNumber,
			@RequestParam(required = false, defaultValue = "5") int pageSize) {
		return employeeService.getAllEmployee(pageNumber, pageSize);
	}

	@GetMapping("/getEmployees")
	List<Employee> getsAllEmployee() {
		return employeeService.getAllEmployees();
	}

	// Get the employee
	@GetMapping("/Employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
		return employeeService.getEmployee(id);

	}

	// add the employee
	@PostMapping("/Employees")
	public ResponseEntity<ApiResponse> addEmployees(@RequestBody Employee employee) {
		return employeeService.addEmployees(employee);
	}

	// update the employee
	@PutMapping("/Employees/{id}")
	public ResponseEntity<ApiResponse> updateEmploee(@PathVariable("id") Long id, @RequestBody Employee employeeDts) {
		return employeeService.updateEmploee(id, employeeDts);
	}

	// update the employee using procedure
	@PutMapping("/Employee/{id}")
	public void updateEmploees(@PathVariable("id") Long id, @RequestBody Employee employeeDts) {
		employeeService.updateEmploeeById(id, employeeDts);
	}

	// delete the employee
	@DeleteMapping("/Employees/{id}")
	public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable("id") Long id) {
		return employeeService.deleteEmployee(id);
	}

}
