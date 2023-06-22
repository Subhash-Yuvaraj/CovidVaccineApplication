package com.covid.vaccination.bookingslots.model;
import java.util.Date;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="users")
public class User {
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getuId() {
		return uId;
	}
	public void setuId(Long uId) {
		this.uId = uId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getResetPasswordToken() {
		return resetPasswordToken;
	}
	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public java.util.Date getDOB() {
		return DOB;
	}
	public void setDOB(java.util.Date dOB) {
		DOB = dOB;
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
	public Set<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}
	public User(Long uId, String email, String password, String resetPasswordToken, String name, Date dOB,
			String addressLine1, String addressLine2, Integer pin, Set<Booking> bookings) {
		super();
		this.uId = uId;
		this.email = email;
		this.password = password;
		this.resetPasswordToken = resetPasswordToken;
		this.name = name;
		DOB = dOB;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.pin = pin;
		this.bookings = bookings;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uId;
	@Column(nullable=false,unique=true,length=30)
	private String email;
	@Column(nullable=false,length=32)
	private String password;
	@Column(length=32)
	private String resetPasswordToken;
	@Column(nullable=false,length=30)
	private String name;
	@Column(nullable=false)
	private java.util.Date DOB;
	@Column(nullable=false,length=50)
	private String addressLine1;
	@Column(nullable=false,length=50)
	private String addressLine2;
	@Column(nullable=false)
	private Integer pin;
	@OneToMany(mappedBy="user",fetch=FetchType.EAGER)
	private Set<Booking> bookings;
	
}
