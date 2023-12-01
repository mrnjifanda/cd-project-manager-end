package com.njifanda.pm.Web.Validators;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.njifanda.pm.Models.Project;

@Component
public class ProjectValidator implements Validator {

    @Override
    public boolean supports(Class<?> c) {
        return Project.class.equals(c);
    }

    @Override
    public void validate(Object object, Errors errors) {

        Project project = (Project) object;
		Date currentDate = new Date();
		int compareDate = currentDate.compareTo(project.getDueDate());
		if (compareDate == 1) {

			errors.rejectValue("dueDate", "Incorrect", "Date must not be in the past.");
		}
    }
}
