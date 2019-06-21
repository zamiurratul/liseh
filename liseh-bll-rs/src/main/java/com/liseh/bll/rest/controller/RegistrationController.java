package com.liseh.bll.rest.controller;

import com.liseh.bll.model.common.GenericResponse;
import com.liseh.bll.model.dto.PreRegistrationDto;
import com.liseh.bll.model.dto.RegistrationDto;
import com.liseh.bll.service.PreRegistrationService;
import com.liseh.bll.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationController extends BaseController {
    private PreRegistrationService preRegistrationService;
    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(PreRegistrationService preRegistrationService, RegistrationService registrationService) {
        this.preRegistrationService = preRegistrationService;
        this.registrationService = registrationService;
    }

    @RequestMapping(value = "/pre_registration", method = RequestMethod.POST)
    public ResponseEntity<GenericResponse> preRegistration(@RequestBody PreRegistrationDto preRegistrationDto) {
        return super.callService(() -> preRegistrationService.preRegister(preRegistrationDto));
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<GenericResponse> registration(@RequestBody RegistrationDto registrationDto) {
        return super.callService(() -> registrationService.register(registrationDto));
    }
}
