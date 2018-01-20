package com.teamknp.hotel.entity;

import io.springlets.format.EntityFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "`user`")
@EntityFormat("#{username}")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NotNull
    String username;
    @NotNull
    @Size(min = 6, max = 30)
    String password;
}
