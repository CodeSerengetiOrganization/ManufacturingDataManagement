package com.mytech.service;

import com.mytech.domain.ManufacturingResult;
import com.mytech.repository.ManufacturingResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-09-27
 * @description :
 */
@Service
public class ManufacturingResultServiceImpl implements ManufacturingResultService {
    @Autowired
    ManufacturingResultRepository repository;
    @Override
    public <S extends ManufacturingResult> S save(S entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(ManufacturingResult entity) {

    }

    @Override
    public void deleteByBarcode(String barcode) {

    }

    @Override
    public void deleteByBarcodeAndFeatureNameAndTestItem(String barcode, String featureName, String testItem) {

    }
}//ManufacturingResultServiceImpl
