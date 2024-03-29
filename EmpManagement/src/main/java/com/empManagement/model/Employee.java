package com.empManagement.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "User_name", nullable = false)
	private String userName;

	private String image;

	@JsonManagedReference
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeAddress> addresses;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "employee_role", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@Embedded
	private ApiInDetails apiInDetails = new ApiInDetails();

}
