package com.empManagement.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Component
@JsonInclude(value = Include.NON_NULL)
 public class EmployeeDto {
 
	private long id;
 
	private String firstName;
 
	private String lastName;
 
	private String emailId;
	 
	private String password;
	 	
	private String userName;
	
	private String image;
	 
	private String dateCreated;
 
	private String dateModified;
 
	private String createdBy;
	
	private String modifiedBy;
}