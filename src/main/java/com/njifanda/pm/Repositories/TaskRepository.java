package com.njifanda.pm.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.njifanda.pm.Models.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long>{}
