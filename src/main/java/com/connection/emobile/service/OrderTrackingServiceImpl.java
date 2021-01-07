package com.connection.emobile.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connection.emobile.dto.OrderTrackingResponseDto;
import com.connection.emobile.entity.Track;
import com.connection.emobile.exception.InvalidTrackIdException;
import com.connection.emobile.repository.OrderTrackingRepository;
import com.connection.emobile.util.MobileConnectionContants;

@Service
public class OrderTrackingServiceImpl implements OrderTrackingService {

	@Autowired
	OrderTrackingRepository orderTrackingRepository;

	@Override
	public OrderTrackingResponseDto getOrderTrackingDetails(Integer trackId) throws InvalidTrackIdException {

		Optional<Track> trackingDetails = orderTrackingRepository.findByTrackId(trackId);
		if (trackingDetails.isPresent()) {
			OrderTrackingResponseDto orderTrackingResponseDto = new OrderTrackingResponseDto();
			orderTrackingResponseDto.setAdminComments(trackingDetails.get().getApproverComments());
			orderTrackingResponseDto.setOrderStatus(trackingDetails.get().getTrackStatus());

			return orderTrackingResponseDto;
		} else {
			throw new InvalidTrackIdException(MobileConnectionContants.NO_TRACK_ID_FOUND);
		}
	}
}
