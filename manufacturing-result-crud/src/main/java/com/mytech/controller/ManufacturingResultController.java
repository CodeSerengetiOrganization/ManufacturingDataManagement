package com.mytech.controller;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.mytech.domain.ManufacturingResult;
import com.mytech.dto.ManufacturingResultInputDTO;
import com.mytech.dto.ManufacturingResultOutputDTO;
import com.mytech.savecommand.CommandFactory;
import com.mytech.savecommand.IManufactCommand;
import com.mytech.savecommand.SaveCommandInvoker;
import com.mytech.service.ManufacturingResultService;
import com.mytech.service.ManufacturingResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-09-27
 * @description :
 */
@RestController
//@RequestMapping()
public class ManufacturingResultController {

    @Autowired
    @Qualifier("complexResultServiceImpl")
    ManufacturingResultService resultService;

    @Autowired
    SaveCommandInvoker saveCommandInvoker;
    @Autowired
    CommandFactory commandFactory;

    @PostMapping("/manufacturingresult/v1")
    @ResponseStatus(HttpStatus.CREATED)
    public ManufacturingResultOutputDTO saveResult(@RequestBody ManufacturingResultInputDTO inputDTO){
        Preconditions.checkNotNull(inputDTO,"inputDTO is null");
        ManufacturingResult result = inputDTO.convertToManufacturingResult();
        ManufacturingResult savedResult = resultService.save(result);
        return ManufacturingResultOutputDTO.convertToDTO(savedResult);
    }

    @PostMapping("/manufacturingresult/v2")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String,ManufacturingResultOutputDTO> saveResult2(Map<String, ManufacturingResultInputDTO> inputMap){
        Preconditions.checkNotNull(inputMap,"inputMap is null");
        Map<String,ManufacturingResultOutputDTO> returnMap= Maps.newHashMap();
        for (Map.Entry<String,ManufacturingResultInputDTO> entries:inputMap.entrySet()) {
            String commandKey=entries.getKey();
            if (commandKey==null) throw new RuntimeException("key of inputMap from front end is null");
            IManufactCommand concreatCommand = commandFactory.getCommand(commandKey);
//            saveCommandInvoker.setSaveCommand(concreatCommand);
            ManufacturingResultInputDTO inputDTO = entries.getValue();
            ManufacturingResult result = inputDTO.convertToManufacturingResult();
            ManufacturingResult savedResult = saveCommandInvoker.saveManufacturingResult2(concreatCommand,result);
            returnMap.put(commandKey,ManufacturingResultOutputDTO.convertToDTO(savedResult));
        }
        return returnMap;
    }

//    public int deleteResult()

}//ManufacturingResultController
