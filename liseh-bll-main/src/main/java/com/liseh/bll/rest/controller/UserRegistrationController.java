package com.liseh.bll.rest.controller;

import com.liseh.bll.common.GenericResponse;
import com.liseh.bll.event.AppEventManager;
import com.liseh.bll.persistence.dto.UserRegistrationDto;
import com.liseh.bll.service.UserService;
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
    private final UserService userService;

    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<GenericResponse> registration(@RequestBody UserRegistrationDto userRegistrationDto) {
        AppEventManager.fire("TEST_CUSTOM_EVENT");
        return super.callService(() -> userService.registerNewUser(userRegistrationDto));
    }
}
