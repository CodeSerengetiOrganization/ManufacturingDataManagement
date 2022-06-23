package com.mytech.savecommand;

import com.mytech.domain.ManufacturingResult;
import com.mytech.domain.SimpleManufacturingResult;
import com.mytech.service.SimpleResultService;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-06-22
 * @description :
 */
@Data
@Builder
public class SaveSimpleResultCommand implements IManufacturingResultSaveCommand<SimpleManufacturingResult> {
    private SimpleManufacturingResult simpleResult;
    @Autowired
    private SimpleResultService simpleResultService;

    @Override
    public SimpleManufacturingResult execute() {
        return simpleResultService.save(simpleResult);
    }
}
