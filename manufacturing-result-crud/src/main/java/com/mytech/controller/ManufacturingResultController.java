package com.mytech.controller;

import com.google.common.base.Preconditions;
import com.mytech.domain.ManufacturingResult;
import com.mytech.dto.ManufacturingResultInputDTO;
import com.mytech.dto.ManufacturingResultOutputDTO;
import com.mytech.service.ManufacturingResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-09-27
 * @description :
 */
@RestController
//@RequestMapping()
public class ManufacturingResultController {

    @Autowired
    ManufacturingResultServiceImpl resultService;

    @PostMapping("/manufacturingresult/v1")
    @ResponseStatus(HttpStatus.CREATED)
    public ManufacturingResultOutputDTO saveResult(@RequestBody ManufacturingResultInputDTO inputDTO){
        Preconditions.checkNotNull(inputDTO,"inputDTO is null");
        ManufacturingResult result = inputDTO.convertToManufacturingResult();
        ManufacturingResult savedResult = resultService.save(result);
        return ManufacturingResultOutputDTO.convertToDTO(savedResult);
    }

//    public int deleteResult()

}//ManufacturingResultController
