package com.njifanda.pm.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.njifanda.pm.Models.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{
	public List<Project> findAll();
}
