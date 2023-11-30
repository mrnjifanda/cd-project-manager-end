package com.njifanda.pm.Web.Validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.njifanda.pm.Models.User;
import com.njifanda.pm.Services.UserService;

@Component
public class UserValidator implements Validator {

	@Autowired
	UserService userService;

    @Override
    public boolean supports(Class<?> c) {
        return User.class.equals(c);
    }

    @Override
    public void validate(Object object, Errors errors) {

        User user = (User) object;
        if (!user.getConfirm().equals(user.getPassword())) {

            errors.rejectValue("confirm", "Match", "Password and Password Confirmation must match");
        }
        
        User findUser = userService.findByEmail(user.getEmail());
        if (findUser != null) {
        	errors.rejectValue("email", "Exist", "Email already exist");
        }
    }
}
