package com.covid.vaccination.bookingslots.service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.vaccination.bookingslots.model.Admin;
import com.covid.vaccination.bookingslots.repository.AdminRepository;
@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminRepository adminRepository;
	@Override
	public boolean authenticate(String email, String password) throws NoSuchAlgorithmException{
		
	      MessageDigest md = MessageDigest.getInstance("MD5");

	      md.update(password.getBytes());

	      byte[] bytes = md.digest();

	      StringBuilder sb = new StringBuilder();
	      for (int i = 0; i < bytes.length; i++) {
	        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	      }

	      String hashed = sb.toString();
	    
		
		System.out.println(hashed);
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null && admin.getPassword().equals(hashed))
            return true;
        
        return false; 
    }
	@Override
	public Admin findByEmail(String email) {
		// TODO Auto-generated method stub
		return adminRepository.findByEmail(email);
	}
	@Override
	public void updateResetPassword(String token, String email) {
		// TODO Auto-generated method stub
		adminRepository.updateResetPassword(token,email);
		
	}
	@Override
	public Admin getByToken(String token) {
		// TODO Auto-generated method stub
		return adminRepository.getByResetPasswordToken(token);
	}
	@Override
	public Admin save(Admin admin) {
		// TODO Auto-generated method stub
		return adminRepository.save(admin);
	}
	

}
