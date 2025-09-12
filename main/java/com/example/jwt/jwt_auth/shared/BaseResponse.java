package com.example.jwt.jwt_auth.shared;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private boolean isSuccess;

    private String errorMessage;

    private T data;

    public static <T> BaseResponse<T> ok(T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setData(data);
        baseResponse.setSuccess(true);
        baseResponse.setErrorMessage(null);
        return baseResponse;
    }

    public static <T> BaseResponse<T> error(String errorMessage) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setData(null);
        baseResponse.setErrorMessage(errorMessage);
        baseResponse.setSuccess(false);
        return baseResponse;
    }
}
