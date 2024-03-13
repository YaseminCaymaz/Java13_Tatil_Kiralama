package com.yasemin.exception;

import lombok.Getter;

@Getter
public class TatilKiralamaException extends RuntimeException{

    private final ErrorType errorType;
    public TatilKiralamaException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public TatilKiralamaException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }

}
