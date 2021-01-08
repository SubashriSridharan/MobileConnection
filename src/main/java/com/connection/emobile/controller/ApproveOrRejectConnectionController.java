package com.connection.emobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.connection.emobile.dto.ApproveRequestDto;
import com.connection.emobile.dto.ApproveResponseDto;
import com.connection.emobile.exception.InvalidTrackIdException;
import com.connection.emobile.service.ApproveRequestService;

@RestController
@RequestMapping("/request")
public class ApproveOrRejectConnectionController {
	@Autowired
	ApproveRequestService approveRequestService;

	/**
	 * @author Rathna
	 * @throws InvalidTrackIdException
	 * @apiNote This method will be used by Admin to approve/reject the application
	 *          request for a new connection after verifying the details provided by
	 *          user
	 *          
	 *
	 */
	@PutMapping("/{trackId}")
	public ResponseEntity<ApproveResponseDto> approvalRequest(@RequestBody ApproveRequestDto approveRequestDto,
			@RequestParam Integer trackId) throws InvalidTrackIdException {
		return new ResponseEntity<>(approveRequestService.approveRequestByAdmin(approveRequestDto, trackId),
				HttpStatus.OK);
	}
}
