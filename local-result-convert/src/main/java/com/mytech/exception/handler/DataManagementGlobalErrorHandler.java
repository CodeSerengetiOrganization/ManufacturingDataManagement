package com.mytech.exception.handler;

import com.mytech.dto.ErrorDTO;
import com.mytech.exception.apiexception.ApiException;
import com.mytech.exception.serviceexception.FolderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-08-08
 * @description :
 */
@ControllerAdvice(basePackages = "com.mytech")
public class DataManagementGlobalErrorHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(FolderNotFoundException.class)
    public ResponseEntity<Object> handleFolderNotFoundException(FolderNotFoundException exception, WebRequest request){
        ErrorDTO errorDTO=ErrorDTO.builder()
                .errorCode(9998L)
                .errorMessage("re-write by handler:"+exception.getMessage())
                .occurDateTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiTestException(ApiException exception, WebRequest request){
        ErrorDTO errorDTO=ErrorDTO.builder()
                .errorCode(exception.getCode())
                .errorMessage(exception.getMessage())
                .occurDateTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.NO_CONTENT);
    }
}
