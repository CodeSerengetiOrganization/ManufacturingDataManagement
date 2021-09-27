package com.mytech;

import com.mytech.domain.ManufacturingResult;
import com.mytech.domain.ScanResult;
import com.mytech.repository.ManufacturingResultRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-09-24
 * @description :
 */
@SpringBootTest
public class ManufacturingResultRepositoryTests {

    @Autowired
    ManufacturingResultRepository repository;
    private String barcodeToDelete="Ford_U553_CCB_001_test_for_deleting";
    private String featureNameToDelete="TP12";
    private String testItemToDelete="TP12 height";
    @Test
    public void test_save_into_db(){
        LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
        ScanResult scanResult = ScanResult.builder().barcode("Ford_U553_CCB_001_test")
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
                .comment("created by repository test program")
                .build();
        ScanResult saved = repository.save(scanResult);
//        Assertions.()
        Assertions.assertTrue(saved.getBarcode().equals(scanResult.getBarcode()),"barcode from database is not equal to original");
    }//test_save_into_db

    @Test
    public void test_save_manufacturing_result_into_db(){
        LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
        ManufacturingResult manufacturingResult = ManufacturingResult.builder().barcode("Ford_U553_CCB_001_test")
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
                .comment("created by repository test program")
                .build();
        ManufacturingResult saved = repository.save(manufacturingResult);
//        Assertions.()
        Assertions.assertTrue(saved.getBarcode().equals(manufacturingResult.getBarcode()),"barcode from database is not equal to original");
    }

    public void test_findByBarcode(){

    }

    @Test
    public void save_into_db_for_deleting(){
        LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
        ManufacturingResult manufacturingResult = ManufacturingResult.builder().barcode(barcodeToDelete)
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
                .comment("created by repository test program for deleting")
                .build();
        ManufacturingResult saved = repository.save(manufacturingResult);
    }
    @Test
    public void test_deleteByBarcode_manual_check(){
        repository.deleteByBarcode(barcodeToDelete);
    }

    @Test
    public void test_delete_manual_check(){
        repository.deleteByBarcodeAndFeatureNameAndTestItem(barcodeToDelete,featureNameToDelete,testItemToDelete);
    }
}//class
