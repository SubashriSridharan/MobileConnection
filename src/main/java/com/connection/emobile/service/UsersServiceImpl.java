package com.connection.emobile.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.connection.emobile.entity.Users;
import com.connection.emobile.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	private UsersRepository usersRepository;
	
	//dervied or dynamic query
	@Override
	public List<Users> getUsersList() {
		List<Users> usersList = usersRepository.findAll();
		return usersList;
	}
	
	//dervied or dynamic query
	@Override
	public List<Users> getUsersListByProfessionAndSalary(String profession, double salary) {
		List<Users> usersList = usersRepository.findAllByProfessionAndSalaryGreaterThan(profession,salary);
		return usersList;
	}

	//customized query
	@Override
	public List<Users> usersListByProfessionAndSalary(String profession, double salary) {
		List<Users> usersList = usersRepository.getUsersListProfessionAndSalary(profession, salary);
		return usersList;
	}
	
	//named query
	@Override
	public List<Users>  getUsersListBySalary() {
		List<Users> usersList = usersRepository.usersListBySalary();
		return usersList;
	}


}
