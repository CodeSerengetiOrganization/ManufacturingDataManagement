package com.mytech;

import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.savecommand.SaveComplexResultCommand;
import com.mytech.service.ManufacturingResultServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

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
    @Test
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

    }

    @Test
    public void input_manufacturing_result_with_null_should_throw_exception(){
        ComplexManufacturingResult nullResult=null;
//        ComplexManufacturingResult executeResult = saveComplexResultCommand.execute(null);

        //the following does NOT work,
//        Assertions.assertThrows(NullPointerException.class,saveComplexResultCommand.execute(null));
        //this lambda expression style works
        Assertions.assertThrows(NullPointerException.class,()->{saveComplexResultCommand.execute(saveComplexResultCommand,nullResult);});
    }

    public void input_simple_manufacturing_result_should_throw_exception(){
//        SimpleManufacturingResult.
    }
}//class
