package com.mytech.savecommand;

import com.google.common.collect.Lists;
import com.mytech.domain.ManufacturingResult;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-11-04
 * @description :
 */
@Data
@Component
public class SaveCommandInvoker {

    //todo: necessary to use polymorphism here? what about this:
    //public List<ManufacturingResult> saveResult(Iterable<IManufacturingResultSaveCommand> commands)
/*    public <E extends ManufacturingResult> List<E> saveResult(Iterable<IManufacturingResultSaveCommand> commands){
        List<E> savedList= Lists.newArrayList();
        for (IManufacturingResultSaveCommand command: commands) {
            ManufacturingResult savedResult = command.execute();
            savedList.add((E) savedResult);
        }
        return savedList;
    }*/

    public List<ManufacturingResult> saveResult(Iterable<IManufacturingResultSaveCommand> commands){
        List<ManufacturingResult> savedList= Lists.newArrayList();
        for (IManufacturingResultSaveCommand command: commands) {
            ManufacturingResult savedResult = command.execute();
            savedList.add( savedResult);
        }
        return savedList;
    }
}
