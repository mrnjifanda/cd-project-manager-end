package com.njifanda.pm.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.njifanda.pm.Models.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long>{
	List<Team> findAll();
}
