package com.liseh.bll.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "VERIFICATION_TOKEN")
@Data
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id") //nullable = false on the User to ensure data integrity and consistency in the VerificationToken<->User association
    private User user;

    private Date expiryDate;

    private Date calculateExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, EXPIRATION);
        return calendar.getTime();
    }
}
