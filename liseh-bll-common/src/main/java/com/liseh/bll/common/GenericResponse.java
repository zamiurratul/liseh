package com.liseh.bll.common;

import com.liseh.bll.constant.ResponseCode;
import lombok.Data;

@Data
public class GenericResponse {
    private String responseCode;
    private String description;
    private String content;
    private Boolean isError;
    private String errorDetails;

    public static GenericResponse createSuccessResponse(){
        GenericResponse response = new GenericResponse();
        response.responseCode = ResponseCode.SUCCESS;
        response.setIsError(false);
        return response;
    }

    public static GenericResponse createResponseFromException(Exception ex){
        GenericResponse response = new GenericResponse();
        response.responseCode = ResponseCode.FAILED;
        response.setIsError(true);
        response.setErrorDetails(ex.getMessage());
        return response;
    }
}
