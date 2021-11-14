package com.mytech;

import com.mytech.domain.SimpleManufacturingResult;
import com.mytech.savecommand.IManufactCommand;
import com.mytech.savecommand.SaveCommandInvoker;
import com.mytech.savecommand.SaveSimpleResultCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-11-13
 * @description :
 */
@SpringBootTest
public class SaveCommandInvokerTests {
    @Autowired
    SaveCommandInvoker invoker;
    @Test
    public void input_saveSimpleResultCommand_and_SimpleResult_should_return_saved_object(){
        IManufactCommand simpleCommand=new SaveSimpleResultCommand();
        SimpleManufacturingResult simpleResult=SimpleManufacturingResult.builder().build();
        invoker.saveManufacturingResult2(simpleCommand,simpleResult);
    }//

    @Test
    public void input_saveComplexResultCommand_and_ComplexResult_should_return_saved_object(){

    }//

    @Test
    public void input_NullCommand_and_SimpleResult_should_throw_exception(){

    }//

    @Test
    public void input_saveSimpleResultCommand_and_NullResult_should_throw_exception(){

    }//

    @Test
    public void input_saveSimpleResultCommand_and_ComplexResult_should_throw_exception(){

    }//

}//class
