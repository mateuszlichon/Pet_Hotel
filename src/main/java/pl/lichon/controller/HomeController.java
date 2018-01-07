package pl.lichon.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.lichon.entity.Hotel;
import pl.lichon.repository.HotelRepository;

@Controller
public class HomeController {

	@Autowired
	private HotelRepository hotelRepository;
	
	@GetMapping("")
	public String home(Model m) {
		List<String> citiesList = getCitiesList();
		m.addAttribute("citiesList", citiesList);
		return "home";
	}
	
	@PostMapping("")
	public String homePost(@RequestParam String city, Model m) {
		List<Hotel> cityHotels = this.hotelRepository.findAllByAddressCity(city);
		m.addAttribute("cityHotels", cityHotels);
		List<String> citiesList = getCitiesList();
		m.addAttribute("citiesList", citiesList);
		return "home";
	}
	
	@ModelAttribute("availableHotels")
	public List<Hotel> getHotels() {
		return this.hotelRepository.findAll();
	}
	
	public List<String> getCitiesList() {
		HashSet<String> cities = new HashSet<String>();
		List<Hotel> hotels = this.hotelRepository.findAll();
		for (Hotel hotel : hotels) {
			cities.add(hotel.getAddressCity());
		}
		List<String> citiesList = new ArrayList<String>(cities);
		return citiesList;
	}
}
