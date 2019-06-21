package com.liseh.bll.service;

import com.liseh.bll.model.common.GenericResponse;
import com.liseh.bll.model.dto.PreRegistrationDto;
import com.liseh.bll.model.dto.RegistrationDto;

public interface RegistrationService {
    GenericResponse register(RegistrationDto registrationDto);
    GenericResponse preRegister(PreRegistrationDto preRegistrationDto);
}
