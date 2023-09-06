package com.empManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empManagement.helper.ApiResponse;
import com.empManagement.model.Role;
import com.empManagement.service.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class RoleController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/addRole")
	public ResponseEntity<ApiResponse> addRole(@RequestParam(value = "email") String email,
			@RequestBody Role role) {
		return employeeService.addRole(email, role);
	}
}
