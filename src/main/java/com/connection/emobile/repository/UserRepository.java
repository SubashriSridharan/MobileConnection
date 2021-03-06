package com.connection.emobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connection.emobile.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
