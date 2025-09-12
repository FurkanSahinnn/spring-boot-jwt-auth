package com.example.jwt.jwt_auth.shared;

public class RestBaseController {
    public <T> BaseResponse<T> ok(T data) {
        return BaseResponse.ok(data);
    }

    public <T> BaseResponse<T> error(String errorMessage) {
        return BaseResponse.error(errorMessage);
    }
}
