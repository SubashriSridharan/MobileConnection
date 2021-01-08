package com.emobileconnection.mobileconnection.service;

import com.connection.emobile.entity.MobileNumber;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.timeout;

import org.mockito.junit.MockitoJUnitRunner;

import com.connection.emobile.entity.Track;
import com.connection.emobile.entity.Plan;
import com.connection.emobile.entity.User;
import com.connection.emobile.repository.MobileNumberRepository;
import com.connection.emobile.repository.TrackRepository;
import com.connection.emobile.repository.PlanRepository;
import com.connection.emobile.repository.UserRepository;
import com.connection.emobile.service.UserServiceImpl;
import com.connection.emobile.util.Constants;
import com.connection.emobile.util.OrderEnum;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PlanRepository planRepository;
    @Mock
    private TrackRepository orderRepository;
    @Mock
    private MobileNumberRepository mobileNumberRepository;
    @InjectMocks
    private UserServiceImpl userService;

    private User user = new User();

    private Plan plan = new Plan();

    private Track order = new Track();

    @Before
    public void init() {

        user.setAddress("Sandhi Sadhan Apartments");
        user.setEmailId("xyz@gmail.com");
        user.setName("subashri");
        user.setPancardNumber("ABCD34567");
        user.setUserId(1);

        plan.setPlanId(101);
        plan.setPrice(299.0f);
        plan.setTalktime(235.36f);
        plan.setDescription("Prepaid Plan for 56 days");
        plan.setType(Constants.PREPAID);
        plan.setValidity(56);

        order.setOrderId(1001);
        order.setPlanId(101);
        order.setStatus(OrderEnum.INPROGRESS.toString());
        order.setUserId(1);
        order.setAdminId(9999);

    }

    @Test
    public void testAvailableMobileNumbers() {

        List<MobileNumber> listofMobileNumbers = new ArrayList();

        MobileNumber mobileNumber = new MobileNumber();
        mobileNumber.setMobileNumber(8075878L);
        mobileNumber.setStatus("active");

        MobileNumber mobileNumber2 = new MobileNumber();
        mobileNumber2.setMobileNumber(8075878L);
        mobileNumber2.setStatus("active");
        listofMobileNumbers.add(mobileNumber);
        listofMobileNumbers.add(mobileNumber2);

        when(mobileNumberRepository.findAll()).thenReturn(listofMobileNumbers);
        List<Long> response = userService.availableMobileNumbers();
        Assert.assertNotNull(response);
        verify(mobileNumberRepository, timeout(2000).times(1)).findAll();
    }

}
