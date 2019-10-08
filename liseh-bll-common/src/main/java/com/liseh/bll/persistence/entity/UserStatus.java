package com.liseh.bll.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "USER_STATUS")
public class UserStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usm_seq_gen")
    @SequenceGenerator(name = "usm_seq_gen", allocationSize = 25, sequenceName = "user_status_seq")
    private Long id;

    @Column(length = 32)
    private String userId;

    @Column(length = 32)
    private String statusId;

    @Lob
    @Column(length = 512)
    private String message;

    private Integer reactionCount;

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
