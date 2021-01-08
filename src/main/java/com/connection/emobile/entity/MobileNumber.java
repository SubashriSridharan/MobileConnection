package com.connection.emobile.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Mobile_Number")
public class MobileNumber {

	@Id
	@Column(name = "mobile_number")
	@NotNull(message = "Phone Number must not be null")
	@NotBlank(message = "Phone Number is mandatory")
	@Pattern(regexp="(^$|[0-9]{10})", message = "Please provide the valid phone number")
	private Long mobileNumber;

	@Column(name = "mobile_number_status")
	private String status;

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
