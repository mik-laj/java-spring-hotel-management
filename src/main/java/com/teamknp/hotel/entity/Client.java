package com.teamknp.hotel.entity;

import io.springlets.format.EntityFormat;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString(exclude = "reservations")
@EntityFormat("##{id} #{firstName} #{lastName}")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String firstName;
    String lastName;
    @OneToMany(mappedBy="client", cascade=CascadeType.ALL)
    List<Reservation> reservations;
}
