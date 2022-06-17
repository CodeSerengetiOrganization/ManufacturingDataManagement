package com.mytech.savecommand;

import com.google.common.base.Preconditions;
import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.domain.ManufacturingResult;
import com.mytech.domain.SimpleManufacturingResult;
import com.mytech.service.SimpleResultService;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-11-04
 * @description :
 */
@Data
@Builder
@Component
public class SaveSimpleResultCommand<T extends ComplexManufacturingResult> implements IManufacturingResultSaveCommand {
    @Autowired
    private SimpleResultService simpleResultService;
    @Override
    public <E extends ManufacturingResult> E execute(IManufacturingResultSaveCommand command, E e) {
        Preconditions.checkNotNull(command,this.getClass().getSimpleName()+": the input arguement command is null");
        Preconditions.checkArgument((command instanceof SaveSimpleResultCommand),
                "class type incorrect, expected:"+ SimpleManufacturingResult.class.getSimpleName()+";" +
                        "actual:"+e.getClass().getSimpleName());
        Preconditions.checkNotNull(e,this.getClass().getSimpleName()+":the object to save is null;");
        Preconditions.checkArgument((e instanceof SimpleManufacturingResult),this.getClass().getSimpleName()+
                ":class type of the object to save incorrect, expected:"+SimpleManufacturingResult.class.getSimpleName()+
                ";actual: "+e.getClass().getSimpleName());
        return (E) simpleResultService.save((SimpleManufacturingResult) e);
    }

    @Override
    public <E extends ManufacturingResult> Iterable<E> execute(IManufacturingResultSaveCommand command, Iterable<E> it) {
        Preconditions.checkNotNull(it,"the Iterable is null when try to execute in SaveComplexResultCommand:"+this.getClass());
        Preconditions.checkArgument((command instanceof SaveSimpleResultCommand),"the command is expected to be instance of "+SaveSimpleResultCommand.class.getSimpleName()+
                ";but actually it is: "+command.getClass().getSimpleName());
        Preconditions.checkArgument((it instanceof Iterable<?>),"the input parameter is expected to be an instance of an Iterable; but actually is :"+ it.getClass().getSimpleName());
        int i=0;
        Iterator<E> iterator = it.iterator();
        while(iterator.hasNext()){
            E element = iterator.next();
            if(! (element instanceof SimpleManufacturingResult)) throw new RuntimeException("the index["+i+"] member of input iterable is not instance of "+ComplexManufacturingResult.class.getSimpleName());
            i+=1;

        }
//        return (E) manufacturingResultService.save((ComplexManufacturingResult)it);
        return simpleResultService.saveAll(it);
    }
}
