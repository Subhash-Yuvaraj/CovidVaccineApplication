package com.covid.vaccination.bookingslots.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.covid.vaccination.bookingslots.config.Utility;
import com.covid.vaccination.bookingslots.model.Admin;
import com.covid.vaccination.bookingslots.model.Centre;
import com.covid.vaccination.bookingslots.model.Slot;
import com.covid.vaccination.bookingslots.service.AdminServiceImpl;
import com.covid.vaccination.bookingslots.service.CentreServiceImpl;
import com.covid.vaccination.bookingslots.service.SlotServiceImpl;

import net.bytebuddy.utility.RandomString;
@Controller
public class AdminController {
	@Autowired 
	private CentreServiceImpl centreService;
	@Autowired
	private SlotServiceImpl slotService;
	@Autowired
	private AdminServiceImpl adminService;
	Admin adminHome=null;
	@Autowired
	private JavaMailSender mailSender;
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
	public String viewAddCentre(Model model,RedirectAttributes redirectAttributes) {
		if(adminHome==null) {
			redirectAttributes.addFlashAttribute("error", "You have to login first");
			return "redirect:/adminlogin";
		}
		return "addCentre";
	}
	@PostMapping("/addCentre")
	public String addCentre(@RequestParam String addressLine1,@RequestParam String addressLine2,@RequestParam String workingFrom,RedirectAttributes redirectAttributes,@RequestParam String workingTo,@RequestParam Integer pin,Model model) {
		Centre centre=new Centre();
		centre.setAddressLine1(addressLine1);
		centre.setAddressLine2(addressLine2);
		centre.setPin(pin);
		centre.setState(true);
		centre.setWorkingFrom(workingFrom);
		centre.setWorkingTo(workingTo);
		if(adminHome==null) {
			redirectAttributes.addFlashAttribute("error", "Login first");
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
	public String viewCentres(Model model,RedirectAttributes redirectAttributes) {
		if(adminHome==null) {
			redirectAttributes.addFlashAttribute("error", "Login first");
			return "redirect:/adminlogin";
		}
		model.addAttribute("centers", centreService.findAll());
		return "viewCentres";
	}	
	@GetMapping("/admindashboard")
	public String dashboard(RedirectAttributes redirectAttributes) {
		if(adminHome==null) {
			redirectAttributes.addFlashAttribute("error", "Login first");
			return "redirect:/adminlogin";
		}
		return "admindashboard";
	}
	@GetMapping("/manage")
    public String showManageCenterPage(@RequestParam Long cId, Model model,RedirectAttributes redirectAttributes) {
        Centre center = centreService.getCenterById(cId);
        
        if(adminHome==null){
        	redirectAttributes.addFlashAttribute("error", "first you have to login");
        	return "redirect:/adminlogin";
        }
        if (center != null && center.getcId()==cId) {
        	model.addAttribute("center", center);
            return "manage";
        } else {
        	redirectAttributes.addFlashAttribute("error", "Enter proper Centre id");
            return "viewCentres";
        }
    }
		@GetMapping("/addSlot")
		public String showAddSlotPage(@RequestParam("cId") Long cId, Model model,RedirectAttributes redirectAttributes) {
			if(adminHome==null) {
				redirectAttributes.addFlashAttribute("error", "Login first");	
				return "redirect:/adminlogin";
			}
			model.addAttribute("cId", cId);
			Centre centre=centreService.findOne(cId);
			model.addAttribute("slots", centre.getSlots());
			return "addSlot";
		}
		@PostMapping("/saveSlot")
	    public String saveSlot(@RequestParam("cId") Long cId,RedirectAttributes redirectAttributes, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, Model model) {
			if(adminHome==null) {
				redirectAttributes.addFlashAttribute("error", "Login first");	
				return "redirect:/adminlogin";
			}
			Centre centre = centreService.getCenterById(cId);
	        if (centre == null) {
	        	redirectAttributes.addFlashAttribute("error", "Enter the proper centre id");
	            return "redirect:/viewCentres"; 
	        }
	        if(slotService.findByDateAndCentre(date, centre)!=null) {
	        	redirectAttributes.addFlashAttribute("error", "Slot has already been added");
	        	return "redirect:/viewCentres";
	        }
	        	
	        
	        Slot slot = new Slot();
	        slot.setDate(date);
	        slot.setCentre(centre);

	        slotService.save(slot);

        	redirectAttributes.addFlashAttribute("error", "Slot has been added successfully");
	        return "redirect:/addSlot?cId=" + cId; 
	    }

	@PostMapping("/manage/{cId}/update")
    public String updateCenter(@PathVariable("cId") Long cId,RedirectAttributes redirectAttributes, @ModelAttribute("center") Centre updatedCenter,Model model) {
        Centre existingCenter = centreService.getCenterById(cId);
        if(adminHome==null) {
			redirectAttributes.addFlashAttribute("error", "Login first");	
			return "redirect:/adminlogin";
		}
        if (existingCenter != null) {
        	Set<Slot>slots=existingCenter.getSlots();
        	for(Slot slot:slots) {
        		if(slot.getBookings().size()>0 && slot.getDate().compareTo(new Date())>=0) {
        			redirectAttributes.addAttribute("error", "People has already booked for future dose in the slot,so you can't edit");
        			return "redirect:/manage?cId=" + cId;

        			
        		}
        	}
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
	@GetMapping("/adminlogout")
	public String adminlogout(RedirectAttributes redirectAttributes,Model model) {
		adminHome=null;
		redirectAttributes.addFlashAttribute("error", "You have been successfuly logged out");
		return "adminlogin";
	}
	@GetMapping("/adminforgotPassword")
    public String forgotPassword() {
		adminHome=null;
    	return "adminforgotPassword";
    }
    @PostMapping("/adminforgotPassword")
	public String sendRequest(HttpServletRequest http,ModelMap m)  {
		String email=http.getParameter("email");
		String token=RandomString.make(32);
		try {
			adminService.updateResetPassword(token, email);
			String resetPasswordLink=Utility.getSiteURL(http)+"/adminResetPassword?token="+token;
			sendEmail(email,resetPasswordLink);
			m.put("error", "Link has been sent");
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			
			m.put("error","Error in server. Please try again");
			
		}
		return "adminforgotPassword";
	}
    private void sendEmail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {
		// TODO Auto-generated method stub
		MimeMessage msg=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(msg);
		helper.setFrom("contact@covidvaccine.com", "Covid Vaccination Support");
		helper.setTo(email);
		String subject="Here's the link to reset your mail";
		String content="<p>Hello</p>"
			+"You have requested to reset your password."
			+"Click the below link to change your password:"
			+"<a href=\""+resetPasswordLink+ "\">Click here</a>"
			+"<p>Ignore this email if you remember your password</p>";
		helper.setSubject(subject);
		helper.setText(content,true);
		mailSender.send(msg);	
	}
    @GetMapping("/adminResetPassword")
	public String resetPasswordForm(@Param(value="token")String token,Model m) {
		Admin admin=adminService.getByToken(token);
		adminHome=null;
		if(admin==null) {
			m.addAttribute("title","Reset your password");
			m.addAttribute("message","Invalid token");
			return "message";
			
		}
		m.addAttribute("token",token);
		m.addAttribute("pageTitle","Reset Your Password");
		return "adminResetPassword";
	}
    @PostMapping("/adminResetPassword")
	public String processResetPassword(RedirectAttributes redirectAttributes,HttpServletRequest request,Model m) throws NoSuchAlgorithmException {
		String token=request.getParameter("token");
		String password=request.getParameter("password");
		Admin admin=adminService.getByToken(token);
		String confirm=request.getParameter("confirmpassword");
		if(admin==null) {
			m.addAttribute("title","Reset your password");
			m.addAttribute("error","Invalid token");
			return "resetpassword";
			
		}
		else if(!password.equals(confirm)) {
			m.addAttribute("title","Reset your password");
			m.addAttribute("error", "Both passwords don't match");
			return "userResetPassword";
		}
		else {
			MessageDigest md = MessageDigest.getInstance("MD5");

		      md.update(password.getBytes());

		      byte[] bytes = md.digest();

		      StringBuilder sb = new StringBuilder();
		      for (int i = 0; i < bytes.length; i++) {
		        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		      }
		      String hashed = sb.toString();
			admin.setPassword(hashed);
			adminService.save(admin);
			redirectAttributes.addFlashAttribute("error", "You have successfully changed the password");
			return "redirect:/adminlogin";
		}
	}

}

