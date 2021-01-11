package com.emobileconnection.mobileconnection.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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
import com.connection.emobile.service.UserServiceImpl;
import com.connection.emobile.util.Constants;
import com.connection.emobile.util.MobileNumberStatusEnum;
import com.connection.emobile.util.OrderEnum;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private static final String ADDRESS = "Sandhi Sadhan Apartments";
    private static final String PAN_NUMBER = "ABCD34567";
    private static final String NAME = "subashri";
    private static final String EMAIL = "xyz@gmail.com";
    private static final int PLAN_ID = 101;
    private static final float PRICE = 299.0f;
    private static final float TALKTIME = 250.35f;
    private static final String DESCRIPTION = "Prepaid Plan for 56 day";
    private static final int USER_ID = 111;
    private static final int VALIDITY = 56;
    private static final int TRACK_ID = 1001;
    private static final long MOBILE_NUMBER = 9876543210L;
    private static final int ADMIN_ID = 9999;

    @Mock
    private UserRepository userRepository;
    @Mock
    PlanRepository planRepository;
    @Mock
    TrackRepository trackRepository;
    @Mock
    MobileNumberRepository mobileNumberRepository;
    @InjectMocks
    UserServiceImpl userService;

    User user = new User();
    Plan plan = new Plan();
    Track track = new Track();
    MobileNumber mobileNumber = new MobileNumber();
    UserRequestDto userRequestDto = new UserRequestDto();

    @Before
    public void init() {

        user.setAddress(ADDRESS);
        user.setEmailId(EMAIL);
        user.setName(NAME);
        user.setPancardNumber(PAN_NUMBER);
        user.setUserId(USER_ID);

        plan.setPlanId(PLAN_ID);
        plan.setPrice(PRICE);
        plan.setTalktime(TALKTIME);
        plan.setDescription(DESCRIPTION);
        plan.setType(Constants.PREPAID);
        plan.setValidity(VALIDITY);

        track.setTrackId(TRACK_ID);
        track.setPlanId(PLAN_ID);
        track.setStatus(OrderEnum.INPROGRESS.toString());
        track.setUserId(USER_ID);
        track.setAdminId(ADMIN_ID);
        track.setMobileNumber(MOBILE_NUMBER);

        mobileNumber.setMobileNumber(MOBILE_NUMBER);
        mobileNumber.setStatus(MobileNumberStatusEnum.INACTIVE.toString());

        userRequestDto.setAddress(ADDRESS);
        userRequestDto.setEmailId(EMAIL);
        userRequestDto.setMobileNumber(MOBILE_NUMBER);
        userRequestDto.setPanCardNumber(PAN_NUMBER);
        userRequestDto.setPlanId(PLAN_ID);
        userRequestDto.setUsername(NAME);

    }

    @Test
    public void testUserConnectionEnrollment() {

        mobileNumber.setStatus(MobileNumberStatusEnum.REQUESTED.toString());

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        Mockito.when(planRepository.findById(Mockito.any())).thenReturn(Optional.of(plan));
        Mockito.when(trackRepository.save(Mockito.any())).thenReturn(track);
        Mockito.when(mobileNumberRepository.findById(Mockito.any())).thenReturn(Optional.of(mobileNumber));
        Mockito.when(mobileNumberRepository.save(Mockito.any())).thenReturn(mobileNumber);

        Optional<UserResponseDto> userResponse = userService.save(userRequestDto);

        assertEquals(userResponse.get().getTrackId(), TRACK_ID);
    }

    @Test(expected = CommonException.class)
    public void testInvalidEmailId() {

        userRequestDto.setEmailId("xyz");
        Optional<UserResponseDto> userResponse = userService.save(userRequestDto);
        assertNotNull(userResponse.get());

    }

    @Test(expected = CommonException.class)
    public void testInvalidMobileNumber() {

        userRequestDto.setMobileNumber(345);
        Optional<UserResponseDto> userResponse = userService.save(userRequestDto);
        assertNotNull(userResponse.get());

    }

    @Test
    public void testAvailableMobileNumbers() {

        List<MobileNumber> listofMobileNumbers = new ArrayList<>();

        MobileNumber mobileNumber = new MobileNumber();
        mobileNumber.setMobileNumber(8075878L);
        mobileNumber.setStatus("active");

        MobileNumber mobileNumber2 = new MobileNumber();
        mobileNumber2.setMobileNumber(8075878L);
        mobileNumber2.setStatus("active");
        listofMobileNumbers.add(mobileNumber);
        listofMobileNumbers.add(mobileNumber2);

        Mockito.when(mobileNumberRepository.findAll()).thenReturn(listofMobileNumbers);
        List<Long> response = userService.availableMobileNumbers();
        Assert.assertNotNull(response);
        Mockito.verify(mobileNumberRepository, Mockito.timeout(2000).times(1)).findAll();

    }
}
