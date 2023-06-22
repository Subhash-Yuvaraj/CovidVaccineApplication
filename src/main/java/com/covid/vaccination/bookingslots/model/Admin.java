package com.covid.vaccination.bookingslots.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long aId;
	@Column(nullable=false,unique=true,length=30)
	private String email;
	@Column(nullable=false,length=32)
	private String password;
	@Column(length=32)
	private String resetPasswordToken;
	@Column(nullable=false,length=30)
	private String name;
	public Long getaId() {
		return aId;
	}
	public void setaId(Long aId) {
		this.aId = aId;
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
	public Admin(Long aId, String email, String password, String resetPasswordToken, String name) {
		super();
		this.aId = aId;
		this.email = email;
		this.password = password;
		this.resetPasswordToken = resetPasswordToken;
		this.name = name;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
