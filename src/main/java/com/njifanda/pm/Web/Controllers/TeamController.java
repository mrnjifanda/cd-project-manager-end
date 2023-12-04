package com.njifanda.pm.Web.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njifanda.pm.Models.User;
import com.njifanda.pm.Services.TeamService;
import com.njifanda.pm.Services.UserService;

@Controller
@RequestMapping("/teams")
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/join/{team_id}")
	public String join(
			@PathVariable(name="team_id", required=true) Long team_id,
			Principal principal
	) {

		User connectedUser = userService.getAuthUser(principal);
		String joinTeam = this.teamService.join(team_id, connectedUser);
		String errorMessage = (joinTeam != null) ? "?error=true&message=" + joinTeam : "";
	
		return "redirect:/dashboard" + errorMessage;
	}
	
	@GetMapping("/leave/{team_id}")
	public String leave(
			@PathVariable(name="team_id", required=true) Long team_id,
			Principal principal
	) {

		User connectedUser = userService.getAuthUser(principal);
		String joinTeam = this.teamService.leave(team_id, connectedUser);
		String errorMessage = (joinTeam != null) ? "?error=true&message=" + joinTeam : "";
	
		return "redirect:/dashboard" + errorMessage;
	}
}
