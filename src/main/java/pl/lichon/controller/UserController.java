package pl.lichon.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.lichon.bean.SessionManager;
import pl.lichon.entity.Pet;
import pl.lichon.entity.User;
import pl.lichon.repository.PetRepository;
import pl.lichon.repository.UserRepository;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PetRepository petRepository;
	

}
