package com.covid.vaccination.bookingslots.service;

import com.covid.vaccination.bookingslots.model.Booking;
import com.covid.vaccination.bookingslots.model.Slot;
import com.covid.vaccination.bookingslots.model.User;

public interface BookingService {
	public Booking findBySlotAndUser(Slot slot,User user);

	public Booking save(Booking booking);
}
