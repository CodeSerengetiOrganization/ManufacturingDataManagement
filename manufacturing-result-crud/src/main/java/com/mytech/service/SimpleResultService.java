package com.mytech.service;

import com.mytech.domain.SimpleManufacturingResult;
import com.mytech.repository.SimpleResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-06-16
 * @description :
 */
@Service("simpleService")
public class SimpleResultService<T extends SimpleManufacturingResult> implements ManufacturingResultService<SimpleManufacturingResult>{

    @Autowired
    SimpleResultRepository simpleRepo;
    @Override
    public SimpleManufacturingResult save(SimpleManufacturingResult entity) {
        return simpleRepo.save(entity);
    }

    @Override
    public List<SimpleManufacturingResult> saveAll(Iterable<SimpleManufacturingResult> iterable) {
        return simpleRepo.saveAll(iterable);
    }

    @Override
    public void delete(SimpleManufacturingResult entity) {
        simpleRepo.delete(entity);
    }

    @Override
    public Integer delete(Set<SimpleManufacturingResult> resultSet) {
        simpleRepo.deleteAll(resultSet);
        return resultSet.size();
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

