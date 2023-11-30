package com.njifanda.pm.Services;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.njifanda.pm.Models.Role;
import com.njifanda.pm.Models.User;
import com.njifanda.pm.Repositories.RoleRepository;
import com.njifanda.pm.Repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
//	@Autowired
//	private Principal principal;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User getAuthUser(Principal principal) {

		String email = principal.getName();
		return this.findByEmail(email);
	}
	
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public void register(User user) {

    	String password = user.getPassword();
    	String HashedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(HashedPassword);
        
        List<Role> roles = this.roleRepository.findByName("ROLE_USER");
        user.setRoles(roles);
        userRepository.save(user);
    }

}
