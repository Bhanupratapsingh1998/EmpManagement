package com.empManagement.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ApiInDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "CREATED_DATE")
	private String dateCreated;

	@Column(name = "MODIFIED_DATE")
	private String dateModified;

	@Column(name = "CREATED_BY", length = 60)
	private String createdBy;


	@Column(name = "MODIFIED_BY", length = 60)
	private String modifiedBy;
//	  public void setModifiedBy(String modifiedBy) {
//		  if(!StringUtils.isBlank(modifiedBy)) {//TODO
//			  if(modifiedBy.length()>20) {
//				  modifiedBy = modifiedBy.substring(0, 20);
//			  }
//		  }
//	    this.modifiedBy = modifiedBy;
//	  }
}
