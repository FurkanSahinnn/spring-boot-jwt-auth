package com.example.jwt.jwt_auth.handler;

import com.example.jwt.jwt_auth.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<ApiError<String>> handleBaseException(BaseException baseException, WebRequest webRequest) {
        return ResponseEntity.badRequest().body(createApiError(baseException.getMessage(), webRequest));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError<String>> handleValidationException(MethodArgumentNotValidException ex, WebRequest webRequest) {
        StringBuilder errorMessage = new StringBuilder();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMessage.append(error.getField())
                    .append(": ")
                    .append(error.getDefaultMessage())
                    .append("; ");
        });

        return ResponseEntity.badRequest().body(createApiError(errorMessage.toString(), webRequest));
    }

    private String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public <E> ApiError<E> createApiError(E messsage, WebRequest webRequest) {
        ApiError<E> apiError = new ApiError<>();
        apiError.setHttpStatus(HttpStatus.BAD_REQUEST.value());

        ErrorResponse<E> errorResponse = new ErrorResponse<>();
        errorResponse.setTimestamp(new Date());
        errorResponse.setSource(getHostName());
        errorResponse.setPath(webRequest.getDescription(false));
        errorResponse.setMessage(messsage);

        apiError.setErrorResponse(errorResponse);
        return apiError;
    }
}
