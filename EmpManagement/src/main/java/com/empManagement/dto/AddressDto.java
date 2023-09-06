package com.empManagement.dto;

import lombok.Data;

@Data
public class AddressDto {
	private String streetAddress;

	private String city;

	private String state;

	private String postalCode;

	private String country;

	private String landmark;

	private String phoneNumber;
}
