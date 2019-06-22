package com.liseh.bll.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liseh.bll.constants.ResponseCode;
import com.liseh.bll.exception.BaseException;
import com.liseh.bll.model.common.GenericResponse;
import com.liseh.bll.model.dto.PreRegistrationDto;
import com.liseh.bll.model.dto.RegistrationDto;
import com.liseh.bll.producer.RegistrationProducer;
import com.liseh.bll.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private RegistrationProducer registrationProducer;

    @Override
    public GenericResponse register(RegistrationDto registrationDto) {
        GenericResponse response = createGenericResponse();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContent(registrationProducer.registration(objectMapper.writeValueAsString(registrationDto)));
        } catch (Exception e) {
            throw new BaseException(e.getMessage());
        }

        return response;
    }

    @Override
    public GenericResponse preRegister(PreRegistrationDto preRegistrationDto) {
        GenericResponse response = createGenericResponse();

        try {
            response.setContent(registrationProducer.preRegistration(String.valueOf(preRegistrationDto)));
        } catch (Exception e) {
            throw new BaseException(e.getMessage());
        }

        return response;
    }

    private GenericResponse createGenericResponse(){
        GenericResponse response = new GenericResponse();
        response.setResponseCode(ResponseCode.SUCCESS);
        response.setDescription("Request Success");
        return response;
    }
}
