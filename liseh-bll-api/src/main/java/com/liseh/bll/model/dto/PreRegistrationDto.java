package com.liseh.bll.model.dto;

import com.liseh.bll.model.common.BaseDto;
import lombok.Data;

@Data
public class PreRegistrationDto extends BaseDto {
    private String name;
    private String email;
}
