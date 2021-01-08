package com.connection.emobile.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Track {

	@Id
	@GeneratedValue
	private int trackId;
	private String trackStatus;
	private int userId;
	private int planId;
	private int adminId;
	private Long mobileNumber;

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getOrderId() {
		return trackId;
	}

	public void setOrderId(int trackId) {
		this.trackId = trackId;
	}

	public String getStatus() {
		return trackStatus;
	}

	public void setStatus(String trackStatus) {
		this.trackStatus = trackStatus;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
