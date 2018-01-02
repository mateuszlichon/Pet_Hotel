package pl.lichon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.lichon.bean.LoginData;
import pl.lichon.entity.User;
import pl.lichon.repository.UserRepository;

@Controller
public class LoginController {

	private UserRepository userRepository;
	
	@GetMapping("/login")
	public String home(Model m) {
		m.addAttribute("user", new User());
		m.addAttribute("loginData", new LoginData());
		return "login_page";
	}
}
