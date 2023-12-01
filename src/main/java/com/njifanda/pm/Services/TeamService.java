package com.njifanda.pm.Services;

import java.util.ArrayList;
import java.util.List;

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

}
