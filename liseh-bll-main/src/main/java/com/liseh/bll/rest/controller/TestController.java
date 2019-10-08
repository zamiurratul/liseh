package com.liseh.bll.rest.controller;

import com.liseh.bll.common.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.version}/test")
public class TestController extends BaseRestController {

    @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> ping() {
        GenericResponse response = GenericResponse.createSuccessResponse();
        response.setDescription("Hello from Liseh System");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
