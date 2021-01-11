package com.connection.emobile.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/*
	 * This method is used for requesting new mobile service connection
	 * 
	 * UserRequestDto object as a request body that contains user personal info and
	 * mobile plan details
	 * 
	 * @return TrackId and Message for the placed mobile service request
	 */

	@PostMapping("/newConnection")
	public ResponseEntity<UserResponseDto> save(@RequestBody final UserRequestDto userRequestDto) {

		logger.info("User Controller - Request for new Connection");

		Optional<UserResponseDto> userResponseDto = userService.save(userRequestDto);

		return new ResponseEntity<>(userResponseDto.get(), HttpStatus.OK);

	}

	/*
	 * This method is used for get the available mobile numbers from database
	 *
	 * @return List of mobile numbers
	 */
	@GetMapping(value = "/mobileNumbers")
	public ResponseEntity<List<Long>> availableMobileNumbers() {
		return new ResponseEntity<>(userService.availableMobileNumbers(), HttpStatus.OK);
	}

}
