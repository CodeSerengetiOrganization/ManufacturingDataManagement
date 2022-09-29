package com.mytech.service;

import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.domain.ManufacturingResult;
import com.mytech.exception.serviceexception.FolderNotFoundException;
import com.mytech.repository.ComplexResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
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
    public List<ComplexManufacturingResult> saveAll(Iterable<ComplexManufacturingResult> iterable) {
        return complexRepo.saveAll(iterable);
    }

    @Override
    public void delete(ComplexManufacturingResult entity) {
        complexRepo.delete(entity);
    }

    @Override
    public Integer delete(Set<ComplexManufacturingResult> resultSet) {
        complexRepo.deleteAll(resultSet);
        return resultSet.size();//todo:this may not right. as some of the object may not in database
    }

    @Override
    public void deleteByBarcode(String barcode) {
        complexRepo.deleteComplexManufacturingResultByBarcode(barcode);
    }

    @Override
    public void deleteByBarcodeAndFeatureNameAndTestItem(String barcode, String featureName, String testItem) {
        //todo:may not need it.
    }

    @Override
    public int convertLocalFileToDB(String filePath) throws IOException {
        return 0;
    }

    /**
     * this method only throw a ServiceException, just to test if GlobalErrorHandler could catch this exception
     */
    public void throwServiceException(){
        throw FolderNotFoundException.builder()
                .code(98L)
                .message("FolderNotFoundException thrown by Service Layer")
                .build();
    }
}
