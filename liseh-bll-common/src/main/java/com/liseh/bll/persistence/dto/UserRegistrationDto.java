package com.liseh.bll.persistence.dto;

import com.liseh.bll.common.type.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRegistrationDto {
    private String username;
    private String firstName;
    private String surName;
    private String mobileNumber;
    private String email;
    private String password;
    @ApiModelProperty(example = "11-JAN-1990")
    private String dateOfBirth;
    private Gender gender;
}
