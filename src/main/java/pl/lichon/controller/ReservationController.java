package pl.lichon.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.lichon.entity.Hotel;
import pl.lichon.entity.Pet;
import pl.lichon.entity.Reservation;
import pl.lichon.entity.User;
import pl.lichon.repository.HotelRepository;
import pl.lichon.repository.PetRepository;
import pl.lichon.repository.ReservationRepository;

@Controller
@RequestMapping("reservation")
public class ReservationController {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@GetMapping("/newPetReservation")
	public String newReservation(Model m) {
		m.addAttribute("reservation", new Reservation());
		return "reservation/pet_reservation";
	}
	
	@PostMapping("/newPetReservation")
	public String newReservationPost(@Valid @ModelAttribute Reservation reservation, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "reservation/pet_reservation";
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
