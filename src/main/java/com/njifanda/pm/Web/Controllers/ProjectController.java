package com.njifanda.pm.Web.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njifanda.pm.Models.Project;
import com.njifanda.pm.Models.Task;
import com.njifanda.pm.Models.User;
import com.njifanda.pm.Services.ProjectService;
import com.njifanda.pm.Services.UserService;
import com.njifanda.pm.Web.Validators.ProjectValidator;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectValidator projectValidator;
	
	@GetMapping("/new")
	public String create(@ModelAttribute("project") Project project) {
		
		return "projects/create";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(
			@PathVariable(name="id", required=true) Long id,
			Model model,
			Principal principal
	) {
	
		Project project = this.projectService.findById(id);
		User connectedUser = this.userService.getAuthUser(principal);
		User projectCreator = project.getUser();
		if (project == null || projectCreator.getId() != connectedUser.getId()) {

			return "redirect:/dashboard";
		}

		model.addAttribute("project", project);
		return "projects/edit";
	}
	
	@GetMapping("/{id}")
	public String project(
			@PathVariable(name="id", required=true) Long id,
			Model model
	) {
	
		Project project = this.projectService.findById(id);
		if (project == null) {

			return "redirect:/dashboard";
		}

		model.addAttribute("project", project);
		return "projects/project";
	}
	
	@GetMapping("/{id}/tasks")
	public String tasks(
			@PathVariable(name="id", required=true) Long id,
			@ModelAttribute("task") Task task,
			Model model
	) {

		Project project = this.projectService.findById(id);
		if (project == null) {

			return "redirect:/dashboard?error=true&message=Project with id: " + id + " not found";
		}

		model.addAttribute("project", project);
		return "projects/tasks";
	}
	
	@PostMapping("/new")
	public String createPost(
			@Valid @ModelAttribute("project") Project project,
    		BindingResult result,
    		Principal principal
	) {

    	this.projectValidator.validate(project, result);
    	if (result.hasErrors()) {
  
            return "projects/create";
        }

    	User connectedUser = this.userService.getAuthUser(principal);
    	this.projectService.create(project, connectedUser);
		return "redirect:/dashboard";
	}
	
	@PostMapping("/remove/{id}")
	public String deletePost(
			@PathVariable(name="id", required=true) Long id,
    		Principal principal
	) {

		Project project = this.projectService.findById(id);
		User connectedUser = this.userService.getAuthUser(principal);
		User projectCreator = project.getUser();
		if (project == null || projectCreator.getId() != connectedUser.getId()) {

			return "redirect:/dashboard";
		}

    	this.projectService.remove(project);
		return "redirect:/dashboard";
	}
	
	@PostMapping("/edit/{id}")
	public String editPost(
			@PathVariable(name="id", required=true) Long id,
			@Valid @ModelAttribute("project") Project project,
    		BindingResult result,
    		Principal principal
	) {

    	this.projectValidator.validate(project, result);
    	if (result.hasErrors()) {
  
            return "projects/edit";
        }

    	this.projectService.update(project);
		return "redirect:/dashboard";
	}
	
	@PostMapping("/{id}/tasks")
	public String taskPost(
			@PathVariable(name="id", required=true) Long id,
			@Valid @ModelAttribute("task") Task task,
			BindingResult result,
			Model model,
			Principal principal
	) {

		String returnUrl = "redirect:/projects/" + id + "/tasks";
    	if (result.hasErrors()) {
  
            return returnUrl.concat("?error=true");
        }

    	User user = this.userService.getAuthUser(principal);
    	this.projectService.addTask(id, task, user);    	
		return returnUrl;
	}

}
