package com.connection.emobile.dto;


	import lombok.Getter;
	import lombok.NoArgsConstructor;
	import lombok.Setter;

	@Getter
	@Setter
	@NoArgsConstructor
	public class OrderTrackingResponseDto {
		
		private String orderStatus;
		public String getOrderStatus() {
			return orderStatus;
		}
		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
		}
		public String getAdminComments() {
			return adminComments;
		}
		public void setAdminComments(String adminComments) {
			this.adminComments = adminComments;
		}
		private String adminComments;
		
		
		
	}

