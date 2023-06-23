package com.covid.vaccination.bookingslots.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.vaccination.bookingslots.model.Centre;
import com.covid.vaccination.bookingslots.model.Slot;
import com.covid.vaccination.bookingslots.repository.SlotRepository;
@Service
public class SlotServiceImpl implements SlotService {
	@Autowired
	private SlotRepository repository;
	@Override
	public List<Slot> findByDateGreaterThanAndCentre(Date date, Centre centre) {
		return repository.findByDateGreaterThanAndCentre(date, centre);
	}
	@Override
	public Slot save(Slot slot) {
		// TODO Auto-generated method stub
		return repository.save(slot);
	}
	@Override
	public Slot findByDateAndCentre(Date date, Centre centre) {
		// TODO Auto-generated method stub
		return repository.findByDateAndCentre(date, centre);
	}
	@Override
	@SuppressWarnings("deprecation")
	public Slot getOne(Long sId) {
		// TODO Auto-generated method stub
		return repository.getOne(sId);
	}

}
