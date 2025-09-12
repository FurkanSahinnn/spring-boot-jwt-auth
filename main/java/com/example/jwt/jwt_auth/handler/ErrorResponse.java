package com.example.jwt.jwt_auth.handler;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorResponse<E> {
    private String source;
    private String path;
    private Date timestamp;
    private E message;
}
