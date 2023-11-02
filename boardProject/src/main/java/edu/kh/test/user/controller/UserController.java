package edu.kh.test.user.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;
import edu.kh.test.user.model.service.UserService;
import edu.kh.test.user.model.vo.User;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	
	@PostMapping("/test/search")
	public String search(int input, User user, Model model){
		
		System.out.println("페이지 들어옴");
		System.out.println(input);
		
		User info= service.search(input);
		
		if(info != null) {
			model.addAttribute("user",info);
			return "searchSuccess";
		}else {
			return "searchFail";
		}
		
	}
	
	

}
