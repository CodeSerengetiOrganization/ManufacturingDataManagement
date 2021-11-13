package com.mytech.savecommand;

import com.mytech.domain.ManufacturingResult;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-11-04
 * @description :
 */
@Data
@Builder
@Component
public class SaveSimpleResultCommand implements IManufactCommand {
    @Override
    public <E extends ManufacturingResult> E execute(E e) {
        return null;
    }
}
