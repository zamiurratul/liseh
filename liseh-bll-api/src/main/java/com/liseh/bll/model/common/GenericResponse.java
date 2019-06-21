package com.liseh.bll.model.common;

import lombok.Data;

@Data
public class GenericResponse {
    private String responseCode;
    private String description;
    private String content;

    public static GenericResponse mockSuccessResponse(){
        GenericResponse response = new GenericResponse();
        response.responseCode = ResponseCode.SUCCESS;
        response.description = "Success";
        response.content = "The request was successfully executed";
        return response;
    }

    public static GenericResponse createResponseFromException(Exception e){
        GenericResponse response = new GenericResponse();
        response.responseCode = ResponseCode.FAILED;
        response.description = "Failed";
        response.content = "The request failed";
        return response;
    }
}
