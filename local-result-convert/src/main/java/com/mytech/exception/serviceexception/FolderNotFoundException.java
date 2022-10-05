package com.mytech.exception.serviceexception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.experimental.Tolerate;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-08-07
 * @description :
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FolderNotFoundException extends ServiceException {
    public FolderNotFoundException(String message) {
        super(message);
    }
    public FolderNotFoundException(Throwable cause) {
        super(cause);
    }
    public FolderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public FolderNotFoundException(Long code, String message){
        super(code,message);
    }
    @Builder
    public FolderNotFoundException(Long code, String message,Throwable cause){
        super(code,message,cause);
    }

}//class
