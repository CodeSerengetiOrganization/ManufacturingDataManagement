package com.mytech.savecommand;

import com.mytech.domain.ManufacturingResult;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-11-04
 * @description :
 */
public interface IManufactCommand {
    <E extends ManufacturingResult> E execute(E e);
}
