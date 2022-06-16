package com.mytech.service;/*
package com.mytech.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.domain.ManufacturingResult;
import com.mytech.repository.ComplexResultRepository;
import com.mytech.repository.ManufacturingResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

*/

import com.mytech.domain.ManufacturingResult;
import com.mytech.repository.ComplexResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-09-27
 * @description :
 */

@Service(value="complexResultServiceImpl")
public class ManufacturingResultServiceImpl<T extends ManufacturingResult> implements ManufacturingResultService {
    @Autowired
    ComplexResultRepository repository;

//    @Override
//    public <S extends ManufacturingResult> S save(S entity) {
//        return null;
//    }

    @Override
    public ManufacturingResult save(ManufacturingResult entity) {
        return null;
    }

    @Override
    public void delete(ManufacturingResult entity) {
//        Preconditions.checkNotNull(entity,"entity to delete is null");
//        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
//                .withIgnoreNullValues()
//                .withIgnorePaths("id")
//                .withIgnorePaths("startTime")
//                .withIgnorePaths("endTime");
//        Example<ManufacturingResult> resultExample = Example.of(entity,exampleMatcher);
//        Optional<ManufacturingResult> optionalResult = repository.findOne(resultExample);
//        if (optionalResult.isPresent()) repository.delete(optionalResult.get());

    }



    @Override
    public Integer delete(Set resultSet) {
        /*
        Preconditions.checkNotNull(resultSet,"service found:the result set to delete is null");

         HashSet<ManufacturingResult> resultHashSet = Sets.newHashSet();
       for (ManufacturingResult result: resultSet) {
           if(result==null) continue;
           Example<ManufacturingResult> resultExample = Example.of(result);
           Optional<ManufacturingResult> oneResult = repository.findOne(resultExample);
           if (oneResult.isPresent()) resultHashSet.add(oneResult.get());
        }
       System.out.println("resultHashSet:"+resultHashSet);
        repository.deleteAll(resultHashSet);*/

//        if (resultSet.contains(null)) throw new NullPointerException("service found: at least one of the ComplexManufacturingResult instance to delete is null");
//        repository.deleteAll(resultSet);
        return resultSet.size();

    }

    @Override
    public void deleteByBarcode(String barcode) {

    }

    @Override
    public void deleteByBarcodeAndFeatureNameAndTestItem(String barcode, String featureName, String testItem) {

    }

//    @Override
//    public void deleteByBarcode(String barcode) {
//        repository.deleteByBarcode(barcode);
//    }
//
//    @Override
//    public void deleteByBarcodeAndFeatureNameAndTestItem(String barcode, String featureName, String testItem) {
//        repository.deleteByBarcodeAndFeatureNameAndTestItem(barcode, featureName, testItem);
//    }

//    This method is to extract test items and test results from local test file(ie, created by scanner)
//     @para filePath: the path of text file from which to extract test results
//

    @Override
    @Deprecated //this is a business functionality, should in other module
    public int convertLocalFileToDB(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader= new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        String[] split = line.split(":");
        for (String item :split) {
            System.out.print(item);
        }
        return 1;
    }
}//ManufacturingResultServiceImpl
