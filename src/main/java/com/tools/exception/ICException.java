package com.tools.exception;

import java.io.Serial;
import lombok.Getter;

/** Will be used for throwing ImageCompression Exceptions */
public class ICException extends Exception {
    @Serial static final long serialVersionUID = 7818325828146099195L;

    @Getter private String moreInfo;

    public ICException() {
        super();
    }

    public ICException(String message) {
        super(message);
    }

    public ICException(String message, Throwable cause) {
        super(message, cause);
    }

    public ICException(Throwable cause) {
        super(cause);
    }

    public ICException(String message, String moreInfo) {
        super(message);
        this.moreInfo = moreInfo;
    }
}
