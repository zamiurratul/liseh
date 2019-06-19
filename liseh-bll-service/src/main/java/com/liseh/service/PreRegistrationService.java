package com.liseh.service;

import com.liseh.model.common.GenericResponse;
import com.liseh.model.dto.PreRegistrationDto;

public interface PreRegistrationService {
    GenericResponse preRegistration(PreRegistrationDto preRegistrationDto);
}
