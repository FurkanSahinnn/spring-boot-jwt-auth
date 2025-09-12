package com.example.jwt.jwt_auth.exceptions;


public class BaseException extends RuntimeException {
    public BaseException() {

    }
    /*
    * BaseException is the main custom exception class for the application.
    *
    * Example usage (inside a Service class):
    * throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
    * throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, null));
    *
     */
    public BaseException(ErrorDetail errorDetail) {
        super(errorDetail.getMessage());
    }
}
