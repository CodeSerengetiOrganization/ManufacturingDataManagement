package com.mytech.controller;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.dto.ManufacturingResultInputDTO;
import com.mytech.dto.ManufacturingResultOutputDTO;
import com.mytech.savecommand.CommandFactory;
import com.mytech.savecommand.IManufacturingResultSaveCommand;
import com.mytech.savecommand.SaveCommandInvoker;
import com.mytech.service.ManufacturingResultService;
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

/*    @Autowired
    @Qualifier("complexResultServiceImpl")
    ManufacturingResultService resultService;*/

/*    @Autowired
    SaveCommandInvoker saveCommandInvoker;
    @Autowired
    CommandFactory commandFactory;*/

/*    @PostMapping("/manufacturingresult/v1")
    @ResponseStatus(HttpStatus.CREATED)
    public ManufacturingResultOutputDTO saveResult(@RequestBody ManufacturingResultInputDTO inputDTO){
        Preconditions.checkNotNull(inputDTO,"inputDTO is null");
        ComplexManufacturingResult result = inputDTO.convertToManufacturingResult();
        ComplexManufacturingResult savedResult = (ComplexManufacturingResult) resultService.save(result);
        return ManufacturingResultOutputDTO.convertToDTO(savedResult);
    }*/

/*    @PostMapping("/manufacturingresult/v2")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String,ManufacturingResultOutputDTO> saveResult2(Map<String, ManufacturingResultInputDTO> inputMap){
        Preconditions.checkNotNull(inputMap,"inputMap is null");
        Map<String,ManufacturingResultOutputDTO> returnMap= Maps.newHashMap();
        for (Map.Entry<String,ManufacturingResultInputDTO> entries:inputMap.entrySet()) {
            String commandKey=entries.getKey();
            if (commandKey==null) throw new RuntimeException("key of inputMap from front end is null");// todo: update it to KeyNullException
            IManufacturingResultSaveCommand concreteCommand = commandFactory.getCommand(commandKey);
            if (null==concreteCommand) throw new RuntimeException("concrete Command is null");// todo: update ti to ConcreteCommandNullException
//            saveCommandInvoker.setSaveCommand(concreteCommand);
            ManufacturingResultInputDTO inputDTO = entries.getValue();
            ComplexManufacturingResult result = inputDTO.convertToManufacturingResult();
            ComplexManufacturingResult savedResult = saveCommandInvoker.saveManufacturingResult2(concreteCommand,result);
            returnMap.put(commandKey,ManufacturingResultOutputDTO.convertToDTO(savedResult));
        }
        return returnMap;
    }*/

//    public int deleteResult()

}//ManufacturingResultController
