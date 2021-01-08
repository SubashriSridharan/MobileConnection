package com.connection.emobile.service;


import com.connection.emobile.dto.OrderTrackingResponseDto;
import com.connection.emobile.exception.InvalidTrackIdException;


public interface OrderTrackingService {
	public OrderTrackingResponseDto getOrderTrackingDetails(Integer trackId) throws InvalidTrackIdException;

}
