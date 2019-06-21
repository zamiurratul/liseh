package com.liseh.bll.rest.controller;

import com.liseh.bll.model.common.GenericResponse;
import com.liseh.bll.rest.interfaces.ServiceCaller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    public ResponseEntity<GenericResponse> callService(ServiceCaller serviceCaller){
        GenericResponse response;
        try {
            response = serviceCaller.callService();
        } catch (Exception e) {
            response = GenericResponse.createResponseFromException(e);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
