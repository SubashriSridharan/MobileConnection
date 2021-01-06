package com.connection.emobile.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.connection.emobile.dto.UserRequestDto;
import com.connection.emobile.dto.UserResponseDto;
import com.connection.emobile.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	public ResponseEntity<UserResponseDto> save(@RequestBody final UserRequestDto userRequestDto) {

		Optional<UserResponseDto> userResponseDto = userService.save(userRequestDto);

		return new ResponseEntity<>(userResponseDto.get(), HttpStatus.OK);

	}

}
