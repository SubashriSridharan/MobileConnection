package com.connection.emobile.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connection.emobile.dto.NewConnectionResponseDTO;
import com.connection.emobile.service.AdminService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	AdminService adminService;

	/**
	 * This endpoint is used get the list of new connection requests for admin to
	 * view
	 * 
	 * @param adminId
	 * @return List of new connection requests with status 200 OK and if there are
	 *         no new connections then return 204 No Content
	 */

	@GetMapping("/{adminId}/newConnections")
	public ResponseEntity<List<NewConnectionResponseDTO>> viewConnectionRequests(@PathVariable("adminId") int adminId) {
		
		logger.info("Request received to view new connection requests");
		List<Object> newConnectionRequests = adminService.getNewConnectionRequests(adminId);

		if (newConnectionRequests.size() > 0) {
			List<NewConnectionResponseDTO> connectionsList = new ArrayList<>();
			for (int i = 0; i < newConnectionRequests.size(); i++) {

				Object[] obj = (Object[]) newConnectionRequests.get(i);

				NewConnectionResponseDTO dto = new NewConnectionResponseDTO();
				dto.setUserName(obj[0].toString());
				dto.setAddress(obj[1].toString());
				dto.setEmailId(obj[2].toString());
				dto.setPancardNumber(obj[3].toString());
				dto.setTrackId(Integer.valueOf(obj[4].toString()));
				dto.setTrackStatus(obj[5].toString());
				dto.setMobileNumber(Long.valueOf(obj[6].toString()));
				dto.setPlanId(Integer.parseInt(obj[7].toString()));
				dto.setPlanType(obj[8].toString());

				connectionsList.add(dto);
			}
			return new ResponseEntity<>(connectionsList, HttpStatus.OK);
		} else {
			logger.info("There are no new connection requests");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

}
