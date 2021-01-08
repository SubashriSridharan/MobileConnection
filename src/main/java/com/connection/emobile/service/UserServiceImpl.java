package com.connection.emobile.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connection.emobile.dto.UserRequestDto;
import com.connection.emobile.dto.UserResponseDto;
import com.connection.emobile.entity.MobileNumber;
import com.connection.emobile.entity.Track;
import com.connection.emobile.entity.Plan;
import com.connection.emobile.entity.User;
import com.connection.emobile.repository.MobileNumberRepository;
import com.connection.emobile.repository.TrackRepository;
import com.connection.emobile.repository.PlanRepository;
import com.connection.emobile.repository.UserRepository;
import com.connection.emobile.util.Constants;
import com.connection.emobile.util.MobileNumberStatusEnum;
import com.connection.emobile.util.OrderEnum;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private TrackRepository orderRepository;
	@Autowired
	private MobileNumberRepository mobileNumberRepository;

	public Optional<UserResponseDto> save(final UserRequestDto userRequestDto) {

		User user = new User();
		user.setAddress(userRequestDto.getAddress());
		user.setName(userRequestDto.getUsername());
		user.setEmailId(userRequestDto.getEmailId());
		user.setPancardNumber(userRequestDto.getPanCardNumber());

		User userResponse = userRepository.save(user);

		Optional<Plan> planResponse = planRepository.findById(userRequestDto.getPlanId());

		Track order = new Track();
		order.setPlanId(userRequestDto.getPlanId());
		order.setStatus(OrderEnum.INPROGRESS.toString());
		order.setUserId(userResponse.getUserId());

		if (planResponse.get().getType().equalsIgnoreCase(Constants.POSTPAID))
			order.setAdminId(1);
		else if (planResponse.get().getType().equalsIgnoreCase(Constants.PREPAID))
			order.setAdminId(2);

		orderRepository.save(order);

		Optional<MobileNumber> mobileNumberRespone = mobileNumberRepository.findById(userRequestDto.getMobileNumber());

		MobileNumber updateMobilenumberStatus = mobileNumberRespone.get();
		updateMobilenumberStatus.setStatus(MobileNumberStatusEnum.REQUESTED.toString());

		mobileNumberRepository.save(updateMobilenumberStatus);

		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setOrderId(order.getOrderId());
		userResponseDto.setMessage("Successfully Placed the Request");

		return Optional.of(userResponseDto);

	}

	public List<Long> availableMobileNumbers(){
		return mobileNumberRepository.findAll()
				.stream()
				.map(MobileNumber::getMobileNumber).collect(Collectors.toList());
	}
}
