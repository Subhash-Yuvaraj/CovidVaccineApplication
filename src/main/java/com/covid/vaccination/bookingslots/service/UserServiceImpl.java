package com.covid.vaccination.bookingslots.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.vaccination.bookingslots.model.User;
import com.covid.vaccination.bookingslots.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository repository;
	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return repository.findByEmail(email);
	}
	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return repository.save(user);
	}
	@Override
	public void updateResetPassword(String token, String email) {
		// TODO Auto-generated method stub
		repository.updateResetPassword(token,email);
	}

}
