package com.mytech.savecommand;

import com.google.common.base.Preconditions;
import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.domain.ManufacturingResult;
import com.mytech.service.ComplexResultService;
import lombok.Data;
//import lombok.experimental.Builder;
//import lombok.experimental.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-11-04
 * @description :
 */
@lombok.Builder
@Data
@Component
public class SaveComplexResultCommand<T extends ComplexManufacturingResult> implements IManufacturingResultSaveCommand {
/*    @Autowired
    @Qualifier("complexResultService")
    private ManufacturingResultService manufacturingResultService;*/

    @Autowired
    private ComplexResultService manufacturingResultService;
    @Override
    public <E extends ManufacturingResult> E execute(IManufacturingResultSaveCommand command, E e) {
        Preconditions.checkNotNull(e,"the manufacturing is null when try to execute in SaveComplexResultCommand:"+this.getClass());
        Preconditions.checkArgument((command instanceof SaveComplexResultCommand),"the command is expected to be instance of "+SaveComplexResultCommand.class.getSimpleName()+
                ";but actually it is: "+command.getClass().getSimpleName());
        Preconditions.checkArgument((e instanceof ComplexManufacturingResult),"the object to save is not an instance of "+ ComplexManufacturingResult.class.getName());

        return (E) manufacturingResultService.save((ComplexManufacturingResult)e);
    }

    @Override
    public <E extends ManufacturingResult> Iterable<E> execute(IManufacturingResultSaveCommand command, Iterable<E> it) {
        Preconditions.checkNotNull(it,"the Iterable is null when try to execute in SaveComplexResultCommand:"+this.getClass());
        Preconditions.checkArgument((command instanceof SaveComplexResultCommand),"the command is expected to be instance of "+SaveComplexResultCommand.class.getSimpleName()+
                ";but actually it is: "+command.getClass().getSimpleName());
        Preconditions.checkArgument((it instanceof Iterable<?>),"the input parameter is expected to be an instance of an Iterable; but actually is :"+ it.getClass().getSimpleName());
        int i=0;
        Iterator<E> iterator = it.iterator();
        while(iterator.hasNext()){
            E element = iterator.next();
            if(! (element instanceof ComplexManufacturingResult)) throw new RuntimeException("the index["+i+"] member of input iterable is not instance of "+ComplexManufacturingResult.class.getSimpleName());
            i+=1;

        }
//        return (E) manufacturingResultService.save((ComplexManufacturingResult)it);
        return manufacturingResultService.saveAll(it);
    }
}
