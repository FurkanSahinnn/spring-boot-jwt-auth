package com.example.jwt.jwt_auth.handler;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError<E> {
    private Integer httpStatus;
    private ErrorResponse<E> errorResponse;
}
