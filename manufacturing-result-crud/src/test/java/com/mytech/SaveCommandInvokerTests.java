package com.mytech;

import com.google.common.collect.Sets;
import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.domain.ManufacturingResult;
import com.mytech.domain.SimpleManufacturingResult;
import com.mytech.savecommand.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-11-13
 * @description :
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SaveCommandInvokerTests {
    @Autowired
    SaveCommandInvoker invoker;
/*    @Mock
    SaveSimpleResultCommand simpleCommand;
    @Mock
    SaveComplexResultCommand complexCommand;*/

    SimpleManufacturingResult firstResult;
    SimpleManufacturingResult secondResult;
    SimpleManufacturingResult thirdResult;
    ComplexManufacturingResult complex1;
    ComplexManufacturingResult complex2;
    ComplexManufacturingResult complex3;
    SimpleManufacturingResult simple1;
    SimpleManufacturingResult simple2;

/*    @Test
    public void input_saveSimpleResultCommand_and_SimpleResult_should_return_saved_object(){

        SimpleManufacturingResult simpleResult=SimpleManufacturingResult.builder().build();
        BDDMockito.given(simpleCommand.execute(simpleCommand,simpleResult)).willReturn(simpleResult);

        SimpleManufacturingResult returnedResult = invoker.saveManufacturingResult2(simpleCommand, simpleResult);
        Assertions.assertEquals(simpleResult,returnedResult);
    }//

    @Test
    public void input_saveComplexResultCommand_and_ComplexResult_should_return_saved_object(){
        ComplexManufacturingResult complexManufacturingResult=ComplexManufacturingResult.builder().build();
        ComplexManufacturingResult returnedResult = invoker.saveManufacturingResult2(complexCommand, complexManufacturingResult);
        Assertions.assertEquals(complexManufacturingResult,returnedResult);
    }//

    @Test
    public void input_NullCommand_and_SimpleResult_should_throw_exception(){
        SimpleManufacturingResult simpleResult=SimpleManufacturingResult.builder().build();
        Assertions.assertThrows(RuntimeException.class,()->{invoker.saveManufacturingResult2(null,simpleResult);});
    }//

    @Test
    public void input_saveSimpleResultCommand_and_NullResult_should_throw_exception(){
        Assertions.assertThrows(RuntimeException.class,()->{invoker.saveManufacturingResult2(simpleCommand,null);});
    }//

    @Test
    public void input_saveSimpleResultCommand_and_ComplexResult_should_throw_exception(){
        ComplexManufacturingResult complexManufacturingResult=ComplexManufacturingResult.builder().build();
        Assertions.assertThrows(RuntimeException.class,()->{invoker.saveManufacturingResult2(simpleCommand,complexManufacturingResult);});
    }//*/

    public void new_version_given_SimpleManufacturingResult_should_save_into_database_manual_check(){
//        HashSet<IManufacturingResultSaveCommand> commandSet=Sets.newHashSet();
        LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
        firstResult = SimpleManufacturingResult.builder().barcode("first product")
                .productCode(1)
                .featureType("clip")
                .featureName("TP12")
                .stationCode(1)
                .stationChannelNo(1)
                .testItem("TP12 height")
                .result(true)
                .operator("AliceTest")
                .startTime(startTime)
                .endTime(endTime)
                .comment("created by service test program for saveAll")
                .build();
        secondResult = new SimpleManufacturingResult();
        BeanUtils.copyProperties(firstResult,secondResult);
        secondResult.setBarcode("second product");
        thirdResult=new SimpleManufacturingResult();
        BeanUtils.copyProperties(firstResult,thirdResult);
        thirdResult.setBarcode("third product");
        HashSet<SimpleManufacturingResult> resultHashSet = Sets.newHashSet(firstResult, secondResult, thirdResult);
        //as a caller,test method itself will assemble command
        for (ManufacturingResult result:resultHashSet) {
            System.out.println("result:"+result);

        }
//        HashSet<IManufacturingResultSaveCommand> commandSet=catigorizeManufacturingResult(resultHashSet);

        // invoker is called to do the rest
//        invoker.saveResult(commandSet);
    }
    @Test
    public void private_test_given_ManufacturingResult_Set_should_return_ICommand_Set(){
        LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
        firstResult = SimpleManufacturingResult.builder().barcode("first product")
                .productCode(1)
                .featureType("clip")
                .featureName("TP12")
                .stationCode(1)
                .stationChannelNo(1)
                .testItem("TP12 height")
                .result(true)
                .operator("AliceTest")
                .startTime(startTime)
                .endTime(endTime)
                .comment("created by service test program for saveAll")
                .build();
        secondResult = new SimpleManufacturingResult();
        BeanUtils.copyProperties(firstResult,secondResult);
        secondResult.setBarcode("second product");
        thirdResult=new SimpleManufacturingResult();
        BeanUtils.copyProperties(firstResult,thirdResult);
        thirdResult.setBarcode("third product");
        HashSet<ManufacturingResult> resultHashSet = Sets.newHashSet(firstResult, secondResult, thirdResult);
        //as a caller,test method itself will assemble command
        for (ManufacturingResult result:resultHashSet) {
            System.out.println("result:"+result);
        }
        HashSet<IManufacturingResultSaveCommand> commandSet=catigorizeManufacturingResult(resultHashSet);
        for (IManufacturingResultSaveCommand command:commandSet) {
            System.out.println("comand:"+command);
        }

    }

    private  HashSet<IManufacturingResultSaveCommand> catigorizeManufacturingResult(HashSet<ManufacturingResult> itResult){
        HashSet<IManufacturingResultSaveCommand> commandSet=Sets.newHashSet();
        for (ManufacturingResult result:itResult) {
            if(result instanceof ComplexManufacturingResult){
                commandSet.add(SaveComplexResultCommand.builder().complexResult((ComplexManufacturingResult) result).build());
            }else if(result instanceof  SimpleManufacturingResult){
                SaveSimpleResultCommand builtCommand = SaveSimpleResultCommand.builder().simpleResult((SimpleManufacturingResult) result).build();
                commandSet.add(builtCommand);
            }else{
                throw new RuntimeException("can not find any IManufacturingResult implement for this ManufacturingResult instance:"+result);
            }
        }

        return commandSet;
    }

    @Test
    public void given_SimpleManufacturingResult_Set_should_return_ICommand_Set_using_CommandFactory(){
        LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
        firstResult = SimpleManufacturingResult.builder().barcode("first product")
                .productCode(1)
                .featureType("clip")
                .featureName("TP12")
                .stationCode(1)
                .stationChannelNo(1)
                .testItem("TP12 height")
                .result(true)
                .operator("AliceTest")
                .startTime(startTime)
                .endTime(endTime)
                .comment("created by service test program for saveAll")
                .build();
        secondResult = new SimpleManufacturingResult();
        BeanUtils.copyProperties(firstResult,secondResult);
        secondResult.setBarcode("second product");
        thirdResult=new SimpleManufacturingResult();
        BeanUtils.copyProperties(firstResult,thirdResult);
        thirdResult.setBarcode("third product");
        HashSet<ManufacturingResult> resultHashSet = Sets.newHashSet(firstResult, secondResult, thirdResult);
        Set<IManufacturingResultSaveCommand> commandSet = CommandFactory.getCommandSet(resultHashSet);

//        HashSet<IManufacturingResultSaveCommand> commandSet=catigorizeManufacturingResult(resultHashSet);
        for (IManufacturingResultSaveCommand command:commandSet) {
            System.out.println("comand:"+command);
        }
        List<ManufacturingResult> manufacturingResults = invoker.saveResult(commandSet);
        System.out.println("return values:"+manufacturingResults);
    }

    @Test
    public void given_ComplexManufacturingResult_Set_should_return_ICommand_Set_using_CommandFactory(){
        LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
        complex1 = ComplexManufacturingResult.builder().barcode("complex1 product")
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
        SimpleManufacturingResult simple1 = SimpleManufacturingResult.builder().barcode("first product")
                .productCode(1)
                .featureType("clip")
                .featureName("TP12")
                .stationCode(1)
                .stationChannelNo(1)
                .testItem("TP12 height")
                .result(true)
                .operator("AliceTest")
                .startTime(startTime)
                .endTime(endTime)
                .comment("created by service test program for saveAll")
                .build();
        complex2 = new ComplexManufacturingResult();
        BeanUtils.copyProperties(complex1,complex2);
        complex2.setBarcode("complex2 product");
        complex3=new ComplexManufacturingResult();
        BeanUtils.copyProperties(complex1,complex3);
        complex3.setBarcode("complex3 product");
        HashSet<ManufacturingResult> resultHashSet = Sets.newHashSet(complex1, complex2, complex3);
        Set<IManufacturingResultSaveCommand> commandSet = CommandFactory.getCommandSet(resultHashSet);

        for (IManufacturingResultSaveCommand command:commandSet) {
            System.out.println("command:"+command);
        }
        List<ManufacturingResult> manufacturingResults = invoker.saveResult(commandSet);
        System.out.println("return values:"+manufacturingResults);
    }
    @Test
    public void given_SimpleManufacturingResult_and_ComplexManufacturingResult_Set_should_return_ICommand_Set_using_CommandFactory(){
        LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
        complex1 = ComplexManufacturingResult.builder().barcode("complex1 product")
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
        simple1 = SimpleManufacturingResult.builder().barcode("first product")
                .productCode(1)
                .featureType("clip")
                .featureName("TP12")
                .stationCode(1)
                .stationChannelNo(1)
                .testItem("TP12 height")
                .result(true)
                .operator("AliceTest")
                .startTime(startTime)
                .endTime(endTime)
                .comment("created by Invoker test program with CommandFactory")
                .build();
        complex2 = new ComplexManufacturingResult();
        BeanUtils.copyProperties(complex1,complex2);
        complex2.setBarcode("complex2 product");
        complex3=new ComplexManufacturingResult();
        BeanUtils.copyProperties(complex1,complex3);
        complex3.setBarcode("complex3 product");
        simple2=new SimpleManufacturingResult();
        BeanUtils.copyProperties(simple1, simple2);
        simple2.setResult(false);
        HashSet<ManufacturingResult> resultHashSet = Sets.newHashSet(simple1,simple2,complex1, complex2);
        for (ManufacturingResult result:resultHashSet) {
            System.out.println("result from HashSet:"+result);
        }
        Set<IManufacturingResultSaveCommand> commandSet = CommandFactory.getCommandSet(resultHashSet);

        for (IManufacturingResultSaveCommand command:commandSet) {
            System.out.println("command:"+command);
        }
        List<ManufacturingResult> manufacturingResults = invoker.saveResult(commandSet);
        System.out.println("return values:"+manufacturingResults);
    }
}//class
