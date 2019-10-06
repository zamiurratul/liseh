package com.liseh.bll.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PRIVILEGE")
@Data
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles;

    public Privilege() {
        super();
    }

    public Privilege(final String name) {
        super();
        this.name = name;
    }
}
