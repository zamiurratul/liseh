package com.liseh.bll.rest.controller;

import com.liseh.bll.common.GenericResponse;
import com.liseh.bll.event.AppEventManager;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.version}/test")
public class TestController extends BaseRestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> test() {
        return super.callService(() -> System.out.println("Test Controller !!!"));
    }
}
