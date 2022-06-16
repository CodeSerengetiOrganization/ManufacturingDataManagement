package com.mytech.service;

import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.domain.ManufacturingResult;
import com.mytech.repository.ComplexResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

/*
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-06-15
 * @description :
 * */


@Service(value="complexResultService")
public class ComplexResultService<T extends ComplexManufacturingResult> implements ManufacturingResultService<ComplexManufacturingResult> {
    @Autowired
    ComplexResultRepository complexRepo;


    @Override
    public ComplexManufacturingResult save(ComplexManufacturingResult entity) {
        return complexRepo.save(entity);
    }

    @Override
    public void delete(ComplexManufacturingResult entity) {

    }

    @Override
    public Integer delete(Set<ComplexManufacturingResult> resultSet) {
        return null;
    }

    @Override
    public void deleteByBarcode(String barcode) {

    }

    @Override
    public void deleteByBarcodeAndFeatureNameAndTestItem(String barcode, String featureName, String testItem) {

    }

    @Override
    public int convertLocalFileToDB(String filePath) throws IOException {
        return 0;
    }
}
