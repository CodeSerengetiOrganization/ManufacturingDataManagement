package com.mytech.exception.apiexception;

import lombok.Builder;
import lombok.Data;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-08-09
 * @description :
 */
@Data

public class ApiTestException extends RuntimeException {
    private Long code;
    //    private String message;
//    private Throwable e;
    public ApiTestException() {
    }

    public ApiTestException(String message) {
        super(message);
    }
    public ApiTestException(Throwable cause) {
        super(cause);
    }
    public ApiTestException(String message, Throwable cause) {
        super(message, cause);
    }
    public ApiTestException(Long code, String message){
        super(message);
        this.code=code;
    }
    @Builder
    public ApiTestException(Long code, String message,Throwable cause){
        super(message,cause);
        this.code=code;
    }
    protected ApiTestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
