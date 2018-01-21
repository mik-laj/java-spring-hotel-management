package com.teamknp.hotel;

import com.teamknp.hotel.form.EmployeeForm;
import com.teamknp.hotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;
    @Override
    public boolean supports(Class<?> aClass) {
        return EmployeeForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        EmployeeForm user = (EmployeeForm) o;

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

    }
}