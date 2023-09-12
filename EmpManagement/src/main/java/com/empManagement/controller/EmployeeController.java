package com.empManagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
import com.empManagement.model.EmployeeAddress;
import com.empManagement.service.EmployeeService;

import io.swagger.annotations.Api;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
@Api(value = "/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private AuthenticationManager authenticationManager;

	// get all the employee
	@GetMapping("/listEmployees")
	public Page<Employee> getAllEmployee(@RequestParam(required = false, defaultValue = "0") int pageNumber,
			@RequestParam(required = false, defaultValue = "5") int pageSize) {
		return employeeService.getAllEmployee(pageNumber, pageSize);
	}

    // get all the employee using pagination
	@GetMapping("/getEmployeeByPage")
	public Page<Employee> getEmployeeByPage(Pageable pageable) { 
		Employee employee = new Employee();
		employee.setFirstName("Sanchit");
		pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.desc("firstName")));
		return employeeService.getEmployeeByPage(pageable);
	}
	@GetMapping("/getEmployees")
	List<Employee> getsAllEmployee() {
		return employeeService.getAllEmployees();
	}

	// login employees
	@PostMapping("/signin")
	public ResponseEntity<?> loginEmployee(@RequestBody Employee employee) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(employee.getEmailId().trim(), employee.getPassword().trim()));
		return employeeService.loginEmployee(employee, authentication);
	}

	// Get the employee
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
		return employeeService.getEmployee(id);
	}

	// Get Employee city
	@GetMapping("/{employeeId}/cities")
	public ResponseEntity<List<String>> getCitiesByEmployeeId(@PathVariable Long employeeId) {
		List<String> cities = employeeService.getCitiesByEmployeeId(employeeId);
		return ResponseEntity.ok(cities);
	}

	// add the employee
	@PostMapping("/addEmployee")
	public ResponseEntity<ApiResponse> addEmployees(@RequestBody Employee employee) {
		return employeeService.addEmployees(employee);
	}

	// update the employee
	@PutMapping("/Employees/{id}")
	public ResponseEntity<ApiResponse> updateEmploee(@PathVariable("id") Long id, @RequestBody Employee employeeDts) {
		return employeeService.updateEmploee(id, employeeDts);
	}

	// delete the employee
	@DeleteMapping("/Employees/{id}")
	public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable("id") Long id) {
		return employeeService.deleteEmployee(id);
	}

}
