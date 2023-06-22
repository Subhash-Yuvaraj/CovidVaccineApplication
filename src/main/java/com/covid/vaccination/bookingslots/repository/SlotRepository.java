package com.covid.vaccination.bookingslots.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covid.vaccination.bookingslots.model.Centre;
import com.covid.vaccination.bookingslots.model.Slot;

public interface SlotRepository extends JpaRepository<Slot, Long> {
	List<Slot> findByDateGreaterThanAndCentre(Date date,Centre centre);

	Slot findByDateAndCentre(Date date, Centre centre);
	
}
