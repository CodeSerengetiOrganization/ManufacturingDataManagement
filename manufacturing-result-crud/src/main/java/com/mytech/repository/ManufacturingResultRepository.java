package com.mytech.repository;

import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.domain.ManufacturingResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-09-24
 * @description :
 */
@NoRepositoryBean
public interface ManufacturingResultRepository extends JpaRepository<ManufacturingResult,Integer> {
//    //1.create
//    <S extends ManufacturingResult> S save(S entity);
//    //2.retrieve
//    <S extends ManufacturingResult> List<S> findByBarcode(String barcode);
//    //3.update
//    //4.delete
////    void delete(ManufacturingResult entity);
////    <S extends ManufacturingResult> void  delete(S entity);

}
