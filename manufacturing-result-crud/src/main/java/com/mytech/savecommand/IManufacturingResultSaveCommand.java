package com.mytech.savecommand;

import com.mytech.domain.ManufacturingResult;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-11-04
 * @description :
 */
public interface IManufacturingResultSaveCommand {
    <E extends ManufacturingResult> E execute(IManufacturingResultSaveCommand command, E e);
    <E extends ManufacturingResult> Iterable<E> execute(IManufacturingResultSaveCommand command, Iterable<E> it);
}
