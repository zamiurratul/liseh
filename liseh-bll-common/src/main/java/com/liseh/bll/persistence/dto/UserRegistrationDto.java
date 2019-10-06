package com.liseh.bll.persistence.dto;

import com.liseh.bll.common.type.Gender;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class UserRegistrationDto {
    private String firstName;
    private String surName;
    private String mobileNumber;
    private String email;
    private String password;
    private String dateOfBirth;
    private Gender gender;
}
