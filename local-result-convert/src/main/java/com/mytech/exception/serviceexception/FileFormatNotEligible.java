package com.mytech.exception.serviceexception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-08-07
 * @description :
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FileFormatNotEligible extends ServiceException{
    public FileFormatNotEligible(String message) {
        super(message);
    }
    public FileFormatNotEligible(Throwable cause) {
        super(cause);
    }
    public FileFormatNotEligible(String message, Throwable cause) {
        super(message, cause);
    }
    public FileFormatNotEligible(Long code, String message){
        super(code,message);
    }
    @Builder
    public FileFormatNotEligible(Long code, String message,Throwable cause){
        super(code,message,cause);
    }
}
