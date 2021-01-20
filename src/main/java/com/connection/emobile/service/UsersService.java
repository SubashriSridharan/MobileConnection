package com.connection.emobile.service;

import java.util.List;

import com.connection.emobile.entity.Users;

public interface UsersService {
	
	//derived or dynamic query
	public List<Users> getUsersList();
	//derived or dynamic query
	public List<Users> getUsersListByProfessionAndSalary(String profession, double salary);
	
	//customized query
	public List<Users> usersListByProfessionAndSalary(String profession, double salary);
	
	//named query
	public List<Users> getUsersListBySalary();

}
