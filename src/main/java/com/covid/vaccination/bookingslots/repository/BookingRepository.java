package com.covid.vaccination.bookingslots.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covid.vaccination.bookingslots.model.Booking;
import com.covid.vaccination.bookingslots.model.Slot;
import com.covid.vaccination.bookingslots.model.User;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	Booking findBySlotAndUser(Slot slot,User user);
}
