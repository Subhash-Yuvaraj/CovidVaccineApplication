package com.covid.vaccination.bookingslots.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covid.vaccination.bookingslots.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
