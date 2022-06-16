package com.mytech.savecommand;

import com.google.common.base.Preconditions;
import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.domain.ManufacturingResult;
import com.mytech.service.ComplexResultService;
import com.mytech.service.ManufacturingResultService;
import lombok.Builder;
import lombok.Data;
//import lombok.experimental.Builder;
//import lombok.experimental.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-11-04
 * @description :
 */
@lombok.Builder
@Data
@Component
public class SaveComplexResultCommand<T extends ComplexManufacturingResult> implements IManufactCommand {
/*    @Autowired
    @Qualifier("complexResultService")
    private ManufacturingResultService manufacturingResultService;*/

    @Autowired
    private ComplexResultService manufacturingResultService;
    @Override
    public <E extends ManufacturingResult> E execute(IManufactCommand command, E e) {
        Preconditions.checkNotNull(e,"the manufacturing is null when try to execute in SaveComplexResultCommand:"+this.getClass());
        Preconditions.checkArgument((command instanceof SaveComplexResultCommand),"the command is expected to be instance of "+SaveComplexResultCommand.class.getSimpleName()+
                ";but actually it is: "+command.getClass().getSimpleName());
        Preconditions.checkArgument((e instanceof ComplexManufacturingResult),"the object to save is not an instance of "+ ComplexManufacturingResult.class.getName());

        return (E) manufacturingResultService.save((ComplexManufacturingResult)e);
    }
}
