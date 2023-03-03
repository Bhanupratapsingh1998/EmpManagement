package com.empManagement.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "EMPLOYEE")
//@NamedStoredProcedureQueries({
//    @NamedStoredProcedureQuery(name = "getAllEmployees",
//                                procedureName = "get_all_employees")
//})
public class Employee implements Serializable {
	private static final long serialVersionUID = 1772757159185494620L;

	@Id
	@Column(name = "EMPLOYEE_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "email_id", nullable = false)
	private String emailId;
	
	@Column(name = "User_name", nullable = false)
	private String userName;

	@Embedded
	private ApiInDetails apiInDetails = new ApiInDetails();

}
