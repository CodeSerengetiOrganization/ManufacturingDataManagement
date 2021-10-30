package com.mytech.service;

import com.mytech.domain.ManufacturingResult;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-09-27
 * @description :
 */
@Service
public interface ManufacturingResultService {
    //1.create
    <S extends ManufacturingResult> S save(S entity);
    //4.delete
    void delete(ManufacturingResult entity);
    @Transactional
    Integer delete(Set<ManufacturingResult> resultSet);
    @Transactional
    void deleteByBarcode(String barcode);
    @Transactional
    void deleteByBarcodeAndFeatureNameAndTestItem(String barcode, String featureName, String testItem);
}
