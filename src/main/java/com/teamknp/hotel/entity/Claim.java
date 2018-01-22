package com.teamknp.hotel.entity;

import io.springlets.format.EntityFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@EntityFormat("##{id} #{firstName} #{lastName}")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String firstName;

    String lastName;

    String phone;

    @Enumerated(EnumType.STRING)
    Status status = Status.WAITING;

    @ManyToOne(fetch = FetchType.LAZY)
    Reservation reservation;

    @Column(length = 65535,columnDefinition="Text")
    String content;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    protected User createdBy;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    protected User lastModifiedBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = false)
    protected Date createdDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    protected Date lastModifiedDate;

    @OneToMany(mappedBy = "claim")
    List<Explanation> explanationList;

    public enum Status{
        POSITIVE,
        NEGATIVE,
        WAITING
    }
}
