package com.mytech.savecommand;

import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.domain.ManufacturingResult;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-11-04
 * @description :
 */
@Data
@Component
public class SaveCommandInvoker {
/*    @Autowired
    private IManufactCommand saveCommand;

    public SaveCommandInvoker(IManufactCommand saveCommand) {
        this.saveCommand = saveCommand;
    }*/

    public <E extends ManufacturingResult> E saveManufacturingResult2(IManufactCommand command, E manufacturingResult){
        return (E) command.execute(command,manufacturingResult);
    }
}
