package com.mytech.savecommand;

import com.google.common.base.Preconditions;
import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.domain.ManufacturingResult;
import com.mytech.domain.SimpleManufacturingResult;
import com.mytech.service.ManufacturingResultService;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.scanner.ScannerImpl;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-11-04
 * @description :
 */
@Data
@Builder
@Component
public class SaveSimpleResultCommand implements IManufactCommand {
//    @Autowired
//    SaveSimpleResultCommand simpleResultCommand;
    @Autowired
    @Qualifier(value="complexResultServiceImpl")
    ManufacturingResultService service;
    @Override
    public <E extends ManufacturingResult> E execute(IManufactCommand command, E e) {
        Preconditions.checkNotNull(command,this.getClass().getSimpleName()+": the input arguement command is null");
        Preconditions.checkArgument((command instanceof SaveSimpleResultCommand),
                "class type incorrect, expected:"+ SimpleManufacturingResult.class.getSimpleName()+";" +
                        "actual:"+e.getClass().getSimpleName());
        Preconditions.checkNotNull(e,this.getClass().getSimpleName()+":the object to save is null;");
        Preconditions.checkArgument((e instanceof SimpleManufacturingResult),this.getClass().getSimpleName()+
                ":class type of the object to save incorrect, expected:"+SimpleManufacturingResult.class.getSimpleName()+
                ";actual: "+e.getClass().getSimpleName());
        return service.save(e);
    }
}
