package pl.lichon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.lichon.entity.Month;
import pl.lichon.repository.MonthRepository;

@Controller
@RequestMapping("month")
public class MonthController {

	@Autowired
	private MonthRepository monthRepository;
	
	@GetMapping("/create")
	private String createMonths() {
		Month month = new Month(1, "January", 0);
		this.monthRepository.save(month);
		month = new Month(2, "February", 3);
		this.monthRepository.save(month);
		month = new Month(3, "March", 3);
		this.monthRepository.save(month);
		month = new Month(4, "April", 6);
		this.monthRepository.save(month);
		month = new Month(5, "May", 1);
		this.monthRepository.save(month);
		month = new Month(6, "June", 4);
		this.monthRepository.save(month);
		month = new Month(7, "July", 6);
		this.monthRepository.save(month);
		month = new Month(8, "August", 2);
		this.monthRepository.save(month);
		month = new Month(9, "September", 5);
		this.monthRepository.save(month);
		month = new Month(10, "October", 0);
		this.monthRepository.save(month);
		month = new Month(11, "November", 3);
		this.monthRepository.save(month);
		month = new Month(12, "December", 5);
		this.monthRepository.save(month);
		return "";
	}
}
