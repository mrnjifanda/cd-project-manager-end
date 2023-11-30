package com.njifanda.pm.Web.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.njifanda.pm.Models.User;
import com.njifanda.pm.Services.UserService;

@Controller
public class DashboardController {

	@Autowired
	private UserService userService;

	@GetMapping("/dashboard")
	public String dashboard(Principal principal, Model model) {

		User auth = this.userService.getAuthUser(principal);
		model.addAttribute("auth", auth);
		return "dashboard";
	}
}
