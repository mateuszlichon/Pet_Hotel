package pl.lichon.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.lichon.bean.SessionManager;
import pl.lichon.entity.Hotel;
import pl.lichon.entity.Pet;
import pl.lichon.entity.Reservation;
import pl.lichon.entity.ReservationDate;
import pl.lichon.entity.User;
import pl.lichon.repository.HotelRepository;
import pl.lichon.repository.PetRepository;
import pl.lichon.repository.ReservationDateRepository;
import pl.lichon.repository.ReservationRepository;

@Controller
@RequestMapping("reservationDate")
public class ReservationDateController {

	@Autowired
	private ReservationDateRepository reservationDateRepository;
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@GetMapping("/{petId}/newPetReservation")
	public String newReservation(Model m, @PathVariable long petId) {
		m.addAttribute("reservation", new ReservationDate());
		return "reservation/pet_reservation_date";
	}
	
	@GetMapping("/{petId}/newPetReservation/{dateId}")
	public String dateDetails(@PathVariable long dateId, Model m, @PathVariable long petId) {
		ReservationDate date = this.reservationDateRepository.findOne(dateId);
		Pet pet = this.petRepository.findOne(petId);
		date.addPet(pet);
		date.setPlacesLeft(date.getPlacesLeft()-1);
		this.reservationDateRepository.save(date);
		return "redirect:/";
	}
	
	@GetMapping("/{hotelId}/newHotelReservation")
	public String newHotelReservation(Model m, @PathVariable long hotelId) {
		List<ReservationDate> datesJanuary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotelId, 1);
		List<ReservationDate> datesFebruary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotelId, 2);
		Hotel hotel = this.hotelRepository.findOne(hotelId);
		HttpSession s = SessionManager.session();
		s.setAttribute("chosenHotel", hotel);
		m.addAttribute("hotel", hotel);
		m.addAttribute("datesJanuary", datesJanuary);
		m.addAttribute("datesFebruary", datesFebruary);
		m.addAttribute("reservation", new ReservationDate());
		return "reservation/hotel_reservation_date";
	}

	@GetMapping("/{dateId}/date")
	public String newHotelReservationDate(Model m, @PathVariable long dateId) {
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("chosenHotel");
		List<ReservationDate> datesJanuary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), 1);
		List<ReservationDate> datesFebruary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), 2);
		List<ReservationDate> chosenDate = (List<ReservationDate>) s.getAttribute("chosenDate");
		if(chosenDate == null) {
			chosenDate = new ArrayList<>();
		}
		ReservationDate rs = this.reservationDateRepository.findOne(dateId);
		//making sure there are no repetitions
		for (ReservationDate reservationDate : chosenDate) {
			if(reservationDate.getId() == rs.getId()) {
				chosenDate.remove(rs);
				s.setAttribute("chosenDate", chosenDate);
				m.addAttribute("hotel", hotel);
				m.addAttribute("datesJanuary", datesJanuary);
				m.addAttribute("datesFebruary", datesFebruary);
				m.addAttribute("reservation", new ReservationDate());
				return "reservation/hotel_reservation_date";
			}
		}
		chosenDate.add(rs);
		s.setAttribute("chosenDate", chosenDate);
		m.addAttribute("hotel", hotel);
		m.addAttribute("datesJanuary", datesJanuary);
		m.addAttribute("datesFebruary", datesFebruary);
		m.addAttribute("reservation", new ReservationDate());
		return "reservation/hotel_reservation_date";
	}
	
	@GetMapping("/clear")
	public String clearDates(Model m) {
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("chosenHotel");
		List<ReservationDate> datesJanuary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), 1);
		List<ReservationDate> datesFebruary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), 2);
		List<ReservationDate> chosenDate = (List<ReservationDate>) s.getAttribute("chosenDate");
		if(chosenDate == null) {
			chosenDate = new ArrayList<>();
		}
		chosenDate.clear();
		s.setAttribute("chosenDate", chosenDate);
		m.addAttribute("hotel", hotel);
		m.addAttribute("datesJanuary", datesJanuary);
		m.addAttribute("datesFebruary", datesFebruary);
		m.addAttribute("reservation", new ReservationDate());
		return "reservation/hotel_reservation_date";
	}
	
	
	@GetMapping("/{hotelId}/newHotelReservation/{dateId}/pet/{petId}")
	public String newReservation(Model m, @PathVariable long hotelId, @PathVariable long dateId, @PathVariable long petId, @ModelAttribute Pet newPet) {
		ReservationDate rd = this.reservationDateRepository.findOne(dateId);
		Pet pet = this.petRepository.findOne(petId);
		rd.addPet(pet);
		rd.setPlacesLeft(rd.getPlacesLeft()-1);
		this.reservationDateRepository.save(rd);
		m.addAttribute("reservation", rd);
		return "reservation/reservation_confirmation";
	}
	
	@PostMapping("/{hotelId}/newHotelReservation/{dateId}/pet")
	public String newReservationPost(Model m, @PathVariable long hotelId, @PathVariable long dateId, @ModelAttribute Pet newPet) {
		ReservationDate rd = this.reservationDateRepository.findOne(dateId);
		this.petRepository.save(newPet);
		rd.addPet(newPet);
		rd.setPlacesLeft(rd.getPlacesLeft()-1);
		this.reservationDateRepository.save(rd);
		m.addAttribute("reservation", rd);
		return "reservation/reservation_confirmation";
	}
	
/*	@PostMapping("/newPetReservation")
	public String newReservationPost(@Valid @ModelAttribute Reservation reservation, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "reservation/pet_reservation_date";
		}
		Hotel hotel = reservation.getHotel();
		Date date = reservation.getDate();
		Reservation existingReservation = this.reservationRepository.findOneByHotelAndDate(hotel, date);
		if (existingReservation != null) {
			reservation = existingReservation;
			reservation.setPlacesLeft(reservation.getPlacesLeft()-1);
		} else {
			reservation.setPlacesLeft(hotel.getCapacity() - 1);
		}
		this.reservationRepository.save(reservation);
		return "redirect:/";
	}*/
	
	@ModelAttribute("availableDates")
	public List<ReservationDate> getDates() {
		return this.reservationDateRepository.findAll();
	}
	
	@ModelAttribute("availablePets")
	public List<Pet> getPets() {
		return this.petRepository.findAll();
	}
	
	@ModelAttribute("availableHotels")
	public List<Hotel> getHotels() {
		return this.hotelRepository.findAll();
	}
}
