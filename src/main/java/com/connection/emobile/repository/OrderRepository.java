package com.connection.emobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connection.emobile.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
