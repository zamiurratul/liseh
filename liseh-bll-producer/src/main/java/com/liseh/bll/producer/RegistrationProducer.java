package com.liseh.bll.producer;

import java.util.concurrent.ExecutionException;

public interface RegistrationProducer {
    //TODO return and method parameter will be changed to DTO
    String preRegistration(String content) throws InterruptedException, ExecutionException;
    String registration(String content) throws InterruptedException, ExecutionException;
}
