package com.covid.vaccination.bookingslots.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.vaccination.bookingslots.model.Booking;
import com.covid.vaccination.bookingslots.model.Slot;
import com.covid.vaccination.bookingslots.model.User;
import com.covid.vaccination.bookingslots.repository.BookingRepository;
@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	private BookingRepository repository;
	@Override
	public Booking findBySlotAndUser(Slot slot, User user) {
		// TODO Auto-generated method stub
		return repository.findBySlotAndUser(slot,user);
	}
	@Override
	public Booking save(Booking booking) {
		// TODO Auto-generated method stub
		return repository.save(booking);
	}
	@Override
	public void deleteById(Long bId) {
		// TODO Auto-generated method stub
		repository.deleteById(bId);
	}
	@SuppressWarnings("deprecation")
	@Override
	public Booking getOne(Long bId) {
		// TODO Auto-generated method stub
		return repository.getOne(bId);
	}

}
