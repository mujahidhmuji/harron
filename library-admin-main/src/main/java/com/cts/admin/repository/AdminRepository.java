package com.cts.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.admin.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	@Query(value = "select password from admin where admin_id = ?1", nativeQuery = true)
	String getPassword(Long id);
	
	@Query(value="select admin_id from admin where email=?1", nativeQuery=true)
	Long getAdminId(String email);
	
	@Query(value="select * from admin where email=?1", nativeQuery=true)
	Admin findByEmail(String email);
}
