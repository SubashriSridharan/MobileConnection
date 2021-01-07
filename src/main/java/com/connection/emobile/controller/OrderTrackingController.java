package com.connection.emobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.connection.emobile.dto.OrderTrackingResponseDto;
import com.connection.emobile.exception.InvalidTrackIdException;
import com.connection.emobile.service.OrderTrackingService;

@RestController
public class OrderTrackingController {

	@Autowired
	OrderTrackingService orderTrackingService;
	
	/**
	 * @author Sucheta
	 * @throws InvalidTrackIdException 
	 * @apiNote This method will be used by user to see the status of their new connection request.. 
	 *
	 */
	
	@GetMapping("/{trackId}")
	public ResponseEntity<OrderTrackingResponseDto> getTrackDetails(@RequestParam("trackId") Integer trackId)
			throws InvalidTrackIdException {

		OrderTrackingResponseDto orderTrackingResponseDto = orderTrackingService.getOrderTrackingDetails(trackId);
		return new ResponseEntity<>(orderTrackingResponseDto, HttpStatus.OK);

	}
}
