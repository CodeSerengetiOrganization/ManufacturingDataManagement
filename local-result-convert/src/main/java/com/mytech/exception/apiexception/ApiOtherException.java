package com.mytech.exception.apiexception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ApiOtherException extends ApiException{
    public ApiOtherException(String message) {
        super(message);
    }
    public ApiOtherException(Throwable cause) {
        super(cause);
    }
    public ApiOtherException(String message, Throwable cause) {
        super(message, cause);
    }
    public ApiOtherException(Long code, String message){
        super(code,message);
    }
    @Builder
    public ApiOtherException(Long code, String message,Throwable cause){
        super(code,message,cause);}
}
