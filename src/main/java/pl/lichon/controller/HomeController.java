package pl.lichon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.lichon.entity.Hotel;
import pl.lichon.repository.HotelRepository;

@Controller
public class HomeController {

	@Autowired
	private HotelRepository hotelRepository;
	
	@GetMapping("")
	public String home(Model m) {
		m.addAttribute("hotel1", new Hotel());
		return "home";
	}
	
	@PostMapping("")
	public String homePost(@ModelAttribute Hotel hotel1, Model m) {
		List<Hotel> cityHotels = this.hotelRepository.findAllByAddressCity(hotel1.getAddressCity());
		m.addAttribute("cityHotels", cityHotels);
		m.addAttribute("hotel1", hotel1);
		return "home";
	}
	
	@ModelAttribute("availableHotels")
	public List<Hotel> getHotels() {
		return this.hotelRepository.findAll();
	}
}
