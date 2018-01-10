package pl.lichon.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.lichon.bean.SessionManager;
import pl.lichon.entity.Hotel;
import pl.lichon.entity.Pet;
import pl.lichon.entity.ReservationDate;
import pl.lichon.repository.HotelRepository;
import pl.lichon.repository.PetRepository;
import pl.lichon.repository.ReservationDateRepository;

@Controller
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private ReservationDateRepository reservationDateRepository;
	
	@Autowired
	private PetRepository petRepository;
	
	@GetMapping("/view/{hotelId}")
	public String home(@PathVariable long hotelId, Model m) {
		Hotel hotel = this.hotelRepository.findOne(hotelId);
		m.addAttribute("hotel", hotel);
		return "hotel/hotel_info";
	}
	
	@GetMapping("/showReservations")
	public String showReservations(Model m) {
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("hotel");
		List<ReservationDate> datesJanuary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), 1);
		List<ReservationDate> datesFebruary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), 2);
		m.addAttribute("datesJanuary", datesJanuary);
		m.addAttribute("datesFebruary", datesFebruary);
		return "hotel/reservation_view";
	}
	
	@GetMapping("/showReservations/{dateId}")
	public String showReservations(Model m, @PathVariable long dateId) {
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("hotel");
		List<ReservationDate> datesJanuary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), 1);
		List<ReservationDate> datesFebruary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), 2);
		ReservationDate hotelDate = this.reservationDateRepository.findOne(dateId);
		m.addAttribute("datesJanuary", datesJanuary);
		m.addAttribute("datesFebruary", datesFebruary);
		m.addAttribute("hotelDate", hotelDate);
		return "hotel/reservation_view";
	}
	
	@GetMapping("/showReservations/{dateId}/changeCapacityUp")
	public String changeCapacity(Model m, @PathVariable long dateId) {
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("hotel");
		List<ReservationDate> datesJanuary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), 1);
		List<ReservationDate> datesFebruary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), 2);
		ReservationDate hotelDate = this.reservationDateRepository.findOne(dateId);
		hotelDate.setPlacesLeft(hotelDate.getPlacesLeft()+1);
		this.reservationDateRepository.save(hotelDate);
		m.addAttribute("datesJanuary", datesJanuary);
		m.addAttribute("datesFebruary", datesFebruary);
		m.addAttribute("hotelDate", hotelDate);
		return "hotel/reservation_view";
	}
	
	@GetMapping("/showReservations/{dateId}/changeCapacityDown")
	public String changeCapacityDown(Model m, @PathVariable long dateId) {
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("hotel");
		List<ReservationDate> datesJanuary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), 1);
		List<ReservationDate> datesFebruary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), 2);
		ReservationDate hotelDate = this.reservationDateRepository.findOne(dateId);
		hotelDate.setPlacesLeft(hotelDate.getPlacesLeft()-1);
		this.reservationDateRepository.save(hotelDate);
		m.addAttribute("datesJanuary", datesJanuary);
		m.addAttribute("datesFebruary", datesFebruary);
		m.addAttribute("hotelDate", hotelDate);
		return "hotel/reservation_view";
	}
	
	@GetMapping("/showPet/{petId}")
	public String showPet(Model m, @PathVariable long petId) {
		Pet pet = this.petRepository.findOne(petId);
		m.addAttribute("pet", pet);
		return "hotel/show_pet";
	}
	
	
	
	@ModelAttribute("availableHotels")
	public List<Hotel> getHotels() {
		return this.hotelRepository.findAll();
	}
}
