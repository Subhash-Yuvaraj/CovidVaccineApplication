package com.covid.vaccination.bookingslots.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.vaccination.bookingslots.model.Centre;
import com.covid.vaccination.bookingslots.repository.CentreRepository;
@Service
public class CentreServiceImpl implements CentreService {
	@Autowired
	private CentreRepository repository;
	@Override
	public List<Centre> findByPinAndStateTrue(Integer pin) {
		
		return repository.findByPinAndStateTrue(pin);
	}

	@Override
	public List<Centre> findByAddressLine2ContainingAndStateTrue(String city) {
		
		return repository.findByAddressLine2ContainingAndStateTrue(city);
	}

	@Override
	public List<Centre> findByWorkingFromGreaterThanAndStateTrue(String start) {
		// TODO Auto-generated method stub
		
		return repository.findByWorkingFromGreaterThanAndStateTrue(start);
	}

	@Override
	public void changeStateOfCentre(boolean state, Long cId) {
		// TODO Auto-generated method stub
		repository.changeStateOfCentre(state, cId);

	}
	@Override
	public Centre addCentre(Centre centre) {
		return repository.save(centre);
	}

	@Override
	public Centre save(Centre centre) {
		// TODO Auto-generated method stub
		return repository.save(centre);
	}

	@Override
	public List<Centre> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	@Override
	public Centre getCenterById(Long cId) {
		// TODO Auto-generated method stub
		return repository.getOne(cId);
	}
	@Override
	public Centre saveCenter(Centre existingCenter) {
		// TODO Auto-generated method stub
		return repository.save(existingCenter);
	}

	@Override
	public List<Centre> findByPinAndWorkingFromGreaterThanAndStateTrue(Integer pin, String start) {
		// TODO Auto-generated method stub
		return repository.findByPinAndWorkingFromGreaterThanAndStateTrue(pin, start);
	}

}
