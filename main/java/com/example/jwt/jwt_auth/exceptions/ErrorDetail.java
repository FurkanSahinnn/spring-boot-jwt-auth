package com.example.jwt.jwt_auth.exceptions;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetail {

    private MessageType messageType;
    private String detail;

    public String getMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append(messageType.getErrorMessage());

        if (detail != null) {
            builder.append(" : " + detail);
        }

        return builder.toString();
    }

}
