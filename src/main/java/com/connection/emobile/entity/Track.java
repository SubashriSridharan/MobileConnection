package com.connection.emobile.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Track {

	@Id
	@GeneratedValue
	private int trackId;
	private String status;
	private int userId;
	private int planId;
	private int adminId;
	private String approverComments;
	  public String getApproverComments() { return approverComments; }
	  
	  public void setApproverComments(String approverComments) {
	  this.approverComments = approverComments; }
	  
	  public int getPlanId() { return planId; }
	  
	  public void setPlanId(int planId) { this.planId = planId; }
	  
	  public int getAdminId() { return adminId; }
	  
	  public void setAdminId(int adminId) { this.adminId = adminId; }
	  
	  public int getUserId() { return userId; }
	  
	  public void setUserId(int userId) { this.userId = userId; }
	  
	  public int getTrackId() { return trackId; }
	  
	  public void setTrackId(int trackId) { this.trackId = trackId; }
	  
	  public String getStatus() { return status; }
	  
	  public void setStatus(String status) { this.status = status; }
	 
}
