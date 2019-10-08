package com.liseh.bll.service;

import com.liseh.bll.persistence.dto.UserRegistrationDto;
import com.liseh.bll.persistence.entity.User;

public interface UserRegistrationService {
    User registerNewUser(UserRegistrationDto userRegistrationDto);
}
