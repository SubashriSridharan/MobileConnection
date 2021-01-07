package com.connection.emobile.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.connection.emobile.entity.Track;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderTrackingRepository extends JpaRepository<Track, Integer> {

	public Optional<Track> findByTrackId(Integer trackId);

	@Query("SELECT t.userId from Track t " + "WHERE  t.trackStatus = :status")
	public List<Integer> getApprovedOrdersList(@Param("status") String status);

}

