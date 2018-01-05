package pl.lichon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import pl.lichon.entity.Hotel;
import pl.lichon.repository.HotelRepository;

@Controller
public class HomeController {

	@Autowired
	private HotelRepository hotelRepository;
	
	@GetMapping("")
	public String home() {
		return "home";
	}
	
}
