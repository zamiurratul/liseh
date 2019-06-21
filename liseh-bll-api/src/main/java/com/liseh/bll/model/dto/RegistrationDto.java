package com.liseh.bll.model.dto;

import com.liseh.bll.model.common.BaseDto;
import lombok.Data;

@Data
public class RegistrationDto extends BaseDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
}
