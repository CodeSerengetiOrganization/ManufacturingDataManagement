package com.mytech.repository;

import com.mytech.domain.ManufacturingResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-09-24
 * @description :
 */
public interface ManufacturingResultRepository extends JpaRepository<ManufacturingResult,Integer> {
    //1.create
    <S extends ManufacturingResult> S save(S entity);
    //2.retrieve
    <S extends ManufacturingResult> List<S> findByBarcode(String barcode);
    //3.update
    //4.delete
    void delete(ManufacturingResult entity);
    @Transactional
    void deleteByBarcode(String barcode);
    @Transactional
    void deleteByBarcodeAndFeatureNameAndTestItem(String barcode, String featureName, String testItem);
}
