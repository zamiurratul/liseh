package com.liseh.rest.controller;

import com.liseh.exception.BaseException;
import com.liseh.model.common.GenericResponse;
import com.liseh.model.dto.PreRegistrationDto;
import com.liseh.service.PreRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PreRegistrationController {
    private PreRegistrationService preRegistrationService;

    @Autowired
    public PreRegistrationController(PreRegistrationService preRegistrationService) {
        this.preRegistrationService = preRegistrationService;
    }

    @RequestMapping(value = "/pre_registration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> preRegistration(@RequestBody PreRegistrationDto preRegistrationDto){
        try {
            GenericResponse response = preRegistrationService.preRegistration(preRegistrationDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new BaseException(e.getMessage());
        }
    }
}
