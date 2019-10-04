package com.liseh.bll.service;

import com.liseh.bll.model.dto.UserRegistrationDto;
import com.liseh.bll.model.entity.User;

public interface UserService {
    User registerNewUser(UserRegistrationDto userRegistrationDto);
}
