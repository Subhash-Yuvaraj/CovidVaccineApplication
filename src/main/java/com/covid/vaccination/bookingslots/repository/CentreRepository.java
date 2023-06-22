package com.covid.vaccination.bookingslots.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.covid.vaccination.bookingslots.model.Centre;

public interface CentreRepository extends JpaRepository<Centre, Long> {
	
	List<Centre> findByPinAndStateTrue(Integer pin);
	List<Centre> findByAddressLine2ContainingAndStateTrue(String city);
	List<Centre> findByWorkingFromGreaterThanAndStateTrue(String start);
	List<Centre> findByPinAndWorkingFromGreaterThanAndStateTrue(Integer pin,String start);
	@Modifying
	@Query("update Centre c set c.state=?1 where c.cId=?2")
	void changeStateOfCentre(boolean state,Long cId);
}
