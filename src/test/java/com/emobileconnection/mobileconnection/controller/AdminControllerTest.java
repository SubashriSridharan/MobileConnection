package com.emobileconnection.mobileconnection.controller;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.connection.emobile.controller.AdminController;
import com.connection.emobile.dto.NewConnectionResponseDTO;
import com.connection.emobile.service.AdminService;

@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {

	@Mock
	AdminService adminService;

	@InjectMocks
	AdminController adminController;

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

		Mockito.when(adminService.getNewConnectionRequests(Mockito.anyInt())).thenReturn(newConnectionsList);

		ResponseEntity<List<NewConnectionResponseDTO>> responseDTO = adminController.viewConnectionRequests(adminId);

		assertEquals(HttpStatus.OK, responseDTO.getStatusCode());
		assertEquals(2, responseDTO.getBody().size());
		assertEquals("shreya.nair@gmail.com", responseDTO.getBody().get(0).getEmailId());

	}

}
