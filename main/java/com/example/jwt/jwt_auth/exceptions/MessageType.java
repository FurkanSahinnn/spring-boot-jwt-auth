package com.example.jwt.jwt_auth.exceptions;

import lombok.Getter;

@Getter
public enum MessageType {

    GENERAL_EXCEPTION("1000", "Global Error."),
    NO_RECORD_EXIST("1001", "The user record is not found."),
    DUPLICATE_USERNAME("1002", "Username already exists."),
    DUPLICATE_EMAIL("1003", "Email already exists."),
    INVALID_PASSWORD("1004", "Invalid password format."),
    USER_ALREADY_DELETED("1005", "User is already deleted.");


    private final String errorCode;
    private final String errorMessage;

    MessageType(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
