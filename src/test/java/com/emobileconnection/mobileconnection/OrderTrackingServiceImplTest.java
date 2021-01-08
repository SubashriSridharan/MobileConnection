package com.emobileconnection.mobileconnection;


import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.connection.emobile.dto.OrderTrackingResponseDto;
import com.connection.emobile.entity.Track;
import com.connection.emobile.exception.InvalidTrackIdException;
import com.connection.emobile.repository.OrderTrackingRepository;
import com.connection.emobile.service.OrderTrackingServiceImpl;


 

@RunWith(MockitoJUnitRunner.class)
public class OrderTrackingServiceImplTest {
    
    @Mock
    OrderTrackingRepository orderTrackingRepository;
    
    @InjectMocks
    OrderTrackingServiceImpl orderTrackingServiceImpl;
    
    Track track = new Track();
    
    Integer trackId = 1;
    
    @Before
    public void setup() {
        track.setApproverComments("Approved");
        track.setStatus("Completed");
    }
    
    @Test
    public void getTrackDetailsSuccess() throws InvalidTrackIdException {
        Mockito.when(orderTrackingRepository.findByTrackId(Mockito.any())).thenReturn(Optional.of(track));
        OrderTrackingResponseDto trackResponseDto = orderTrackingServiceImpl.getOrderTrackingDetails(trackId);
        Assert.assertEquals("Completed", trackResponseDto.getOrderStatus());
        Assert.assertEquals("Approved", trackResponseDto.getAdminComments());
    }
    
    @Test(expected=InvalidTrackIdException.class)
    public void noTrackIdFound() throws InvalidTrackIdException {
        Mockito.when(orderTrackingRepository.findByTrackId(Mockito.any())).thenReturn(Optional.empty());
        orderTrackingServiceImpl.getOrderTrackingDetails(trackId);
    }
    

 

}
 
