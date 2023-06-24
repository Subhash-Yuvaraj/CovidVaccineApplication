package com.covid.vaccination.bookingslots.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.covid.vaccination.bookingslots.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	Admin findByEmail(String email);
	@Transactional
	@Modifying
	@Query("update Admin a set a.resetPasswordToken=?1 where a.email=?2")
	void updateResetPassword(String token, String email);
	Admin getByResetPasswordToken(String token);
}
