package com.connection.emobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.connection.emobile.entity.Track;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer>{
	

}
