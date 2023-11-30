package com.njifanda.pm.Web.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.njifanda.pm.Models.User;
import com.njifanda.pm.Services.UserService;
import com.njifanda.pm.Web.Validators.UserValidator;

import jakarta.validation.Valid;

@Controller
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;

	@GetMapping("/")
	public String auth(
			@ModelAttribute("register") User register
	) {
		return "auth/auth";
	}
	
	@PostMapping("/register")
	public String register(
    		@Valid @ModelAttribute("register") User register,
    		BindingResult result
	) {

    	this.userValidator.validate(register, result);
    	if (result.hasErrors()) {
  
            return "auth/auth";
        }

    	this.userService.register(register);
		return "redirect:/dashboard";
	}
}
