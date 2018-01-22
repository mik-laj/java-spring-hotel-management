package com.teamknp.hotel.entity;

import io.springlets.format.EntityFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "`user`")
@EntityFormat("#{firstName} #{lastName}")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;

    String firstName;
    String lastName;

    @NotNull
    String password;

    @ManyToMany
    Set<Role> roles = new HashSet<>();

    public boolean addRole(Role role) {
        return roles.add(role);
    }

    public boolean removeRole(Object o) {
        return roles.remove(o);
    }

    public void clearRoles() {
        roles.clear();
    }
}
