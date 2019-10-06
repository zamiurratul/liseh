package com.liseh.bll.rest.controller;

import com.liseh.bll.common.GenericResponse;
import com.liseh.bll.rest.interfaces.ServiceCaller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseRestController {
    protected ResponseEntity<GenericResponse> callService(ServiceCaller serviceCaller){
        GenericResponse response = GenericResponse.createSuccessResponse();
        try {
            serviceCaller.callService();
        } catch (Exception ex) {
            response = GenericResponse.createResponseFromException(ex);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
