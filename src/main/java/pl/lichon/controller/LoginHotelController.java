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
import org.springframework.web.bind.annotation.RequestMapping;

import pl.lichon.bean.LoginData;
import pl.lichon.bean.SessionManager;
import pl.lichon.entity.Hotel;
import pl.lichon.entity.ReservationDate;
import pl.lichon.repository.HotelRepository;
import pl.lichon.repository.MonthRepository;
import pl.lichon.repository.ReservationDateRepository;

@Controller
public class LoginHotelController {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private ReservationDateRepository reservationDateRepository;
	
	@Autowired
	private MonthRepository monthRepository;
	
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
		//January
		for (int i = 1; i <= 31; i++) {
			ReservationDate rd = new ReservationDate(i, (i%7), this.monthRepository.findOne(1l), 2018, hotel, hotel.getCapacity());
			this.reservationDateRepository.save(rd);
		}
		//February
		for (int i = 1; i <= 28; i++) {
			ReservationDate rd = new ReservationDate(i, ((i+3)%7), this.monthRepository.findOne(2l), 2018, hotel, hotel.getCapacity());
			this.reservationDateRepository.save(rd);
		}
		//March
		for (int i = 1; i <= 31; i++) {
			ReservationDate rd = new ReservationDate(i, ((i+3)%7), this.monthRepository.findOne(3l), 2018, hotel, hotel.getCapacity());
			this.reservationDateRepository.save(rd);
		}
		//April
		for (int i = 1; i <= 30; i++) {
			ReservationDate rd = new ReservationDate(i, ((i+6)%7), this.monthRepository.findOne(4l), 2018, hotel, hotel.getCapacity());
			this.reservationDateRepository.save(rd);
		}
		//May
		for (int i = 1; i <= 31; i++) {
			ReservationDate rd = new ReservationDate(i, ((i+1)%7), this.monthRepository.findOne(5l), 2018, hotel, hotel.getCapacity());
			this.reservationDateRepository.save(rd);
		}
		//June
		for (int i = 1; i <= 30; i++) {
			ReservationDate rd = new ReservationDate(i, ((i+4)%7), this.monthRepository.findOne(6l), 2018, hotel, hotel.getCapacity());
			this.reservationDateRepository.save(rd);
		}
		return "redirect:/";
	}
	
	@PostMapping("/loginHotel")
	public String loginHotelPost(@Valid @ModelAttribute LoginData loginData, Model m, BindingResult bindingResult) {
		Hotel h = this.hotelRepository.findOneByEmail(loginData.getEmail());
			if (h != null && h.isPasswordCorrect(loginData.getPassword())) {
				HttpSession s = SessionManager.session();
				s.setAttribute("hotel", h);
				return "redirect:/hotel/showReservations";
			}
			m.addAttribute("msg", "Enter valid data");
			return "login/login_hotel";
	}
	

}
