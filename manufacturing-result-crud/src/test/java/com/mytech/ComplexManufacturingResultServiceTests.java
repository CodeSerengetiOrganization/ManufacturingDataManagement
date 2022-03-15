package com.mytech;

import com.google.common.collect.Sets;
import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.domain.ManufacturingResult;
import com.mytech.repository.ManufacturingResultRepository;
import com.mytech.service.ManufacturingResultServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashSet;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-10-06
 * @description :
 */
@SpringBootTest
public class ComplexManufacturingResultServiceTests {

    @Autowired
    ManufacturingResultServiceImpl resultService;
    @Autowired
    ManufacturingResultRepository resultRepository;
    String barcodeToDelete="barcodeToDelete";
    //for group delete
    ComplexManufacturingResult firstResult;
    ComplexManufacturingResult secondResult;
    ComplexManufacturingResult thirdResult;


    @Test
    public void prepare_result_for_deleting(){
        LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
        ComplexManufacturingResult complexManufacturingResult = ComplexManufacturingResult.builder().barcode(barcodeToDelete)
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
                .comment("created by service test program for deleting")
                .build();
        ComplexManufacturingResult saved = resultService.save(complexManufacturingResult);
    }

    @Test
    public void test_delete_result_manual_check(){
        LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
        ComplexManufacturingResult resultToDelete = ComplexManufacturingResult.builder().barcode(barcodeToDelete)
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
                .comment("created by service test program for deleting")
                .build();
        System.out.println("resultToDelete:"+resultToDelete);
        resultService.delete(resultToDelete);
    }

    @Test
    public void prepare_for_deleting_multiple_results(){
        LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
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
                .comment("created by service test program for deleting")
                .build();
        System.out.println("firstResult Hashcode:"+ firstResult.hashCode());
        resultRepository.saveAndFlush(firstResult);

        secondResult=new ComplexManufacturingResult();
        BeanUtils.copyProperties(firstResult,secondResult);
        secondResult.setBarcode("second product");
        System.out.println("secondResult Hashcode:"+secondResult.hashCode());
        resultRepository.saveAndFlush(secondResult);

        thirdResult=new ComplexManufacturingResult();
        BeanUtils.copyProperties(firstResult,thirdResult);
        thirdResult.setBarcode("third product");
        System.out.println("thirdResult Hashcode:"+thirdResult.hashCode());
        resultRepository.saveAndFlush(thirdResult);

    }

    @Test
    public void prepare_for_deleting_multiple_results2(){
        LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
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
                .comment("created by service test program for deleting")
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
        resultRepository.saveAll(resultHashSet);
    }
    @Test
    public void deleting_multiple_results_manual_check(){
        LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
        firstResult = ComplexManufacturingResult.builder().barcode("first product")
                .productCode(1)
                .featureType("clip")
                .featureName("TP12")
                .stationCode(1)
                .stationChannelNo(1)
                .testItem("TP12 height")
                .result(4.011)
                .operator("AliceTest")
                .comment("created by service test program for deleting")
                .build();
        secondResult=new ComplexManufacturingResult();
        BeanUtils.copyProperties(firstResult,secondResult);
        secondResult.setBarcode("second product");
        thirdResult=new ComplexManufacturingResult();
        BeanUtils.copyProperties(firstResult,thirdResult);
        thirdResult.setBarcode("third product");
        HashSet<ManufacturingResult> resultSetToDelete = Sets.newHashSet(firstResult, secondResult, thirdResult);
        Integer deleteCount = resultService.delete(resultSetToDelete);
        Assertions.assertEquals(3,deleteCount);
    }

}
