package com.mytech.controller;



import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.mytech.domain.ManufacturingResult;
import com.mytech.exception.apiexception.ApiExceptionConfigEnum;
import com.mytech.exception.apiexception.ApiFolderNotFoundException;
import com.mytech.exception.apiexception.ApiOtherException;
import com.mytech.exception.serviceexception.FileFormatNotEligible;
import com.mytech.exception.serviceexception.FolderNotFoundException;
import com.mytech.service.LocalResultConvertService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-04-22
 * @description :This controller process font end request, convert the test files on local drive, parse into ManufacturingResult,
 * then save into database.
 */
@RestController
@Data
@AllArgsConstructor
public class LocalResultConvertController {

    @Autowired
    LocalResultConvertService localResultConvertService;
    @PostMapping("/convertLocalResult/v1")
    public List<ManufacturingResult> convertLocalResultTextFiles(@RequestBody String url) {
        Preconditions.checkNotNull(url,"URL is null");
        Preconditions.checkArgument(!url.isEmpty(),"URL is empty");
        List<ManufacturingResult> resultList=new ArrayList<>();
        try{
            System.out.println("url:"+url);
            resultList= localResultConvertService.convertAndSaveLocalTestFiles(url, "txt");

        }catch (FileFormatNotEligible e){
            //todo:: throw ApiException

        }catch(FolderNotFoundException e){
            throw ApiFolderNotFoundException.builder()
                    .code(ApiExceptionConfigEnum.FolderNotFoundErrorCode.getCode())
                    .cause(e.getCause())
                    .message(e.getMessage()).build();
        }catch (IOException e) {
            //todo: throw ApiException
            throw ApiOtherException.builder()
                    .code(998L)
                    .message(e.getMessage())
                    .cause(e).build();
        }catch(Exception e){
            throw ApiOtherException.builder()
                    .code(-1L)
                    .message(e.getMessage())
                    .cause(e).build();
        }
        return resultList;
    }//getFileNames

    @GetMapping("/v1")
    public String connectionTest(){
        return "Connection successfully";
    }//
}//class
