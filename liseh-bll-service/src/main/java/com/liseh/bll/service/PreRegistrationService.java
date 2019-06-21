package com.liseh.bll.service;

import com.liseh.bll.model.common.GenericResponse;
import com.liseh.bll.model.dto.PreRegistrationDto;

public interface PreRegistrationService {
    GenericResponse preRegister(PreRegistrationDto preRegistrationDto);
}
