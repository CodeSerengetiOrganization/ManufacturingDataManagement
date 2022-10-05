package com.mytech.exception.apiexception;

import com.mytech.exception.serviceexception.FolderNotFoundException;
//import jdk.jfr.DataAmount;
import lombok.Data;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-08-30
 * @description :
 */

public enum  ApiExceptionConfigEnum {
    NotEligbileToDeleteErrorCode("NotEligbileToDeleteErrorCode",1001L),
    NotEligbileToUpdateErrorCode("NotEligbileToUpdateErrorCode",1002L),
    OrderNotExistErrorCode("OrderNotExistErrorCode",1003L),
    InputDTOMapEmptyExceptionErrorCode("InputDTOMapEmptyExceptionErrorCode",1004L),
    FolderNotFoundErrorCode("FolderNotFoundErrorCode",1005L);

    String errName;//do NOT use private modifier, otherwise cannot access it.
    Long errCode;

    ApiExceptionConfigEnum(String errorName,Long errorCode){
        this.errCode=errorCode;
        this.errName=errorName;
    }
    public Long getCode(){
        return errCode;
    }
}

class TestApiExceptionConfigEnum{
    public static void main(String[] args) {
        String errName = ApiExceptionConfigEnum.OrderNotExistErrorCode.errName;
        Long errCode = ApiExceptionConfigEnum.OrderNotExistErrorCode.errCode;
        System.out.println("errName:"+errName);
        System.out.println("errCode:"+errCode);
    }
}