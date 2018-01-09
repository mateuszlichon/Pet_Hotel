package pl.lichon.controller;

import java.util.List;

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

import pl.lichon.bean.SessionManager;
import pl.lichon.entity.Pet;
import pl.lichon.entity.User;
import pl.lichon.repository.PetRepository;

@Controller
@RequestMapping("pet")
public class PetController {

	@Autowired
	private PetRepository petRepository;

	@GetMapping("/register")
	public String registerPer(Model m) {
		m.addAttribute("pet", new Pet());
		return "pet/register_pet";
	}

	@PostMapping("/register")
	public String registerPost(@Valid @ModelAttribute Pet pet, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "pet/register_pet";
		}
		HttpSession s = SessionManager.session();
		User user = (User) s.getAttribute("user");
		pet.setUser(user);
		this.petRepository.save(pet);
		return "redirect:/";
	}

	@GetMapping("/show")
	public String showPets(Model m) {
		m.addAttribute("pet1", new Pet());
		return "pet/show_pet";
	}
	
	@PostMapping("/show")
	public String showPetsPost(@ModelAttribute Pet pet1, Model m) {
		pet1 = this.petRepository.findOne(pet1.getId());
		m.addAttribute("pet1", pet1);
		return "pet/show_pet";
	}

	@ModelAttribute("userPets")
	public List<Pet> getUserPets() {
		HttpSession s = SessionManager.session();
		User user = (User) s.getAttribute("user");
		return this.petRepository.findByUserId(user.getId());
	}
	
	@ModelAttribute("logedUser")
	public User getLogedUser() {
		HttpSession s = SessionManager.session();
		User user = (User) s.getAttribute("user");
		return user;
	}

}
