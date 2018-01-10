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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.lichon.bean.SessionManager;
import pl.lichon.entity.Hotel;
import pl.lichon.entity.Month;
import pl.lichon.entity.Pet;
import pl.lichon.entity.Reservation;
import pl.lichon.entity.ReservationDate;
import pl.lichon.entity.User;
import pl.lichon.repository.HotelRepository;
import pl.lichon.repository.MonthRepository;
import pl.lichon.repository.PetRepository;
import pl.lichon.repository.ReservationDateRepository;
import pl.lichon.repository.ReservationRepository;
import pl.lichon.repository.UserRepository;

@Controller
@RequestMapping("reservationDate")
public class ReservationDateController {

	@Autowired
	private ReservationDateRepository reservationDateRepository;
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MonthRepository monthRepository;
	
	//DIRECT PET RESERVATION OPTION
	@GetMapping("/{petId}/newPetReservation")
	public String newReservation(Model m, @PathVariable long petId) {
		Pet pet = this.petRepository.findOne(petId);
		HttpSession s = SessionManager.session();
		s.setAttribute("chosenPet", pet);
		return "redirect:/";
	}
	
/*	@GetMapping("/{petId}/newPetReservation/{dateId}")
	public String dateDetails(@PathVariable long dateId, Model m, @PathVariable long petId) {
		ReservationDate date = this.reservationDateRepository.findOne(dateId);
		Pet pet = this.petRepository.findOne(petId);
		date.addPet(pet);
		date.setPlacesLeft(date.getPlacesLeft()-1);
		this.reservationDateRepository.save(date);
		return "redirect:/";
	}*/
	
	//HOTEL OPTIONS
	@GetMapping("/{hotelId}/newHotelReservation")
	public String newHotelReservation(Model m, @PathVariable long hotelId) {
		Month chosenMonth = this.monthRepository.findOne(1l);
		Hotel hotel = this.hotelRepository.findOne(hotelId);
		List<ReservationDate> hotelDates = this.reservationDateRepository.findAllByHotelIdAndMonthIdOrderById(hotel.getId(), chosenMonth.getId());
		HttpSession s = SessionManager.session();
		s.setAttribute("chosenHotel", hotel);
		s.setAttribute("chosenMonth", chosenMonth);
		m.addAttribute("hotel", hotel);
		s.setAttribute("hotelDates", hotelDates);
//		m.addAttribute("reservation", new ReservationDate());
		return "reservation/hotel_reservation_date";
	}
	
	@GetMapping("/hotel/clear")
	public String clearHotel(Model m) {
		HttpSession s = SessionManager.session();
		s.setAttribute("chosenHotel", null);
		s.setAttribute("chosenDate", null);
		s.setAttribute("chosenMonth", null);
		return "redirect:/";
	}

	@GetMapping("/{monthId}/month")
	public String setMonth(Model m, @PathVariable long monthId) {
		Month chosenMonth = this.monthRepository.findOne(monthId);
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("chosenHotel");
		s.setAttribute("chosenMonth", chosenMonth);
		List<ReservationDate> hotelDates = this.reservationDateRepository.findAllByHotelIdAndMonthIdOrderById(hotel.getId(), chosenMonth.getId());
		s.setAttribute("chosenHotel", hotel);
		m.addAttribute("hotel", hotel);
		m.addAttribute("hotelDates", hotelDates);
/*		m.addAttribute("reservation", new ReservationDate());
*/		return "reservation/hotel_reservation_date";
	}
	
	
	//DATE OPTIONS
	@GetMapping("/{dateId}/date")
	public String newHotelReservationDate(Model m, @PathVariable long dateId) {
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("chosenHotel");
/*		List<ReservationDate> datesJanuary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), this.monthRepository.findOne(1l));
		List<ReservationDate> datesFebruary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), this.monthRepository.findOne(2l));*/
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
/*				m.addAttribute("datesJanuary", datesJanuary);
				m.addAttribute("datesFebruary", datesFebruary);*/
//				m.addAttribute("reservation", new ReservationDate());
				return "reservation/hotel_reservation_date";
			}
		}
		chosenDate.add(rs);
		s.setAttribute("chosenDate", chosenDate);
		m.addAttribute("hotel", hotel);
/*		m.addAttribute("datesJanuary", datesJanuary);
		m.addAttribute("datesFebruary", datesFebruary);*/
