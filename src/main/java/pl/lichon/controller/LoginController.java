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

import pl.lichon.bean.LoginData;
import pl.lichon.bean.SessionManager;
import pl.lichon.entity.User;
import pl.lichon.repository.UserRepository;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/login")
	public String loginUser(Model m) {
		m.addAttribute("loginData", new LoginData());
		return "login_user";
	}
	
	@GetMapping("/register")
	public String registerUser(Model m) {
		m.addAttribute("user", new User());
		return "register_user";
	}
	
	@PostMapping("/register")
	public String registerPost(@Valid @ModelAttribute User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "register_user";
		}
		this.userRepository.save(user);
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String loginPost(@Valid @ModelAttribute LoginData loginData, Model m, BindingResult bindingResult) {
		User u = this.userRepository.findOneByEmail(loginData.getEmail());
			if (u != null && u.isPasswordCorrect(loginData.getEmail())) {
				HttpSession s = SessionManager.session();
				s.setAttribute("user", u);
				return "login_user";
			}
			m.addAttribute("msg", "Enter valid data");
			return "login_user";
	}
	
	@GetMapping("/logout")
	public String logout(Model m) {
		HttpSession s = SessionManager.session();
		s.invalidate();
		return "login_user";
	}
}
