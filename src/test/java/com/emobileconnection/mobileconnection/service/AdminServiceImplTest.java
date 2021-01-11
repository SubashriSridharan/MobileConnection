package com.emobileconnection.mobileconnection.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.connection.emobile.repository.AdminRepository;
import com.connection.emobile.service.AdminServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceImplTest {
	
	@Mock
	AdminRepository adminRepository;

	@InjectMocks
	AdminServiceImpl adminService;

	private int adminId = 1;
	private List<Object> newConnectionsList;

	@Before
	public void setUp() {
		newConnectionsList = new ArrayList<>();
		Object[] first_request = new Object[10];
		first_request[0] = "Shreya";
		first_request[1] = "EC";
		first_request[2] = "shreya.nair@gmail.com";
		first_request[3] = "BHKLP9876H";
		first_request[4] = 1;
		first_request[5] = "INPROGRESS";
		first_request[6] = Long.parseLong("9987678098");
		first_request[7] = 1;
		first_request[8] = "prepaid";
		first_request[9] = "1GB Data Pack";

		Object[] second_request = new Object[10];
		second_request[0] = "Sravani";
		second_request[1] = "EC";
		second_request[2] = "naga.sravani@gmail.com";
		second_request[3] = "GHJKL90876T";
		second_request[4] = 1;
		second_request[5] = "INPROGRESS";
		second_request[6] = Long.parseLong("7786547890");
		second_request[7] = 1;
		second_request[8] = "prepaid";
		second_request[9] = "1GB Data Pack";

		newConnectionsList.add(first_request);
		newConnectionsList.add(second_request);
		
	}
	
	@Test
	public void testGetNewConnectionRequests() {
		Mockito.when(adminRepository.getNewConnections(Mockito.anyInt(), Mockito.anyString())).thenReturn(newConnectionsList);
		List<Object> responseDTO = adminService.getNewConnectionRequests(adminId);
		assertEquals(2, responseDTO.size());

	}
	

}
