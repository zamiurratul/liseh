package com.liseh.bll.rest.controller;

import com.liseh.bll.common.GenericResponse;
import com.liseh.bll.persistence.dto.UserRegistrationDto;
import com.liseh.bll.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.version}/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRegistrationController extends BaseRestController {
    private final UserRegistrationService userRegistrationService;

    @Autowired
    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<GenericResponse> registration(@RequestBody UserRegistrationDto userRegistrationDto) {
        return super.callService(() -> userRegistrationService.registerNewUser(userRegistrationDto));
    }
}
