package com.covid.vaccination.bookingslots.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.covid.vaccination.bookingslots.config.Utility;
import com.covid.vaccination.bookingslots.model.Booking;
import com.covid.vaccination.bookingslots.model.Centre;
import com.covid.vaccination.bookingslots.model.Slot;
import com.covid.vaccination.bookingslots.model.User;
import com.covid.vaccination.bookingslots.service.BookingServiceImpl;
import com.covid.vaccination.bookingslots.service.CentreServiceImpl;
import com.covid.vaccination.bookingslots.service.PDFGeneratorService;
import com.covid.vaccination.bookingslots.service.SlotServiceImpl;
import com.covid.vaccination.bookingslots.service.UserServiceImpl;
import com.lowagie.text.DocumentException;

import net.bytebuddy.utility.RandomString;

@Controller
public class UserController {
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private CentreServiceImpl centreService;
	User userhome=null;
	@Autowired
	private SlotServiceImpl slotService;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private PDFGeneratorService generator;
    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }
    @Autowired
    private BookingServiceImpl bookingService;
    @PostMapping("/signup")
    public String signup(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("dob") String dob,
            @RequestParam("address1") String address1,
            @RequestParam("address2") String address2,
            @RequestParam("pin") Integer pin,
            Model model) throws ParseException {
        
        User existingUser = userService.findByEmail(email);
        if (existingUser != null) {
            model.addAttribute("error", "Email already exists");
            return "redirect:/signup";
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashedPassword(password));
        user.setName(name);
        DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
        Date date = (Date) parser.parse(dob);
        user.setDOB(date);
        user.setAddressLine1(address1);
        user.setAddressLine2(address2);
        user.setPin(pin);
        
        userService.save(user);
        
        return "redirect:/userlogin"; 
      }
    @GetMapping("/userforgotpassword")
	public String showForm(Model m) {
		
		return "forgotpassword";
	}
    
    @GetMapping("/userlogin")
    public String showLoginForm(Model model) {
    	userhome=null;
        return "userlogin";
    }
    
    @PostMapping("/userlogin")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model) {
           	
        User user = userService.findByEmail(email);
        password=hashedPassword(password);
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid email or password"); // Set error message
            return "userlogin";
        }
        userhome=user;
        return "redirect:/userdashboard"; 
    }
    @GetMapping("/viewHistory")
    public String viewHistory(Model model,RedirectAttributes redirectAttributes) {
    	if(userhome==null) {
    		redirectAttributes.addFlashAttribute("error", "You have to login first");
    		return "redirect:/userlogin";
    	}
    	userhome=userService.findByEmail(userhome.getEmail());
        Set<Booking> bookings = userhome.getBookings();
        model.addAttribute("bookings", bookings);
        
        return "viewHistory";
    }

    String hashedPassword(String password) {
    	try {
    	MessageDigest md = MessageDigest.getInstance("MD5");
    	
	      md.update(password.getBytes());

	      byte[] bytes = md.digest();

	      StringBuilder sb = new StringBuilder();
	      for (int i = 0; i < bytes.length; i++) {
	        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	      }

	      // Get complete hashed password in hex format
	      String hashed = sb.toString();
	      return hashed;
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    @GetMapping("/userdashboard")
    public String userDashboard(RedirectAttributes redirectAttributes,Model model) {
    	if(userhome==null) {
    		redirectAttributes.addFlashAttribute("error", "You have to login first");
    		return "redirect:/userlogin";
    	}
    	model.addAttribute("name", userhome.getName());
        return "userdashboard";
    }
    @GetMapping("/bookSlots")
    public String bookSlots(@RequestParam(name = "pin", required = false) Integer pin,
            @RequestParam(name = "workingFrom", required = false) String workingFrom,
            Model model,RedirectAttributes redirectAttributes) throws ParseException {
    	if(userhome==null) {
    		redirectAttributes.addFlashAttribute("error", "You have to login first");
    		return "redirect:/userlogin";
    	}
    	userhome=userService.findByEmail(userhome.getEmail());
    	Set<Booking>bookings=userhome.getBookings();
    	System.out.println(bookings.size());
    	if(bookings.size()>1) {
    		redirectAttributes.addFlashAttribute("error", "You have already booked for two doses");
    		return "redirect:/userdashboard";
    	}
    	model.addAttribute("name", userhome.getName());
    	List<Centre> centres=centreService.findByPinAndWorkingFromGreaterThanAndStateTrue(pin, workingFrom);
    	List<Slot> slots=new ArrayList<Slot>();
        Date date=new Date();
    	for(Centre centre:centres) {
    		List<Slot> slotInner=slotService.findByDateGreaterThanAndCentre(date,centre);
    		slots.addAll(slotInner);
    	}
    	
    	model.addAttribute("slots", slots);
    	return "bookSlots";
    	
    }
    @GetMapping(value = "/bookSlot")
    public String bookSlot(@RequestParam("sId") Long sId, Model model,RedirectAttributes redirectAttributes) {
        
        if (userhome == null) {
        	redirectAttributes.addFlashAttribute("error", "You have to login first");
            return "redirect:/userlogin";
        }
        userhome=userService.findByEmail(userhome.getEmail());
        Slot slot = slotService.getOne(sId);
        if(userhome.getBookings().size()>1) {
        	redirectAttributes.addFlashAttribute("error", "You have already booked for two doses");
    		return "redirect:/userdashboard";
        }
        if (slot != null && bookingService.findBySlotAndUser(slot, userhome)==null ) {
            Booking booking = new Booking();
            booking.setSlot(slot);
            booking.setUser(userhome);
            if(slot.getBookings().size()>9) {
            	redirectAttributes.addFlashAttribute("error", "The slot is full");
            	
            }
            else if(userhome.getBookings().size()>1) {
            	redirectAttributes.addFlashAttribute("error", "You have booked 2 slots already");
            }
            else {
            	bookingService.save(booking);
                redirectAttributes.addFlashAttribute("error", "You have booked a slot");
            }
            return "redirect:/userdashboard";
        }

        return "redirect:/bookSlots";
    }
    @GetMapping("/userlogout")
    public String userLogout(Model model) {
    	
    	userhome=null;
    	return "userlogin";
    }
    @GetMapping("/userforgotPassword")
    public String forgotPassword() {
    	userhome=null;
    	return "userforgotPassword";
    }
    @PostMapping("/userforgotPassword")
	public String sendRequest(HttpServletRequest http,ModelMap m)  {
		String email=http.getParameter("email");
		String token=RandomString.make(32);
		try {
			userService.updateResetPassword(token, email);
			String resetPasswordLink=Utility.getSiteURL(http)+"/userResetPassword?token="+token;
			sendEmail(email,resetPasswordLink);
			m.put("error", "Link has been sent");
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			
			m.put("error",e);
			
		}
		return "userforgotPassword";
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
    @GetMapping("/userResetPassword")
	public String resetPasswordForm(@Param(value="token")String token,Model m) {
		User user=userService.getByToken(token);
		if(user==null) {
			m.addAttribute("title","Reset your password");
			m.addAttribute("message","Invalid token");
			return "message";
			
		}
		m.addAttribute("token",token);
		m.addAttribute("pageTitle","Reset Your Password");
		return "userResetPassword";
	}
    @PostMapping("/userResetPassword")
	public String processResetPassword(RedirectAttributes redirectAttributes,HttpServletRequest request,Model m) {
		String token=request.getParameter("token");
		String password=request.getParameter("password");
		String confirm=request.getParameter("confirmpassword");
		User user=userService.getByToken(token);
		if(user==null) {
			m.addAttribute("title","Reset your password");
			m.addAttribute("error","Invalid token");
			return "userResetPassword";
			
		}
		else if(!password.equals(confirm)) {
			m.addAttribute("title","Reset your password");
			m.addAttribute("error", "Both passwords don't match");
			return "userResetPassword";
		}
		else {
			
			user.setPassword(hashedPassword(password));
			userService.save(user);
			redirectAttributes.addFlashAttribute("error", "You have successfully changed the password");
			return "redirect:/userlogin";
		}
	}
    @GetMapping("/cancel")
    public String cancelBooking(Model model,RedirectAttributes redirectAttributes,@RequestParam Long bId) {
    	if(userhome==null) {
    		redirectAttributes.addFlashAttribute("error", "You have to login first");
    		return "redirect:/userlogin";
    	}
    	
    	for(Booking booking:userService.findByEmail(userhome.getEmail()).getBookings()) {
    		if(booking.getbId().longValue()==bId.longValue() && booking.getSlot().getDate().after(new Date())) {
    			System.out.println("condition met");
    			Booking bookings=bookingService.getOne(bId);
    			if(bookings==null) {
    				redirectAttributes.addFlashAttribute("error", "Wrong booking id");
    				return "redirect:/viewHistory";
    			}
    			bookingService.deleteById(bId);
    			userhome=userService.findByEmail(userhome.getEmail());
    			redirectAttributes.addFlashAttribute("error", "Booking cancelled");
    			return "redirect:/viewHistory";
    		}
    	}
    	
    	redirectAttributes.addFlashAttribute("error", "Wrong booking id");
    	return "redirect:/viewHistory";
    }
    @GetMapping("/download")
    public void certificate(RedirectAttributes redirectAttributes,@RequestParam Long bId,HttpServletResponse response) throws DocumentException, IOException {
    	if(userhome==null) {
    		redirectAttributes.addFlashAttribute("error", "You have to login first");
    		return;
    	}
    	Booking booking=bookingService.getOne(bId);
    	if(booking==null || !userhome.getEmail().equals(booking.getUser().getEmail())) {
    		redirectAttributes.addFlashAttribute("error", "You have given wrong booking id");
    		return;
    	}
    	
    	String headerkey="Content-Disposition";
    	String headerValue="attachment;filename=CovidCertificate.pdf";
    	response.setHeader(headerkey, headerValue);
    	generator.export(response, booking); 
    	return;
    }

}
