package com.covid.vaccination.bookingslots;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class CovidVaccinationBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidVaccinationBookingApplication.class, args);
	}

}
