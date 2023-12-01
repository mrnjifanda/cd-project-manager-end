package com.njifanda.pm.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njifanda.pm.Models.Project;
import com.njifanda.pm.Models.Team;
import com.njifanda.pm.Models.User;
import com.njifanda.pm.Repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private TeamService teamService;
	
	public List<Project> findAll() {
		return this.projectRepository.findAll();
	}
	
	public Project create(Project project, User user) {

		project.setUser(user);
		Project newProject = this.projectRepository.save(project);
		
		Team team = this.teamService.create(newProject);
		project.setTeam(team);

		return this.projectRepository.save(newProject);	
	}

	public Project findById(Long id) {

		Optional<Project> project = this.projectRepository.findById(id);
		return project.isEmpty() ? null : (Project) project.get();
	}
	
	public Project update(Project project) {

		Project oldProject = this.findById(project.getId());
		oldProject.setTitle(project.getTitle());
		oldProject.setDescription(project.getDescription());
		oldProject.setDueDate(project.getDueDate());
		return this.projectRepository.save(oldProject);
	}
	
	public void remove(Project project) {
		this.projectRepository.delete(project);
	}
}
