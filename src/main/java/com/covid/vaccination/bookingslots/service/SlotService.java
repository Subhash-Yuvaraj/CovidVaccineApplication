package com.covid.vaccination.bookingslots.service;

import java.util.Date;
import java.util.List;

import com.covid.vaccination.bookingslots.model.Centre;
import com.covid.vaccination.bookingslots.model.Slot;

public interface SlotService {
	public Slot save(Slot slot);
	public List<Slot> findByDateGreaterThanAndCentre(Date date, Centre centre);
	public Slot findByDateAndCentre(Date date, Centre centre);
	public Slot getOne(Long sId);
}
