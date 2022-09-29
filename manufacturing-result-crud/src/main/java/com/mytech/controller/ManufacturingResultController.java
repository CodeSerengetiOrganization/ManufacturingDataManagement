package com.mytech.controller;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.domain.ManufacturingResult;
import com.mytech.dto.*;
import com.mytech.exception.apiexception.ApiTestException;
import com.mytech.savecommand.CommandFactory;
import com.mytech.savecommand.IManufacturingResultSaveCommand;
import com.mytech.savecommand.SaveCommandInvoker;
import com.mytech.service.ComplexResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-09-27
 * @description :
 */
@RestController
//@RequestMapping()
public class ManufacturingResultController {

    @Autowired
    SaveCommandInvoker saveCommandInvoker;
    @Autowired
    ComplexResultService complexResultService;
/*    @Autowired
    @Qualifier("complexResultServiceImpl")
    ManufacturingResultService resultService;*/

/*    @Autowired
    SaveCommandInvoker saveCommandInvoker;
    @Autowired
    CommandFactory commandFactory;*/

/*    @PostMapping("/manufacturingresult/v1")
    @ResponseStatus(HttpStatus.CREATED)
    public ManufacturingResultOutputDTO saveResult(@RequestBody SimpleManufacturingResultInputDTO inputDTO){
        Preconditions.checkNotNull(inputDTO,"inputDTO is null");
        ComplexManufacturingResult result = inputDTO.convertToManufacturingResult();
        ComplexManufacturingResult savedResult = (ComplexManufacturingResult) resultService.save(result);
        return ManufacturingResultOutputDTO.convertToDTO(savedResult);
    }*/

/*    @PostMapping("/manufacturingresult/v2")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String,ManufacturingResultOutputDTO> saveResult2(Map<String, SimpleManufacturingResultInputDTO> inputMap){
        Preconditions.checkNotNull(inputMap,"inputMap is null");
        Map<String,ManufacturingResultOutputDTO> returnMap= Maps.newHashMap();
        for (Map.Entry<String,SimpleManufacturingResultInputDTO> entries:inputMap.entrySet()) {
            String commandKey=entries.getKey();
            if (commandKey==null) throw new RuntimeException("key of inputMap from front end is null");// todo: update it to KeyNullException
            IManufacturingResultSaveCommand concreteCommand = commandFactory.getCommand(commandKey);
            if (null==concreteCommand) throw new RuntimeException("concrete Command is null");// todo: update ti to ConcreteCommandNullException
//            saveCommandInvoker.setSaveCommand(concreteCommand);
            SimpleManufacturingResultInputDTO inputDTO = entries.getValue();
            ComplexManufacturingResult result = inputDTO.convertToManufacturingResult();
            ComplexManufacturingResult savedResult = saveCommandInvoker.saveManufacturingResult2(concreteCommand,result);
            returnMap.put(commandKey,ManufacturingResultOutputDTO.convertToDTO(savedResult));
        }
        return returnMap;
    }*/
    @PostMapping("/manufacturingresult/v2")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ManufacturingResult> saveResult(@RequestBody Set<ComplexManufacturingResultInputDTO> inputDTOSet) throws InterruptedException {
        LocalTime start=LocalTime.now();
        Preconditions.checkNotNull(inputDTOSet,"inputMap is null");
        if(inputDTOSet.size()==0)  return null;//throw new RuntimeException("the inputSet is empty")；
        HashSet<ManufacturingResult> resultSet = Sets.newHashSet();
        for (ComplexManufacturingResultInputDTO inputDTO:inputDTOSet) {
            resultSet.add(inputDTO.convertToManufacturingResult());
        }
        Set<IManufacturingResultSaveCommand> commandSet = CommandFactory.getCommandSet(resultSet);
        LocalTime beforeSave=LocalTime.now();
        List<ManufacturingResult> savedResults = saveCommandInvoker.saveResult(commandSet);
        //mark time stamp
        LocalTime end=LocalTime.now();
        System.out.println("start:"+start+";beforeSave:"+beforeSave+";end:"+end);
//        long latency = end.to() - start.toNanoOfDay();
//        System.out.println("latency:"+latency);
        System.out.println("thread name;"+Thread.currentThread().getName()+"Controller:"+this.hashCode());
        Thread.sleep(10000);
        for (ManufacturingResult r:savedResults) {
            System.out.println(r);
        }
        return savedResults;
    }

    @PostMapping("/manufacturingresult/v3")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ManufacturingResult> saveSingleResult(@RequestBody ComplexManufacturingResultInputDTO inputDTO){
        LocalTime start=LocalTime.now();
        Preconditions.checkNotNull(inputDTO,"inputMap is null");

        ManufacturingResult resultObj = inputDTO.convertToManufacturingResult();
        IManufacturingResultSaveCommand command = CommandFactory.getCommand(resultObj);
        ArrayList<IManufacturingResultSaveCommand> commandList = Lists.newArrayList(command);
        List<ManufacturingResult> savedResults = saveCommandInvoker.saveResult(commandList);
        //mark time stamp
        LocalTime end=LocalTime.now();
        System.out.println("start:"+start+";"+"end:"+end);
        for (ManufacturingResult r:savedResults) {
            System.out.println(r);
        }
        return savedResults;
    }
    @PostMapping("/manufacturingresult/v4")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ManufacturingResult> saveResultsWithService(@RequestBody Set<ComplexManufacturingResultInputDTO> inputDTOSet){
        LocalTime start=LocalTime.now();
        Preconditions.checkNotNull(inputDTOSet,"inputMap is null");
        if(inputDTOSet.size()==0)  return null;//throw new RuntimeException("the inputSet is empty")；
        HashSet<ManufacturingResult> resultSet = Sets.newHashSet();
        for (ComplexManufacturingResultInputDTO inputDTO:inputDTOSet) {
            resultSet.add(inputDTO.convertToManufacturingResult());
        }

        LocalTime beforeSave=LocalTime.now();
        List<ManufacturingResult> savedResults = complexResultService.saveAll(resultSet);
        //mark time stamp
/*        LocalTime end=LocalTime.now();
        System.out.println("start:"+start+";beforeSave:"+beforeSave+";end:"+end);*/
//        long latency = end.to() - start.toNanoOfDay();
//        System.out.println("latency:"+latency);
//        for (ManufacturingResult r:savedResults) {
//            System.out.println(r);
//        }
        return savedResults;
    }

