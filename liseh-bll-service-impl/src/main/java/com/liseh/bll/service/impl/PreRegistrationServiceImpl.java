package com.liseh.bll.service.impl;

import com.liseh.bll.model.common.GenericResponse;
import com.liseh.bll.model.dto.PreRegistrationDto;
import com.liseh.bll.service.PreRegistrationService;
import org.springframework.stereotype.Service;

@Service
public class PreRegistrationServiceImpl implements PreRegistrationService {
    @Override
    public GenericResponse preRegistration(PreRegistrationDto preRegistrationDto) {
        return GenericResponse.mockSuccessResponse();
    }
}
