package com.liseh.model.dto;

import com.liseh.model.common.BaseDto;
import lombok.Data;

@Data
public class PreRegistrationDto extends BaseDto {
    private String name;
    private String email;
}
