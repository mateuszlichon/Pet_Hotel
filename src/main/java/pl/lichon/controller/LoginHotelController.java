package pl.lichon.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.lichon.bean.LoginData;
import pl.lichon.bean.SessionManager;
import pl.lichon.entity.Hotel;
import pl.lichon.repository.HotelRepository;

@Controller
public class LoginHotelController {

	@Autowired
	private HotelRepository hotelRepository;
	
	// hotel section
	@GetMapping("/loginHotel")
	public String loginHotel(Model m) {
		m.addAttribute("loginData", new LoginData());
		return "login/login_hotel";
	}
	
	@GetMapping("/registerHotel")
	public String registerHotel(Model m) {
		m.addAttribute("hotel", new Hotel());
		return "login/register_hotel";
	}
	
	@PostMapping("/registerHotel")
	public String registerHotelPost(@Valid @ModelAttribute Hotel hotel, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "login/register_hotel";
		}
		this.hotelRepository.save(hotel);
		return "redirect:/";
	}
	
	@PostMapping("/loginHotel")
	public String loginHotelPost(@Valid @ModelAttribute LoginData loginData, Model m, BindingResult bindingResult) {
		Hotel h = this.hotelRepository.findOneByEmail(loginData.getEmail());
			if (h != null && h.isPasswordCorrect(loginData.getPassword())) {
				HttpSession s = SessionManager.session();
				s.setAttribute("hotel", h);
				return "login/login_hotel";
			}
			m.addAttribute("msg", "Enter valid data");
			return "login/login_hotel";
	}
	

}
