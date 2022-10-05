package com.mytech.exception.serviceexception;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-08-07
 * @description :
 */
//@Builder
@ToString(callSuper = true)
@Data
public abstract class ServiceException extends RuntimeException{
    private Long code;
//    private String message;
//    private Throwable e;
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }
    public ServiceException(Throwable cause) {
        super(cause);
    }
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public ServiceException(Long code, String message){
        super(message);
        this.code=code;
    }
    public ServiceException(Long code, String message,Throwable cause){
        super(message,cause);
        this.code=code;
    }
    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
