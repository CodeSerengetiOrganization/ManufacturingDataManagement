
package com.mytech;

import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.service.ComplexResultService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-06-15
 * @description :
 */

@SpringBootTest
public class ComplexResultServiceTests {
    @Autowired
    ComplexResultService complexResultService;
    @Test
    public void test_save_manufacturing_result_into_db(){
        LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
        ComplexManufacturingResult complexManufacturingResult = ComplexManufacturingResult.builder().barcode("Ford_U553_CCB_001_test")
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
                .comment("created by ComplexResultService test program")
                .build();
        System.out.println(complexManufacturingResult);
        System.out.println("complexResultService:"+complexResultService);
        ComplexManufacturingResult saved = complexResultService.save(complexManufacturingResult);
//        Assertions.()
        Assertions.assertTrue(saved.getBarcode().equals(complexManufacturingResult.getBarcode()),"barcode from database is not equal to original");
    }
}

