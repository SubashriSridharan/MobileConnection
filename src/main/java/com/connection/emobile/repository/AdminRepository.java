package com.connection.emobile.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.connection.emobile.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	
	@Query("SELECT u.name, u.address, u.emailId, u.pancardNumber, "
			+"t.trackId, t.trackStatus, t.mobileNumber, " 
			+"t.planId, p.type "
			+"FROM User u, Track t, Plan p "
			+"WHERE	t.adminId = :adminId AND t.trackStatus = :trackStatus AND u.userId = t.userId AND "
			+"t.planId = p.planId")
	public List<Object> getNewConnections(@Param("adminId") int adminId, @Param("trackStatus") String trackStatus);

}
