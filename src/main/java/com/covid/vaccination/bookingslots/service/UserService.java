package com.covid.vaccination.bookingslots.service;

import com.covid.vaccination.bookingslots.model.User;

public interface UserService {
	public User findByEmail(String email);
	public User save(User user);
	public void updateResetPassword(String token, String email);
	public User getByToken(String token);
}
