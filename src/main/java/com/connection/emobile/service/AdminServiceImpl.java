package com.connection.emobile.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.connection.emobile.repository.AdminRepository;
import com.connection.emobile.util.OrderEnum;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminRepository adminRepository;

	@Override
	public List<Object> getNewConnectionRequests(int adminId) {
		List<Object> newConnectionRequests = adminRepository.getNewConnections(adminId, OrderEnum.INPROGRESS.toString());
		return newConnectionRequests;
		
	}
	
	

}
