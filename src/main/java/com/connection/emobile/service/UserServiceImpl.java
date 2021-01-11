package com.connection.emobile.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connection.emobile.dto.UserRequestDto;
import com.connection.emobile.dto.UserResponseDto;
import com.connection.emobile.entity.MobileNumber;
import com.connection.emobile.entity.Plan;
import com.connection.emobile.entity.Track;
import com.connection.emobile.entity.User;
import com.connection.emobile.exception.CommonException;
import com.connection.emobile.repository.MobileNumberRepository;
import com.connection.emobile.repository.PlanRepository;
import com.connection.emobile.repository.TrackRepository;
import com.connection.emobile.repository.UserRepository;
import com.connection.emobile.util.Constants;
import com.connection.emobile.util.MobileNumberStatusEnum;
import com.connection.emobile.util.OrderEnum;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private TrackRepository trackRepository;
	@Autowired
	private MobileNumberRepository mobileNumberRepository;

	/*
	 * This method is used for requesting new mobile service connection
	 * 
	 * UserRequestDto object as a request body that contains user personal info and
	 * mobile plan details
	 * 
	 * @return TrackId and Message for the placed mobile service request
	 */
	@Transactional
	public Optional<UserResponseDto> save(final UserRequestDto userRequestDto) {

		logger.info("UserService Impl - Request for new Connection");

		if (String.valueOf(userRequestDto.getMobileNumber()).length() != 10) {

			throw new CommonException(Constants.INVALID_MOBILE_NUMBER);
		}

		if (!validateEmail(userRequestDto.getEmailId())) {
			throw new CommonException(Constants.INVALID_EMAIL);
		}

		User user = new User();
		user.setAddress(userRequestDto.getAddress());
		user.setName(userRequestDto.getUsername());
		user.setEmailId(userRequestDto.getEmailId());
		user.setPancardNumber(userRequestDto.getPanCardNumber());

		User userResponse = userRepository.save(user);

		Optional<Plan> planResponse = planRepository.findById(userRequestDto.getPlanId());

		Track track = new Track();
		track.setPlanId(userRequestDto.getPlanId());
		track.setStatus(OrderEnum.INPROGRESS.toString());
		track.setUserId(userResponse.getUserId());
		track.setMobileNumber(userRequestDto.getMobileNumber());
		track.setApproverComments(MobileNumberStatusEnum.REQUESTED.toString());

		if (planResponse.get().getType().equalsIgnoreCase(Constants.POSTPAID))
			track.setAdminId(1);
		else if (planResponse.get().getType().equalsIgnoreCase(Constants.PREPAID))
			track.setAdminId(2);

		Track trackInfo = trackRepository.save(track);

		Optional<MobileNumber> mobileNumberRespone = mobileNumberRepository.findById(userRequestDto.getMobileNumber());

		MobileNumber updateMobilenumberStatus = mobileNumberRespone.get();
		updateMobilenumberStatus.setStatus(MobileNumberStatusEnum.REQUESTED.toString());

		mobileNumberRepository.save(updateMobilenumberStatus);

		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setTrackId(trackInfo.getTrackId());
		userResponseDto.setMessage("Successfully Placed the Request");

		return Optional.of(userResponseDto);

	}

	private Boolean validateEmail(String email) {

		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pattern = Pattern.compile(emailRegex);

		return pattern.matcher(email).matches();
	}

	public List<Long> availableMobileNumbers() {
		return mobileNumberRepository.findAll().stream().map(MobileNumber::getMobileNumber)
				.collect(Collectors.toList());
	}
}
