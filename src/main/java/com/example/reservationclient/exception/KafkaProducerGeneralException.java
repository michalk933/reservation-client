package com.example.reservationclient.exception;

public class KafkaProducerGeneralException extends RuntimeException {

    public KafkaProducerGeneralException(String errorMessage){
        super(errorMessage);
    }

    public KafkaProducerGeneralException(String errorMessage, Throwable error){
        super(errorMessage, error);
    }

}
