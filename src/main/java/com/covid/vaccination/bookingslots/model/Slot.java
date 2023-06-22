package com.covid.vaccination.bookingslots.model;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Slot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sId;
	@Column(nullable=false)
	private Date date;
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="cId")
	private Centre centre;
	@OneToMany(mappedBy="slot",fetch=FetchType.EAGER)
	private Set<Booking> bookings;
	public Slot() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Slot(Long sId, Date date, Centre centre, Set<Booking> bookings) {
		super();
		this.sId = sId;
		this.date = date;
		this.centre = centre;
		this.bookings = bookings;
	}
	public Long getsId() {
		return sId;
	}
	public void setsId(Long sId) {
		this.sId = sId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Centre getCentre() {
		return centre;
	}
	public void setCentre(Centre centre) {
		this.centre = centre;
	}
	public Set<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}
	
}
