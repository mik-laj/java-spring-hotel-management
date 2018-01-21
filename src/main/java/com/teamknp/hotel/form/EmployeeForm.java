package com.teamknp.hotel.form;

import com.teamknp.hotel.entity.Role;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class EmployeeForm {
    @Size(min=5)
    @NotNull
    String username;
    @Size(min=5)
    @NotNull
    String firstName;
    @Size(min=5)
    @NotNull
    String lastName;
    @Size(min=5)
    @NotNull
    String password;
    @Size(min=5)
    @NotNull
    String passwordConfirm;
    Set<Integer> roles;

    @Valid
    public boolean isPasswordMatch() {
        return password != null && password.equals(passwordConfirm);
    }
}
