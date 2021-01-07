package com.connection.emobile.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connection.emobile.dto.UserRequestDto;
import com.connection.emobile.dto.UserResponseDto;
import com.connection.emobile.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class UserController {

	@Autowired
	private UserService userService;
	
	/*
	 * This method is used for requesting new mobile service connection
	 * 
	 * UserRequestDto object as a request body that contains user personal info and mobile plan details
	 * 
	 * @return TrackId and Message for the placed mobile service request
	 */
	
	@PostMapping("/newConnection")
	public ResponseEntity<UserResponseDto> save(@RequestBody final UserRequestDto userRequestDto) {

		Optional<UserResponseDto> userResponseDto = userService.save(userRequestDto);

		return new ResponseEntity<>(userResponseDto.get(), HttpStatus.OK);

	}

}
