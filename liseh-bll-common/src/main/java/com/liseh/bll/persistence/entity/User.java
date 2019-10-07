package com.liseh.bll.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.liseh.bll.common.type.Gender;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", allocationSize = 25, sequenceName = "user_seq")
    private Long id;

    private String userIdentifier;

    private String firstName;

    private String surName;

    @Column(unique = true)
    private String mobileNumber;

    @Column(unique = true)
    private String email;

    @Column(length = 60)
    private String password;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Boolean isVerified;

    private Boolean isActive;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;


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
