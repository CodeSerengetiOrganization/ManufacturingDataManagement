package com.mytech.exception.apiexception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ApiFolderNotFoundException extends ApiException{
    public ApiFolderNotFoundException(String message) {
        super(message);
    }
    public ApiFolderNotFoundException(Throwable cause) {
        super(cause);
    }
    public ApiFolderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public ApiFolderNotFoundException(Long code, String message){
        super(code,message);
    }
    @Builder
    public ApiFolderNotFoundException(Long code, String message,Throwable cause){
        super(code,message,cause);
    }
}