//		m.addAttribute("reservation", new ReservationDate());
		return "reservation/hotel_reservation_date";
	}
	
	@GetMapping("/clear")
	public String clearDates(Model m) {
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("chosenHotel");
/*		List<ReservationDate> datesJanuary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), this.monthRepository.findOne(1l));
		List<ReservationDate> datesFebruary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), this.monthRepository.findOne(2l));*/
		List<ReservationDate> chosenDate = (List<ReservationDate>) s.getAttribute("chosenDate");
		if(chosenDate == null) {
			chosenDate = new ArrayList<>();
		}
		chosenDate = null;
		s.setAttribute("chosenDate", chosenDate);
		m.addAttribute("hotel", hotel);
/*		m.addAttribute("datesJanuary", datesJanuary);
		m.addAttribute("datesFebruary", datesFebruary);*/
//		m.addAttribute("reservation", new ReservationDate());
		return "reservation/hotel_reservation_date";
	}
	
	//PET OPTIONS
	@GetMapping("/{petId}/pet")
	public String choosePet(Model m, @PathVariable long petId) {
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("chosenHotel");
/*		List<ReservationDate> datesJanuary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), this.monthRepository.findOne(1l));
		List<ReservationDate> datesFebruary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), this.monthRepository.findOne(2l));*/
		Pet pet = this.petRepository.findOne(petId);
		s.setAttribute("chosenPet", pet);
		m.addAttribute("hotel", hotel);
/*		m.addAttribute("datesJanuary", datesJanuary);
		m.addAttribute("datesFebruary", datesFebruary);*/
//		m.addAttribute("reservation", new ReservationDate());
		return "reservation/hotel_reservation_date";
	}
	
	@GetMapping("/pet/clear")
	public String clearPet(Model m) {
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("chosenHotel");
/*		List<ReservationDate> datesJanuary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), this.monthRepository.findOne(1l));
		List<ReservationDate> datesFebruary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), this.monthRepository.findOne(2l));*/
		Pet pet = null;
		s.setAttribute("chosenPet", pet);
		m.addAttribute("hotel", hotel);
/*		m.addAttribute("datesJanuary", datesJanuary);
		m.addAttribute("datesFebruary", datesFebruary);
		m.addAttribute("reservation", new ReservationDate());*/
		return "reservation/hotel_reservation_date";
	}
	
	@GetMapping("/showAll")
	public String showAll(Model m) {
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("chosenHotel");
/*		List<ReservationDate> datesJanuary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), this.monthRepository.findOne(1l));
		List<ReservationDate> datesFebruary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), this.monthRepository.findOne(2l));*/
		m.addAttribute("hotel", hotel);
/*		m.addAttribute("datesJanuary", datesJanuary);
		m.addAttribute("datesFebruary", datesFebruary);
		m.addAttribute("reservation", new ReservationDate());*/
		return "reservation/hotel_reservation_date";
	}
	
	//QUICK REGISTRATION WITH VALIDATION
	@PostMapping("/quickRegister")
	public String quickRegister(@RequestParam String petName, 
								@RequestParam String petCategory, 
								@RequestParam String userName, 
								@RequestParam String email, RedirectAttributes ra, 
								Model m) {
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("chosenHotel");
/*		List<ReservationDate> datesJanuary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), this.monthRepository.findOne(1l));
		List<ReservationDate> datesFebruary = this.reservationDateRepository.findAllByHotelIdAndMonth(hotel.getId(), this.monthRepository.findOne(2l));*/
		m.addAttribute("hotel", hotel);
/*		m.addAttribute("datesJanuary", datesJanuary);
		m.addAttribute("datesFebruary", datesFebruary);*/
		
		List<User> allUsers = this.userRepository.findAll();
		if(petName.isEmpty()) {
			String petNameError = "Pet name cannot be empty";
			m.addAttribute("petNameError", petNameError);
			return "reservation/hotel_reservation_date";
		}
		if(petCategory.isEmpty()) {
			String categoryError = "Pet category cannot be empty";
			m.addAttribute("categoryError", categoryError);
			return "reservation/hotel_reservation_date";
		}
		if(userName.isEmpty()) {
			String userNameError = "Owner name cannot be empty";
			m.addAttribute("userNameError", userNameError);
			return "reservation/hotel_reservation_date";
		}
		if(email.isEmpty()) {
			String mailError = "Email cannot be empty";
			m.addAttribute("mailError", mailError);
			return "reservation/hotel_reservation_date";
		}
		if(!email.contains("@")) {
			String mailError = "Please use valid email";
			m.addAttribute("mailError", mailError);
			return "reservation/hotel_reservation_date";
		}
		
		
		for (User user : allUsers) {
			if (user.getEmail().equals(email)) {
				String mailError = "This email is already used. Log in or use different one.";
				m.addAttribute("mailError", mailError);
				return "reservation/hotel_reservation_date";
			}
		}
		User user = new User();
		user.setEmail(email);
		user.setName(userName);
		Pet pet = new Pet();
		pet.setName(petName);
		pet.setCategory(petCategory);
		pet.setUser(user);
		this.userRepository.save(user);
		this.petRepository.save(pet);
		s.setAttribute("chosenPet", pet);
		return "reservation/hotel_reservation_date";
	}
	
	@GetMapping("/removePet/{dateId}/{petId}")
	public String removePet(@PathVariable long dateId, @PathVariable long petId) {
		ReservationDate date = this.reservationDateRepository.findOne(dateId);
		Pet pet = this.petRepository.findOne(petId);
		List<Pet> pets = date.getPet();
		pets.remove(pet);
		date.setPet(pets);
		this.reservationDateRepository.save(date);
		
		List<ReservationDate> rs = pet.getReservationDate();
		rs.remove(date);
		pet.setReservationDate(rs);
		this.petRepository.save(pet);
		return "redirect:/pet/show";
	}
	
	//CONFIRM ALL
	@GetMapping("/confirm")
	public String confirmReservation(Model m) {
		HttpSession s = SessionManager.session();
		Hotel hotel = (Hotel) s.getAttribute("chosenHotel");
		Pet pet = (Pet) s.getAttribute("chosenPet");
		List<ReservationDate> dates = (List<ReservationDate>) s.getAttribute("chosenDate");
		for (ReservationDate date : dates) {
			date.addPet(pet);
			date.setHotel(hotel);
			date.setPlacesLeft(date.getPlacesLeft()-1);
			this.reservationDateRepository.save(date);
		}
		m.addAttribute("hotel", hotel);
		m.addAttribute("pet", pet);
		m.addAttribute("dates", dates);
		m.addAttribute("datesNumber", dates.size());
		s.setAttribute("chosenHotel", null);
		s.setAttribute("chosenPet", null);
		s.setAttribute("chosenDate", null);
		return "reservation/confirmed";
	}
	
/*	@GetMapping("/{hotelId}/newHotelReservation/{dateId}/pet/{petId}")
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
	}*/
	
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
	
	@ModelAttribute("months")
	public List<Month> getMonths() {
		return this.monthRepository.findAll();
	}
}
