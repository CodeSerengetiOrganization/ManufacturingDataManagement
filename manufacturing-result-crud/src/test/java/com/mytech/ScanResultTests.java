package com.mytech;

import com.mytech.domain.ScanResultComplex;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-09-18
 * @description :
 */
@SpringBootTest
public class ScanResultTests {
    @Test
    public void create_ScannResult_Object_manual_confirm(){
        LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
        ScanResultComplex scanResult = ScanResultComplex.builder().barcode("Ford_U553_CCB_001")
                .productCode(1)
                .featureType("clip")
                .featureName("TP12")
                .stationCode(1)
                .stationChannelNo(1)
                .testItem("TP12 height")
                .testValue(4.011)
                .result(true)
                .operator("Alice")
                .startTime(startTime)
                .endTime(endTime)
                .comment("created by test program")
                .build();
        System.out.println("scanResult:"+scanResult);
    }
}//
