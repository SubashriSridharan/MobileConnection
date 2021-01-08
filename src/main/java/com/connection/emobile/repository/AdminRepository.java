package com.connection.emobile.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.connection.emobile.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	
	@Query("SELECT u.name, u.address, u.emailId, u.pancardNumber, "
			+"t.trackId, t.status, t.mobileNumber, " 
			+"t.planId, p.type, p.description "
			+"FROM User u, Track t, Plan p "
			+"WHERE	t.adminId = :adminId AND t.status = :status AND u.userId = t.userId AND "
			+"t.planId = p.planId")
	public List<Object> getNewConnections(@Param("adminId") int adminId, @Param("status") String status);

}
