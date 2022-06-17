package com.mytech;

import com.mytech.savecommand.CommandFactory;
import com.mytech.savecommand.IManufacturingResultSaveCommand;
import com.mytech.savecommand.SaveComplexResultCommand;
import com.mytech.savecommand.SaveSimpleResultCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-11-12
 * @description :
 */
@SpringBootTest
public class CommandFactoryTests {
    @Autowired
    CommandFactory commandFactory;
    @Test
    public void test_getCommand_with_simpleResult_string_should_pass(){
        IManufacturingResultSaveCommand simpleResult = commandFactory.getCommand("simpleResult");
        Assertions.assertTrue(simpleResult instanceof SaveSimpleResultCommand,"Incorrect class type: expected:"+SaveSimpleResultCommand.class.getSimpleName()+"; real class type:"+simpleResult.getClass().getSimpleName());
    }

    @Test
    public void test_getCommand_with_complexResult_string_should_pass(){
        IManufacturingResultSaveCommand complexResult = commandFactory.getCommand("complexResult");
        Assertions.assertTrue(complexResult instanceof SaveComplexResultCommand,"Incorrect class type: expected:"+SaveSimpleResultCommand.class.getSimpleName()+"; real class type:"+complexResult.getClass().getSimpleName());

    }
    @Test
    public void test_getCommand_with_incorrect_string_should_return_null(){
        IManufacturingResultSaveCommand incorrectCommand = commandFactory.getCommand("incorrect string");
        Assertions.assertNull(incorrectCommand,"command is not Null");
    }
    @Test
    public void test_getCommand_with_null_string_should_throw_exception(){
//        IManufacturingResultSaveCommand nullCommand = commandFactory.getCommand(null);
        Assertions.assertThrows(NullPointerException.class,()->{commandFactory.getCommand(null);});
    }
}//class
