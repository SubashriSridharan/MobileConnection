package com.connection.emobile.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.connection.emobile.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{
	
	//dervied query
	List<Users> findAllByProfessionAndSalaryGreaterThan(String profession, double salary);
	
	//customized query
	@Query("SELECT u FROM Users u WHERE u.profession =?1 AND u.salary >= ?2")
	List<Users> getUsersListProfessionAndSalary(String profession, double salary);
	
	//named query
	List<Users> usersListBySalary();


}
