package com.mytech.exception.apiexception;

import lombok.Builder;
import lombok.Data;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-08-09
 * @description :
 */
@Data

public class ApiException extends RuntimeException {
    private Long code;
    //    private String message;
//    private Throwable e;
    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }
    public ApiException(Throwable cause) {
        super(cause);
    }
    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
    public ApiException(Long code, String message){
        super(message);
        this.code=code;
    }
//    @Builder
    public ApiException(Long code, String message, Throwable cause){
        super(message,cause);
        this.code=code;
    }
    protected ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
