package com.empManagement.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.empManagement.exception.ResourceNotFoundException;
import com.empManagement.helper.ApiResponse;
import com.empManagement.model.ApiInDetails;
import com.empManagement.model.Employee;
import com.empManagement.repository.EmployeeRepository;
import com.empManagement.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public ResponseEntity<Employee> getEmployee(Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee does not exits with id : " + id));
		return ResponseEntity.ok(employee);
	}

	@Override
	public ResponseEntity<ApiResponse> addEmployees(Employee employee) {
		ApiResponse response = new ApiResponse();
		String email = employee.getEmailId();
		if (employeeRepository.existsByEmailId(email)) {
			response.setStatus_code(400);
			response.setMessage("Employee Already Exists with this emailId: " + email);
			return ResponseEntity.ok(response);
		} else {

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy h:mm:ss a");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
			String formattedDate = sdf.format(date);
			String sdf11 = sdf1.format(date);

			ApiInDetails apDetails = new ApiInDetails();
			apDetails.setDateCreated(formattedDate);
			apDetails.setCreatedBy(employee.getFirstName());
			employee.setApiInDetails(apDetails);
			employee.setUserName(employee.getFirstName().toLowerCase() + "@" + sdf11);
			employeeRepository.save(employee);
			response.setStatus_code(200);
			response.setMessage("Employee added successfully");
			return ResponseEntity.ok(response);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> updateEmploee(Long id, Employee employeeDts) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee does not exits with id : " + id));
		ApiResponse response = new ApiResponse();
		String name = employeeDts.getFirstName();
		String email = employeeDts.getEmailId();

		String dateCreated = employee.getApiInDetails().getDateCreated();
		String createdBy = employee.getApiInDetails().getCreatedBy();

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy h:mm:ss a");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
		String formattedDate = sdf.format(date);
		String sdf11 = sdf1.format(date);

		ApiInDetails apDetails = new ApiInDetails();
		apDetails.setDateCreated(dateCreated);
		apDetails.setCreatedBy(createdBy);
		apDetails.setDateModified(formattedDate);
		apDetails.setModifiedBy(employeeDts.getFirstName());

		employee.setApiInDetails(apDetails);
		employee.setApiInDetails(employee.getApiInDetails());
		employee.setFirstName(employeeDts.getFirstName());
		employee.setLastName(employeeDts.getLastName());
		employee.setUserName(employeeDts.getFirstName().toLowerCase() + "@" + sdf11);
		this.employeeRepository.save(employee);

		response.setStatus_code(200);
		response.setMessage("Employee updated successfully");
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Map<String, String>> deleteEmployee(Long id) {

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee does not exits with id : " + id));

		employeeRepository.delete(employee);

		Map<String, String> response = new HashMap<>();
		response.put("Status code", "200");
		response.put("Deleted successfully", " ");

		return ResponseEntity.ok(response);
	}

	@Override
	public Page<Employee> getAllEmployee(int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		return employeeRepository.findAll(paging);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.getAllEmployees();
	}

	@Override
	public void updateEmploeeById(Long id, Employee employeeDts) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee does not exits with id : " + id));
		System.out.println(id+" "+employee.getFirstName()+" "+ employee.getLastName()+" "+ employee.getEmailId());
		this.employeeRepository.updateEmployee(id, employeeDts.getFirstName(), employeeDts.getLastName(), employeeDts.getEmailId());

	}

}
