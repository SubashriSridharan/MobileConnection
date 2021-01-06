package com.connection.emobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connection.emobile.entity.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

}
