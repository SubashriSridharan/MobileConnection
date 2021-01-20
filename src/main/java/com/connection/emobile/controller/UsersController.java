package com.connection.emobile.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.connection.emobile.entity.Users;
import com.connection.emobile.service.UsersService;

@RestController
@RequestMapping("/demo")
public class UsersController {
	
	@Autowired
	UsersService usersService;
	
	//dervied or dynamic queries or query by method name
	@GetMapping("/all-users")
	public ResponseEntity<List<Users>> getAllUsers() {

		List<Users> usersList = usersService.getUsersList();
		return new ResponseEntity<>(usersList, HttpStatus.OK);

	}
	
	//dervied or dynamic queries or query by method name
	@GetMapping("/selected-users")
	public ResponseEntity<List<Users>> getUsersByProfessionAndSalary(@RequestParam String profession, @RequestParam double salary) {

		List<Users> usersList = usersService.getUsersListByProfessionAndSalary(profession, salary);
		return new ResponseEntity<>(usersList, HttpStatus.OK);

	}
	
	//customized queries using query annotation
	@GetMapping("/selected-users-customized-query")
	public ResponseEntity<List<Users>> findUsersByProfessionAndSalary(@RequestParam String profession, @RequestParam double salary) {

		List<Users> usersList = usersService.usersListByProfessionAndSalary(profession, salary);
		return new ResponseEntity<>(usersList, HttpStatus.OK);

	}
	
	//named query
		@GetMapping("/selected-users-named-query")
		public ResponseEntity<List<Users>> findtUsersBySalary() {

			List<Users> usersList = usersService.getUsersListBySalary();
			return new ResponseEntity<>(usersList, HttpStatus.OK);

		}

}