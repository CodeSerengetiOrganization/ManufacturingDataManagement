package com.mytech;

import com.google.common.collect.Sets;
import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.savecommand.CommandFactory;
import com.mytech.savecommand.IManufacturingResultSaveCommand;
import com.mytech.savecommand.SaveCommandInvoker;
import com.mytech.savecommand.SaveComplexResultCommand;
import com.mytech.service.ManufacturingResultServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashSet;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-11-07
 * @description :
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SaveComplexResultCommandTests {
    @InjectMocks
    SaveComplexResultCommand saveComplexResultCommand;
    @Mock
    ManufacturingResultServiceImpl serviceImpl;
//    @Autowired
//    SaveComplexResultCommand realComplexCommand;
    ComplexManufacturingResult firstResult;
    ComplexManufacturingResult secondResult;
    ComplexManufacturingResult thirdResult;
    @Autowired
    CommandFactory commandFactory;
    @Autowired
    SaveCommandInvoker commandInvoker;

/*    @Test
    public void input_complex_manufacturing_result_should_pass(){
        LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
        ComplexManufacturingResult complexResult = ComplexManufacturingResult.builder().barcode("correct complex result")
                .productCode(1)
                .featureType("clip")
                .featureName("TP12")
                .stationCode(1)
                .stationChannelNo(1)
                .testItem("TP12 height")
                .result(4.011)
                .operator("AliceTest")
                .startTime(startTime)
                .endTime(endTime)
                .comment("created by SaveComplexResultCommandTests test program")
                .build();
        BDDMockito.given(saveComplexResultCommand.execute(saveComplexResultCommand,complexResult)).willReturn(complexResult);
        Assertions.assertTrue(complexResult.equals(saveComplexResultCommand.execute(saveComplexResultCommand,complexResult)),"returned object not equals to orginal one");

    }*/

/*    @Test
    public void input_manufacturing_result_with_null_should_throw_exception(){
        ComplexManufacturingResult nullResult=null;
//        ComplexManufacturingResult executeResult = saveComplexResultCommand.execute(null);

        //the following does NOT work,
//        Assertions.assertThrows(NullPointerException.class,saveComplexResultCommand.execute(null));
        //this lambda expression style works
        Assertions.assertThrows(NullPointerException.class,()->{saveComplexResultCommand.execute(saveComplexResultCommand,nullResult);});
    }*/

    public void input_simple_manufacturing_result_should_throw_exception(){
//        SimpleManufacturingResult.
    }

/*    @Test
    public void input_complexResult_set_should_save_correctly_into_database(){
        LocalDateTime startTime=LocalDateTime.parse("2022-06-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2022-06-16T13:13");
        firstResult = ComplexManufacturingResult.builder().barcode("first product")
                .productCode(1)
                .featureType("clip")
                .featureName("TP12")
                .stationCode(1)
                .stationChannelNo(1)
                .testItem("TP12 height")
                .result(4.011)
                .operator("AliceTest")
                .startTime(startTime)
                .endTime(endTime)
                .comment("created by SaveComplexResultCommandTests for saveAll")
                .build();
        secondResult=new ComplexManufacturingResult();
        BeanUtils.copyProperties(firstResult,secondResult);
        secondResult.setBarcode("second product");
        thirdResult=new ComplexManufacturingResult();
        BeanUtils.copyProperties(firstResult,thirdResult);
        thirdResult.setBarcode("third product");
        HashSet<ComplexManufacturingResult> resultHashSet = Sets.newHashSet(firstResult, secondResult, thirdResult);
        for (ComplexManufacturingResult result:resultHashSet) {
            System.out.println("result:"+result);
        }
        IManufacturingResultSaveCommand command = commandFactory.getCommand("complexResult");
        Iterable savedSet = commandInvoker.saveAll(command, resultHashSet);
        System.out.println("savedSet:"+savedSet);
    }*/
}//class
