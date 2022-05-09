package com.mytech;

import com.google.common.collect.Sets;
import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.domain.ScanResultComplex;
import com.mytech.repository.ComplexResultRepository;
import com.mytech.repository.ManufacturingResultRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashSet;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-09-24
 * @description :
 */
@SpringBootTest
public class ComplexManufacturingResultRepositoryTests {

    @Autowired
    ComplexResultRepository repository;
    private String barcodeToDelete="Ford_U553_CCB_001_test_for_deleting";
    private String featureNameToDelete="TP12";
    private String testItemToDelete="TP12 height";
/*    @Test
    public void test_save_into_db(){
        LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
        ScanResultComplex scanResult = ScanResultComplex.builder().barcode("Ford_U553_CCB_001_test")
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
        ScanResultComplex saved = repository.save(scanResult);
//        Assertions.()
        Assertions.assertTrue(saved.getBarcode().equals(scanResult.getBarcode()),"barcode from database is not equal to original");
    }//test_save_into_db*/

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
                .comment("created by complex result repository test program")
                .build();
        ComplexManufacturingResult saved = repository.save(complexManufacturingResult);
//        Assertions.()
        Assertions.assertTrue(saved.getBarcode().equals(complexManufacturingResult.getBarcode()),"barcode from database is not equal to original");
    }

    public void test_findByBarcode(){

    }

    @Test
    public void save_into_db_for_deleting(){
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
                .comment("created by repository test program for deleting")
                .build();
        ComplexManufacturingResult saved = repository.save(complexManufacturingResult);
    }
//    @Test
//    public void test_deleteByBarcode_manual_check(){
//        repository.deleteByBarcode(barcodeToDelete);
//    }
//
//    @Test
//    public void test_delete_manual_check(){
//        repository.deleteByBarcodeAndFeatureNameAndTestItem(barcodeToDelete,featureNameToDelete,testItemToDelete);
//    }

    public void test_save_simple_result_manual_check(){

    }

    @Test
    public void deleteExistingRecordShouldSeeDeletedInDatabase(){
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
                .comment("created by repository test program for deleting")
                .id(10)///this parameter should be read from database
                .build();
        repository.delete(complexManufacturingResult);
    }

    @Test
    public void deleteExistingComplexManufacturingResultRecordByBarcode(){
        repository.deleteComplexManufacturingResultByBarcode(barcodeToDelete);
    }

    @Test
    public void deleteMultipleExistingComplexManufacturingResultRecord(){
        HashSet<Integer> ids = Sets.newHashSet();
        //need to manually read f_id from database, and then build this ids Set
        ids.add(12);
        ids.add(13);
        ids.add(14);
        repository.deleteAllById(ids);
    }

/*    @Test
    public void testdeleteComplexManufacturingResultById(){
        repository.deleteComplexManufacturingResultById(12);
    }*/
}//class