    @PostMapping("/manufacturingresult/v5")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ManufacturingResult> saveResultsOneByOneWithService(@RequestBody Set<ComplexManufacturingResultInputDTO> inputDTOSet){
        LocalTime start=LocalTime.now();
        Preconditions.checkNotNull(inputDTOSet,"inputMap is null");
        if(inputDTOSet.size()==0)  return null;//throw new RuntimeException("the inputSet is empty")；
        HashSet<ManufacturingResult> resultSet = Sets.newHashSet();
        for (ComplexManufacturingResultInputDTO inputDTO:inputDTOSet) {
            resultSet.add(inputDTO.convertToManufacturingResult());
        }

        LocalTime beforeSave=LocalTime.now();
        List<ManufacturingResult> savedResults = Lists.newArrayList();
        for(ManufacturingResult result:resultSet){
            ComplexManufacturingResult savedResult = complexResultService.save((ComplexManufacturingResult) result);
            savedResults.add(savedResult);
        }

        //mark time stamp
        LocalTime end=LocalTime.now();
        System.out.println("start:"+start+";beforeSave:"+beforeSave+";end:"+end);
/*        for (ManufacturingResult r:savedResults) {
            System.out.println(r);
        }*/
        return savedResults;
    }

    @PostMapping("/manufacturingresult/v6")
    @ResponseStatus(HttpStatus.CREATED)
    public ManufacturingResult saveSingleResultWithService(@RequestBody ComplexManufacturingResultInputDTO inputDTO){
        LocalTime start=LocalTime.now();
        Preconditions.checkNotNull(inputDTO,"inputMap is null");
        ManufacturingResult resultObj = inputDTO.convertToManufacturingResult();
        ComplexManufacturingResult savedResult = complexResultService.save((ComplexManufacturingResult) resultObj);
        //mark time stamp
        LocalTime end=LocalTime.now();
        System.out.println("start:"+start+";"+"end:"+end);
        System.out.println(savedResult);
        return savedResult;
    }
//    public int deleteResult()
    @GetMapping("/apiLoadTest")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String loadTestMethod(@RequestBody ComplexManufacturingResultInputDTO inputDTO){
        return "load test for API";
    }

    @PostMapping("/apiLoadTest/v2")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ManufacturingResult> loadTestMethodMultiResult(@RequestBody Set<ComplexManufacturingResultInputDTO> inputDTOSet){
        List<ManufacturingResult> results = LoadTestResult.resultList;
//        for (ManufacturingResult result:results) {
//            System.out.println(result);
//        }
        return results;

    }

    private static class LoadTestResult{
        public static List<ManufacturingResult> resultList=Lists.newArrayList();
        static{
            LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
            LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
            for (int i = 0; i <80 ; i++) {
                ComplexManufacturingResult complex = ComplexManufacturingResult.builder().barcode("complex1 product")
                        .productCode(1)
                        .featureType("clip")
                        .featureName("TP12")
                        .stationCode(1)
                        .stationChannelNo(1)
                        .testItem("TP12 height")
                        .result(1.234)
                        .operator("AliceTest")
                        .startTime(startTime)
                        .endTime(endTime)
                        .comment("created by Invoker test program with CommandFactory")
                        .build();
                complex.setBarcode("complex" + i);
                resultList.add(complex);
            }//for
        }//static
/*        private LoadTestResult(){
            LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
            LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
            for (int i = 0; i <80 ; i++) {
                ComplexManufacturingResult complex = ComplexManufacturingResult.builder().barcode("complex1 product")
                        .productCode(1)
                        .featureType("clip")
                        .featureName("TP12")
                        .stationCode(1)
                        .stationChannelNo(1)
                        .testItem("TP12 height")
                        .result(1.234)
                        .operator("AliceTest")
                        .startTime(startTime)
                        .endTime(endTime)
                        .comment("created by Invoker test program with CommandFactory")
                        .build();
                complex.setBarcode("complex"+i);
                resultList.add(complex);
            }
        }//constructor*/
/*        private static List<ManufacturingResult> getResults(){
            return resultList;
        }*/
    }//class LoadTestResult

    /**
     * this method is to test GlobalErrorHandler could catch this exception
     * @return NA
     */
    @GetMapping("/throwapiexception")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO throwErrorDtoForTest(){
        throw ApiTestException.builder()
                .code(99L)
                .message("Api Exception throw by controller method")
                .build();
    }

    /**
     * this method is to trigger a service to throw ServiceException, then test if GlobalErrorHandler could catch this exception
     */
    @GetMapping("/trigger_service_exception")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void triggerServiceException(){
        complexResultService.throwServiceException();
    }
}//ManufacturingResultController
