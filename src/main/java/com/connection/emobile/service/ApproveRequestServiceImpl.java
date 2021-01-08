package com.connection.emobile.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connection.emobile.MobileconnectionApplication;
import com.connection.emobile.dto.ApproveRequestDto;
import com.connection.emobile.dto.ApproveResponseDto;
import com.connection.emobile.entity.Track;
import com.connection.emobile.exception.InvalidTrackIdException;
import com.connection.emobile.repository.TrackRepository;
import com.connection.emobile.util.MobileConnectionConstants;

@Service
public class ApproveRequestServiceImpl implements ApproveRequestService {

	@Autowired
	TrackRepository trackRepository;

	@Override

	public ApproveResponseDto approveRequestByAdmin(ApproveRequestDto approveRequestDto, Integer trackId)
			throws InvalidTrackIdException {
		ApproveResponseDto approveResponseDto = new ApproveResponseDto();
		Optional<Track> trackRecord = trackRepository.findById(trackId);
		if (trackRecord.isPresent()) {
			Track track = trackRecord.get();
			track.setStatus(approveRequestDto.getStatus());
			track.setApproverComments(approveRequestDto.getApproverComments());
			track.setAdminId(1);
			trackRepository.save(track);
		} else {
			throw new InvalidTrackIdException(MobileConnectionConstants.NO_TRACK_ID_FOUND);

		}
		approveResponseDto.setMessage("Success");
		approveResponseDto.setStatusCode("200");

		return approveResponseDto;
	}

}
