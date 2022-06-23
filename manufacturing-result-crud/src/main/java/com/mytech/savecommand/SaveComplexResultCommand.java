package com.mytech.savecommand;

import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.domain.ManufacturingResult;
import com.mytech.service.ComplexResultService;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-06-22
 * @description :
 */
@Data
@Builder
public class SaveComplexResultCommand implements IManufacturingResultSaveCommand<ComplexManufacturingResult>{
    private ComplexManufacturingResult complexResult;
    @Autowired
    private ComplexResultService complexResultService;

    @Override
    public ComplexManufacturingResult execute() {
        return complexResultService.save(complexResult);
    }
}
