package com.emobileconnection.mobileconnection.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.connection.emobile.controller.UserController;
import com.connection.emobile.dto.UserRequestDto;
import com.connection.emobile.dto.UserResponseDto;
import com.connection.emobile.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	
	private static final int TRACK_ID=1001;
	private static final String ADDRESS = "Sandhi Sadhan Apartments";
	private static final String PAN_NUMBER = "ABCD34567";
	private static final String NAME = "subashri";
	private static final String EMAIL = "xyz@gmail.com";
	private static final int PLAN_ID = 101;
	private static final long MOBILE_NUMBER = 9876543210L;

	@Mock
	private UserService userService;
	@InjectMocks
	private UserController userController;
	
	UserResponseDto userResponse = new UserResponseDto();
	UserRequestDto userRequestDto = new UserRequestDto();
	
	@Before
	public void init() {
		
		userResponse.setTrackId(TRACK_ID);
		userResponse.setMessage("Request Placed successfully");
		
		userRequestDto.setAddress(ADDRESS);
		userRequestDto.setEmailId(EMAIL);
		userRequestDto.setMobileNumber(MOBILE_NUMBER);
		userRequestDto.setPanCardNumber(PAN_NUMBER);
		userRequestDto.setPlanId(PLAN_ID);
		userRequestDto.setUsername(NAME);
	}
	
	@Test
	public void testUserNewConnectionRequest() {
		
		Mockito.when(userService.save(Mockito.any())).thenReturn(Optional.of(userResponse));
		
		ResponseEntity<UserResponseDto> userResponseDto = userController.save(userRequestDto);
		
		assertEquals(TRACK_ID, userResponseDto.getBody().getTrackId());
		assertEquals(HttpStatus.OK.value(), userResponseDto.getStatusCode());
	}

	@Test
	public void testAvailableMobileNumbers() {
		ResponseEntity<List<Long>> listOfMobileNumbers = userController.availableMobileNumbers();
		assertNotNull(listOfMobileNumbers);
		assertEquals(200, listOfMobileNumbers.getStatusCodeValue());
	}
}
