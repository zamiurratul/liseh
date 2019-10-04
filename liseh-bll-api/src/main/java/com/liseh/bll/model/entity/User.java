package com.liseh.bll.model.entity;

import com.liseh.bll.model.common.type.Gender;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", allocationSize = 25, sequenceName = "user_seq_gen")
    private Long id;

    private String userIdentifier;
    private String firstName;
    private String surName;

    @Column(unique = true)
    private String mobileNumber;

    @Column(unique = true)
    private String email;
    private String password;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Boolean isVerified;
    private Boolean isActive;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @PrePersist
    public void onPrePersist() {
        created = new Date();
    }

    @PreUpdate
    public void onPreUpdate() {
        updated = new Date();
    }
}
