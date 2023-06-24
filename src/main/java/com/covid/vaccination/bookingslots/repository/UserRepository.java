package com.covid.vaccination.bookingslots.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.covid.vaccination.bookingslots.model.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	@Transactional
	@Modifying
	@Query("update users u set u.resetPasswordToken=?1 where u.email=?2")
	void updateResetPassword(String token, String email);
	User getByResetPasswordToken(String token);
}
