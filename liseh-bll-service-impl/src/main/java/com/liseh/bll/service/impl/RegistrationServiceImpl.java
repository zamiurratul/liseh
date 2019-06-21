package com.liseh.bll.service.impl;

import com.liseh.bll.model.common.GenericResponse;
import com.liseh.bll.model.dto.RegistrationDto;
import com.liseh.bll.service.RegistrationService;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Override
    public GenericResponse register(RegistrationDto registrationDto) {
        return GenericResponse.mockSuccessResponse();
    }
}
