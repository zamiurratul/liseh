package com.liseh.bll.model.common;

import lombok.Data;

@Data
public class GenericResponse {
    private String responseCode;
    private String description;
    private String content;

    public static GenericResponse mockSuccessResponse(){
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.responseCode = "OK";
        genericResponse.description = "Success";
        genericResponse.content = "The quick brown fox jumps over the lazy dog";
        return genericResponse;
    }
}
