package com.tools.exception;

import com.tools.error.ErrorCodes;
import com.tools.error.ICErrorEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ImageExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ICException.class)
    public ResponseEntity<Object> handleImageException(ICException ex, WebRequest webRequest) {
        ICErrorEntity error = null;
        String errorCode = ex.getMessage();
        String moreInfo = ex.getMoreInfo();
        switch (errorCode) {
            case ErrorCodes.UNKNOWN_EXCEPTION:
                error =
                        ICErrorEntity.builder()
                                .errorCode(Integer.valueOf(HttpStatus.NOT_FOUND.value()))
                                .userMessage(errorCode)
                                .build();
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            default:
                error =
                        ICErrorEntity.builder()
                                .errorCode(
                                        Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                                .userMessage(moreInfo)
                                .build();
                return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
