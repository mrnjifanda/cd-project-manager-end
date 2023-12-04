package com.njifanda.pm.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njifanda.pm.Models.Project;
import com.njifanda.pm.Models.Team;
import com.njifanda.pm.Models.User;
import com.njifanda.pm.Repositories.TeamRepository;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;
	
	public Team create(Project project) {
		
		Team team = new Team(project);
		return this.teamRepository.save(team);
	}
	
	public List<Team> findAll() {
		return this.teamRepository.findAll();
	}
	
	public List<Team> findAllNoUser(User user) {

		List<Team> teams = this.findAll();
		ArrayList<Team> trueTeams = new ArrayList<Team>();
		for(Team team : teams) {
			if (team.getProject().getUser().getId() != user.getId() && !team.getUsers().contains(user)) {
				trueTeams.add(team);
			}
		}
		
		return trueTeams;
	}
	
	public List<Team> findAllWithUser(User user) {

		List<Team> teams = this.findAll();
		ArrayList<Team> trueTeams = new ArrayList<Team>();
		for(Team team : teams) {
			if (team.getProject().getUser().getId() == user.getId() || team.getUsers().contains(user)) {
				trueTeams.add(team);
			}
		}
		
		return trueTeams;
	}
	
	public String join(Long team_id, User user) {
		
		// Check if team exits
		Optional<Team> findTeam = this.teamRepository.findById(team_id);
		if (!findTeam.isPresent()) {
			return "Team with id: " + team_id + " not found";
		}

		// Check if user is not creator of project
		Team team = findTeam.get();
		if (team.getProject().getUser().getId() == user.getId()) {
			return "This not avalaible the join this team";
		}
		
		// Check if user is not already exist in the team
		if (team.getUsers().contains(user)) {
			return "This user already join this team";
		}
		
		// Add user in the team
		team.addUserInTeam(user);
		this.teamRepository.save(team);
		return null;
	}
	
	public String leave(Long team_id, User user) {
		
		// Check if team exits
		Optional<Team> findTeam = this.teamRepository.findById(team_id);
		if (!findTeam.isPresent()) {
			return "Team with id: " + team_id + " not found";
		}

		// Check if user is not creator of project
		Team team = findTeam.get();
		if (team.getProject().getUser().getId() == user.getId()) {
			return "This not avalaible the join this team";
		}
		
		// Check if user is not already exist in the team
		if (!team.getUsers().contains(user)) {
			return "This user no join this team";
		}
		
		// Add user in the team
		team.removeUserInTeam(user);
		this.teamRepository.save(team);
		return null;
	}
}
