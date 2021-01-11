package com.connection.emobile.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ApproveResponseDto {
private String message;
private String statusCode;
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getStatusCode() {
	return statusCode;
}
public void setStatusCode(String statusCode) {
	this.statusCode = statusCode;
}
public ApproveResponseDto() {
	
}
}
