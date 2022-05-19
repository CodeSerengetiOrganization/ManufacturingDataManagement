package com.mytech.repository;

import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.domain.ManufacturingResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-09-24
 * @description :
 */
@NoRepositoryBean
public interface ManufacturingResultRepository<T extends ManufacturingResult> extends JpaRepository<T,Integer> {
//    //1.create
//    <S extends ManufacturingResult> S save(S entity);
//    //2.retrieve
    @Query("SELECT e FROM #{#entityName} as e")
    List<T> findAll();

    @Query("SELECT e FROM #{#entityName} as e")
    Page<T> findAll(Pageable pageable);

    @Query("select t from #{#entityName} as t where t.barcode= :bar")
    List<T> findAllByBarcode(@Param("bar") String barcd);
//    @Query("SELECT FROM #{entityName} ")



//    //3.update
//    //4.delete
////    void delete(ManufacturingResult entity);
////    <S extends ManufacturingResult> void  delete(S entity);

}
