package com.liseh.bll.rest.controller;

import com.liseh.bll.common.GenericResponse;
import com.liseh.bll.persistence.dto.UserRegistrationDto;
import com.liseh.bll.persistence.dto.UserStatusDto;
import com.liseh.bll.persistence.entity.UserStatus;
import com.liseh.bll.service.UserRegistrationService;
import com.liseh.bll.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "${api.version}/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends BaseRestController {
    private final UserRegistrationService userRegistrationService;
    private final UserStatusService userStatusService;

    @Autowired
    public UserController(UserRegistrationService userRegistrationService, UserStatusService userStatusService) {
        this.userRegistrationService = userRegistrationService;
        this.userStatusService = userStatusService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> registration(@RequestBody UserRegistrationDto userRegistrationDto) {
        return super.callService(() -> userRegistrationService.registerNewUser(userRegistrationDto));
    }

    @RequestMapping(value = "/post_status", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> postStatus(@RequestBody UserStatusDto userStatusDto) {
        return super.callService(() -> userStatusService.postStatus(userStatusDto));
    }

    @RequestMapping(value = "/get_all_status", method = RequestMethod.GET)
    public ResponseEntity<List<UserStatus>> getAllStatus() {
        return new ResponseEntity<>(userStatusService.getAllStatus(), HttpStatus.OK);
    }
}
