package com.covid.vaccination.bookingslots.service;

import java.security.NoSuchAlgorithmException;

import com.covid.vaccination.bookingslots.model.Admin;

public interface AdminService {
	public boolean authenticate(String email, String password) throws NoSuchAlgorithmException;
	public Admin findByEmail(String email);
	public void updateResetPassword(String token, String email);
	public Admin getByToken(String token);
	public Admin save(Admin admin);
}
