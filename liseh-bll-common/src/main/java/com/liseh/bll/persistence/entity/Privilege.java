package com.liseh.bll.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRIVILEGE")
@Data
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "privilege_seq_gen")
    @SequenceGenerator(name = "privilege_seq_gen", allocationSize = 25, sequenceName = "privilege_seq")
    private Long id;

    private String name;

    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @JsonBackReference
    @ManyToMany(mappedBy = "privileges", fetch = FetchType.LAZY)
    private List<Role> roles;

    public Privilege() {
        super();
    }

    public Privilege(final String name) {
        super();
        this.name = name;
    }

    public List<Role> getRoles() {
        if (roles == null)
            roles = new ArrayList<>();
        return roles;
    }
}
