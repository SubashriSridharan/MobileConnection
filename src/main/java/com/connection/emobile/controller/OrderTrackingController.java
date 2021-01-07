package com.connection.emobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.connection.emobile.dto.OrderTrackingResponseDto;
import com.connection.emobile.exception.InvalidTrackIdException;
import com.connection.emobile.service.OrderTrackingService;


import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/track")
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
	public ResponseEntity<OrderTrackingResponseDto> getOrderTrackingDetails(@RequestParam("trackId") Integer trackId)
			throws InvalidTrackIdException {

		//Log.info("inside tracking controller");
		OrderTrackingResponseDto orderTrackingResponseDto = orderTrackingService.getOrderTrackingDetails(trackId);
		//Log.info("getting back request");
		return new ResponseEntity<>(orderTrackingResponseDto, HttpStatus.OK);

	}
}
