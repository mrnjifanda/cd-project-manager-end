package com.njifanda.pm.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.njifanda.pm.Models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{
	List<Role> findByName(String roleName);
}