package com.mytech.savecommand;

import com.mytech.domain.ManufacturingResult;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-11-04
 * @description :
 */
public interface IManufacturingResultSaveCommand <T extends ManufacturingResult>{
    //execute method for new version of command pattern
    T execute();
//    <E extends ManufacturingResult> Iterable<E> execute(Iterable<IManufacturingResultSaveCommand> itCommand);
}
