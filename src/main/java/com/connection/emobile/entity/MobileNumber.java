package com.connection.emobile.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MobileNumber {

	@Id
	private Long mobileNumber;
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
