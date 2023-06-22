package com.covid.vaccination.bookingslots.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covid.vaccination.bookingslots.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	Admin findByEmail(String email);
}
