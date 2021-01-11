package com.connection.emobile.service;


import com.connection.emobile.dto.ApproveRequestDto;
import com.connection.emobile.dto.ApproveResponseDto;
import com.connection.emobile.exception.InvalidTrackIdException;



public interface ApproveRequestService {
	public ApproveResponseDto approveRequestByAdmin(ApproveRequestDto approveRequestDto, Integer trackId)throws InvalidTrackIdException;
}
