package com.emobileconnection.mobileconnection;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.connection.emobile.controller.OrderTrackingController;
import com.connection.emobile.dto.OrderTrackingResponseDto;
import com.connection.emobile.exception.InvalidTrackIdException;
import com.connection.emobile.service.OrderTrackingService;


@RunWith(MockitoJUnitRunner.class)
public class OrderTrackingControllerTest {
	@Mock
	OrderTrackingService orderTrackingService;

	@InjectMocks
	OrderTrackingController orderTrackingController;

	Integer trackId = 1;
	OrderTrackingResponseDto orderTrackingResponseDto = new OrderTrackingResponseDto();

	@Before
	public void setup() {
		orderTrackingResponseDto.setAdminComments("Approved");
		orderTrackingResponseDto.setOrderStatus("Completed");
	}

	@Test
	public void getTrackDetailsSuccess() throws InvalidTrackIdException {
		Mockito.when(orderTrackingService.getOrderTrackingDetails(Mockito.any())).thenReturn(orderTrackingResponseDto);
		ResponseEntity<OrderTrackingResponseDto> response = orderTrackingController.getOrderTrackingDetails(trackId);
		// expected actual
		Assert.assertEquals(200, response.getStatusCodeValue());
		Assert.assertEquals("Completed", response.getBody().getOrderStatus());
		Assert.assertEquals("Approved", response.getBody().getAdminComments());
	}
}
