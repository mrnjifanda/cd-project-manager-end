package com.njifanda.pm.Web.Controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.njifanda.pm.Models.Team;
import com.njifanda.pm.Models.User;
import com.njifanda.pm.Services.TeamService;
import com.njifanda.pm.Services.UserService;

@Controller
public class DashboardController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TeamService teamService;

	@GetMapping("/dashboard")
	public String dashboard(Principal principal, Model model) {

		User connectedUser = this.userService.getAuthUser(principal);
		List<Team> teams = this.teamService.findAllNoUser(connectedUser);
		List<Team> connectedUserTeams =  this.teamService.findAllWithUser(connectedUser);
		model.addAttribute("connectedUser", connectedUser);
		model.addAttribute("teams", teams);
		model.addAttribute("connectedUserTeams", connectedUserTeams);
		return "dashboard";
	}
}
