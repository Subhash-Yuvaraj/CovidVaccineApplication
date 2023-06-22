package com.covid.vaccination.bookingslots.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.covid.vaccination.bookingslots.model.Admin;
import com.covid.vaccination.bookingslots.model.Centre;
import com.covid.vaccination.bookingslots.model.Slot;
import com.covid.vaccination.bookingslots.service.AdminServiceImpl;
import com.covid.vaccination.bookingslots.service.CentreServiceImpl;
import com.covid.vaccination.bookingslots.service.SlotServiceImpl;
@Controller
public class AdminController {
	@Autowired 
	private CentreServiceImpl centreService;
	@Autowired
	private SlotServiceImpl slotService;
	@Autowired
	private AdminServiceImpl adminService;
	Admin adminHome=null;
	@GetMapping("/adminlogin")
	public String showLoginForm(Model model) {
        return "adminlogin";
    }
	@PostMapping("/adminlogin")
	public String processLoginForm( @ModelAttribute("admin") Admin admin,
             Model model) throws NoSuchAlgorithmException {

		
		if (adminService.authenticate(admin.getEmail(), admin.getPassword())) {
			adminHome=admin;
			return "redirect:/admindashboard";
		}
			
		 else {
			model.addAttribute("error", "Invalid email or password");
			adminHome=null;
			return "adminlogin";
		}
	}
	@GetMapping("/addCentre")
	public String viewAddCentre(Model model) {
		if(adminHome==null) {
			model.addAttribute("error", "Login in first");
			return "redirect:/adminlogin";
		}
		return "addCentre";
	}
	@PostMapping("/addCentre")
	public String addCentre(@RequestParam String addressLine1,@RequestParam String addressLine2,@RequestParam String workingFrom,@RequestParam String workingTo,@RequestParam Integer pin,Model model) {
		Centre centre=new Centre();
		centre.setAddressLine1(addressLine1);
		centre.setAddressLine2(addressLine2);
		centre.setPin(pin);
		centre.setState(true);
		centre.setWorkingFrom(workingFrom);
		centre.setWorkingTo(workingTo);
		if(adminHome==null) {
			model.addAttribute("error", "Login first");
			return "adminlogin";
		}
		System.out.println("Successfully to be saved");
		centreService.save(centre);
		model.addAttribute("error", "Centre added successfully");
		return "addCentre";
	}
	@PostMapping("/adminaddSlot")
	public Slot addSlot(@RequestBody Centre centre,@RequestParam Date date) {
		Slot slot=slotService.findByDateAndCentre(date, centre);
		if(slot==null) {
			Slot s=new Slot();
			s.setCentre(centre);
			s.setDate(date);
			slot=slotService.save(s);
		}
		return slot;		
	}
	@GetMapping("/viewCentres")
	public String viewCentres(Model model) {
		if(adminHome==null) {
			return "redirect:/adminlogin";
		}
		model.addAttribute("centers", centreService.findAll());
		return "viewCentres";
	}
	@GetMapping("/admindashboard")
	public String dashboard() {
		return "admindashboard";
	}
	@GetMapping("/manage")
    public String showManageCenterPage(@RequestParam Long cId, Model model) {
        Centre center = centreService.getCenterById(cId);
        if(adminHome==null){
        	return "redirect:/adminlogin";
        }
        if (center != null) {
            model.addAttribute("center", center);
            return "manage";
        } else {
            return "redirect:/viewCentres";
        }
    }
		@GetMapping("/addSlot")
		public String showAddSlotPage(@RequestParam("cId") Long cId, Model model) {
			model.addAttribute("cId", cId);
			return "addSlot";
		}
		@PostMapping("/saveSlot")
	    public String saveSlot(@RequestParam("cId") Long cId, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, Model model) {
	        Centre centre = centreService.getCenterById(cId);
	        if (centre == null) {
	            return "redirect:/viewCentres"; // Replace with the desired error page
	        }
	        if(slotService.findByDateAndCentre(date, centre)!=null)
	        	return "redirect:/viewCentres";
	        
	        Slot slot = new Slot();
	        slot.setDate(date);
	        slot.setCentre(centre);

	        slotService.save(slot);


	        return "redirect:/addSlot?cId=" + cId; // Redirect to the manage page for the specific Centre
	    }

	@PostMapping("/manage/{cId}/update")
    public String updateCenter(@PathVariable("cId") Long cId, @ModelAttribute("center") Centre updatedCenter,Model model) {
        Centre existingCenter = centreService.getCenterById(cId);
        
        if (existingCenter != null) {
        	Set<Slot>slots=existingCenter.getSlots();
        	for(Slot slot:slots) {
        		if(slot.getBookings().size()>0) {
        			model.addAttribute("error", "People has already booked in the slot you can't edit");
        			return "redirect:/manage?cId=" + cId;

        		}
        	}
            // Update the fields of the existing center with the new values
            existingCenter.setAddressLine1(updatedCenter.getAddressLine1());
            existingCenter.setAddressLine2(updatedCenter.getAddressLine2());
            existingCenter.setPin(updatedCenter.getPin());
            existingCenter.setState(updatedCenter.isState());
            existingCenter.setWorkingFrom(updatedCenter.getWorkingFrom());
            existingCenter.setWorkingTo(updatedCenter.getWorkingTo());
            
            centreService.saveCenter(existingCenter);
            model.addAttribute("error", "Edited");
            return "redirect:/manage?cId=" + cId;
        } else {
            
            return "redirect:/viewCentres";
        }
    }
}
