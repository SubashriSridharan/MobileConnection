package com.connection.emobile.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.connection.emobile.dto.ApproveRequestDto;
import com.connection.emobile.dto.ApproveResponseDto;
import com.connection.emobile.exception.InvalidTrackIdException;
import com.connection.emobile.service.ApproveRequestService;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ApproveOrRejectConnectionControllerTest {
	@Mock
	private ApproveRequestService approveRequestService;
	@InjectMocks
	private ApproveOrRejectConnectionController approveOrRejectController;
	ApproveResponseDto approveResponseDto;
	ApproveRequestDto approveRequestDto;

	@BeforeAll
	public void setUp() {

		approveRequestDto = new ApproveRequestDto();
		approveRequestDto.setStatus("Approved");
		approveRequestDto.setApproverComments("Accepted");

		approveResponseDto = new ApproveResponseDto();
		approveResponseDto.setMessage("success");
		approveResponseDto.setStatusCode("200");
		
}
   @Test
	public void approvalRequestTest() throws InvalidTrackIdException {
		// GIVEN

		Mockito.when(approveRequestService.approveRequestByAdmin(approveRequestDto, 1)).thenReturn(approveResponseDto);
		// WHEN
		ResponseEntity<ApproveResponseDto> response = approveOrRejectController.approvalRequest(approveRequestDto, 1);
		// THEN
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

	}
}
