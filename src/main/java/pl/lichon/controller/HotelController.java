package pl.lichon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.lichon.entity.Hotel;
import pl.lichon.repository.HotelRepository;

@Controller
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelRepository hotelRepository;
	
	@GetMapping("/view/{hotelId}")
	public String home(@PathVariable long hotelId, Model m) {
		Hotel hotel = this.hotelRepository.findOne(hotelId);
		m.addAttribute("hotel", hotel);
		return "hotel/hotel_info";
	}
	
	@ModelAttribute("availableHotels")
	public List<Hotel> getHotels() {
		return this.hotelRepository.findAll();
	}
}
