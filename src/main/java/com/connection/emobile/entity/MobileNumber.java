package com.connection.emobile.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MobileNumber {

	@Id
	private int mobileNumber;
	private String status;
	private int userId;

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
