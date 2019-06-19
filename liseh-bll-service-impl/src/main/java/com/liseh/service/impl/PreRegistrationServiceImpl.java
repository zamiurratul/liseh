package com.liseh.service.impl;

import com.liseh.model.common.GenericResponse;
import com.liseh.model.dto.PreRegistrationDto;
import com.liseh.service.PreRegistrationService;
import org.springframework.stereotype.Service;

@Service
public class PreRegistrationServiceImpl implements PreRegistrationService {
    @Override
    public GenericResponse preRegistration(PreRegistrationDto preRegistrationDto) {
        return GenericResponse.mockSuccessResponse();
    }
}
