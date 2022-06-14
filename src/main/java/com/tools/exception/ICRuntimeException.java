package com.tools.exception;

import lombok.Getter;

import java.io.Serial;

public class ICRuntimeException extends RuntimeException {
  @Serial static final long serialVersionUID = 7811325828146099195L;

  @Getter private String moreInfo;

  public ICRuntimeException() {
    super();
  }

  public ICRuntimeException(String message) {
    super(message);
  }

  public ICRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public ICRuntimeException(Throwable cause) {
    super(cause);
  }

  public ICRuntimeException(String message, String moreInfo) {
    super(message);
    this.moreInfo = moreInfo;
  }
}
