package com.tools.exception;

import com.tools.error.ErrorCodes;
import com.tools.error.ICErrorEntity;
import com.tools.error.ImageEntityBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@AllArgsConstructor
@ControllerAdvice
public class ImageExceptionHandler extends ResponseEntityExceptionHandler {
  ImageEntityBuilder builder;

  @ExceptionHandler(ICException.class)
  public ResponseEntity<Object> handleImageException(ICException ex, WebRequest webRequest) {
    ICErrorEntity error = null;
    String errorCode = ex.getMessage();
    String moreInfo = ex.getMoreInfo();
    switch (errorCode) {
      case ErrorCodes.IMAGE_FILE_EXTENSION_NOT_SUPPORTED:
        error = builder.getErrorEntity(ErrorCodes.IMAGE_FILE_EXTENSION_NOT_SUPPORTED);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
      default:
        error =
            ICErrorEntity.builder()
                .errorCode(Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .userMessage(moreInfo)
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
