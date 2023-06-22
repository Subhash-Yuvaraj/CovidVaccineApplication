package com.covid.vaccination.bookingslots.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Centre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cId;
	@Column(nullable=false,length=50)
	private String addressLine1;
	@Column(nullable=false,length=50)
	private String addressLine2;
	@Column(nullable=false)
	private Integer pin;
	@Column(nullable=false)
	private boolean state;
	@Column(nullable=false)
	private String workingFrom;
	@Column(nullable=false)
	private String workingTo;
	@OneToMany(mappedBy="centre",fetch=FetchType.EAGER)
	private Set<Slot> slots;
	public Long getcId() {
		return cId;
	}
	public void setcId(Long cId) {
		this.cId = cId;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public Integer getPin() {
		return pin;
	}
	public void setPin(Integer pin) {
		this.pin = pin;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getWorkingFrom() {
		return workingFrom;
	}
	public void setWorkingFrom(String workingFrom) {
		this.workingFrom = workingFrom;
	}
	public String getWorkingTo() {
		return workingTo;
	}
	public void setWorkingTo(String workingTo) {
		this.workingTo = workingTo;
	}
	public Set<Slot> getSlots() {
		return slots;
	}
	public void setSlots(Set<Slot> slots) {
		this.slots = slots;
	}
	public Centre() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Centre(Long cId, String addressLine1, String addressLine2, Integer pin, boolean state, String workingFrom,
			String workingTo, Set<Slot> slots) {
		super();
		this.cId = cId;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.pin = pin;
		this.state = state;
		this.workingFrom = workingFrom;
		this.workingTo = workingTo;
		this.slots = slots;
	} 
}
