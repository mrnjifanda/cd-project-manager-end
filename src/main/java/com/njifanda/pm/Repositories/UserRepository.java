package com.njifanda.pm.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.njifanda.pm.Models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findByEmail(String email);
}
