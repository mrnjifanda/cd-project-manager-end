package com.njifanda.pm.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njifanda.pm.Models.Project;
import com.njifanda.pm.Models.Task;
import com.njifanda.pm.Models.User;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	
	public Task create(Project project, Task task, User user) {
		
		task.setUser(user);
		task.setProject(project);
		return this.taskRepository.save(task);
	}

}
