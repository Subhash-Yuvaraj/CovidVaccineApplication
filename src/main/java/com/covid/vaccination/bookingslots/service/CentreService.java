package com.covid.vaccination.bookingslots.service;

import java.util.List;

import com.covid.vaccination.bookingslots.model.Centre;

public interface CentreService {
	public List<Centre> findByPinAndStateTrue(Integer pin);
	public List<Centre> findByAddressLine2ContainingAndStateTrue(String city);
	public List<Centre> findByWorkingFromGreaterThanAndStateTrue(String start);
	public void changeStateOfCentre(boolean state,Long cId);
	public Centre addCentre(Centre centre);
	public Centre save(Centre centre);
	public List<Centre> findAll();
	public Centre getCenterById(Long cId);
	Centre saveCenter(Centre existingCenter);
	public List<Centre> findByPinAndWorkingFromGreaterThanAndStateTrue(Integer pin,String start);
	public Centre findOne(Long cId);
}
