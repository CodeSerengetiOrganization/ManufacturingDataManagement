package com.mytech.repository;

import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-05-03
 * @description :
 */
@Repository
public interface ComplexResultRepository extends ManufacturingResultRepository {
//    @Transactional
//    void deleteByBarcode(String barcode);
//    @Transactional
//    void deleteByBarcodeAndFeatureNameAndTestItem(String barcode, String featureName, String testItem);

    @Transactional
//    @Query("delete from ComplexManufacturingResult c where c.barcode= ?1")
    @Modifying
    @Query("delete from ComplexManufacturingResult c where c.barcode= :barcode")
    void deleteComplexManufacturingResultByBarcode( @Param("barcode") String barcode);

    @Transactional
    @Modifying
    @Query("delete from ComplexManufacturingResult c where c.id in(:ids)")
    void deleteAllById( @Param("ids") Set<Integer> ids);

////    void deleteManufacturingResultsById(Integer id);
//    void deleteComplexManufacturingResultById(Integer id);
}
