package com.mytech;

import com.google.common.collect.Sets;
import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.domain.ManufacturingResult;
import com.mytech.repository.ComplexResultRepository;
import com.mytech.repository.ManufacturingResultRepository;
import com.mytech.service.ComplexResultService;
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
public class ComplexResultServiceTests {

    @Autowired
    ComplexResultService resultService;
    @Autowired
    ComplexResultRepository resultRepository;
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
        ComplexManufacturingResult saved = (ComplexManufacturingResult) resultService.save(complexManufacturingResult);
    }

    // To delete the record in database, it is needed to read the id in database and manually input in the id field.
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
                .id(16)//this need to be read from database
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
        System.out.println("firstResult:"+ firstResult);
        resultRepository.save(firstResult);

        secondResult=new ComplexManufacturingResult();
        BeanUtils.copyProperties(firstResult,secondResult);
        secondResult.setBarcode("second product");
        System.out.println("secondResult Hashcode:"+secondResult.hashCode());
        System.out.println("secondResult:"+ secondResult);
        resultRepository.save(secondResult);

        thirdResult=new ComplexManufacturingResult();
        BeanUtils.copyProperties(firstResult,thirdResult);
        thirdResult.setBarcode("third product");
        System.out.println("thirdResult Hashcode:"+thirdResult.hashCode());
        System.out.println("thirdResult:"+ thirdResult);
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

    // To delete the record in database, it is needed to read the id in database and manually input in the id field.
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

    @Test
    public void given_ManufacturingResult_Set_should_saveAll_manual_check_in_database(){
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
                .comment("created by service test program for saveAll")
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
        resultService.saveAll(resultHashSet);

    }

}
