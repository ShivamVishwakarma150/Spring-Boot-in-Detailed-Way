package com.app.shivam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller 
public class UserController {
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	//@GetMapping("/home")
	public String showHomePage() {
		return "UserHome";
	}
}
