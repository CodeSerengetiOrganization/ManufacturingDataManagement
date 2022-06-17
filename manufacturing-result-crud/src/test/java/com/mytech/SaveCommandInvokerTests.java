package com.mytech;

import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.domain.SimpleManufacturingResult;
import com.mytech.savecommand.SaveCommandInvoker;
import com.mytech.savecommand.SaveComplexResultCommand;
import com.mytech.savecommand.SaveSimpleResultCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-11-13
 * @description :
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SaveCommandInvokerTests {
    @Autowired
    SaveCommandInvoker invoker;
    @Mock
    SaveSimpleResultCommand simpleCommand;
    @Mock
    SaveComplexResultCommand complexCommand;

    @Test
    public void input_saveSimpleResultCommand_and_SimpleResult_should_return_saved_object(){

        SimpleManufacturingResult simpleResult=SimpleManufacturingResult.builder().build();
        BDDMockito.given(simpleCommand.execute(simpleCommand,simpleResult)).willReturn(simpleResult);

        SimpleManufacturingResult returnedResult = invoker.saveManufacturingResult2(simpleCommand, simpleResult);
        Assertions.assertEquals(simpleResult,returnedResult);
    }//

    @Test
    public void input_saveComplexResultCommand_and_ComplexResult_should_return_saved_object(){
        ComplexManufacturingResult complexManufacturingResult=ComplexManufacturingResult.builder().build();
        ComplexManufacturingResult returnedResult = invoker.saveManufacturingResult2(complexCommand, complexManufacturingResult);
        Assertions.assertEquals(complexManufacturingResult,returnedResult);
    }//

    @Test
    public void input_NullCommand_and_SimpleResult_should_throw_exception(){
        SimpleManufacturingResult simpleResult=SimpleManufacturingResult.builder().build();
        Assertions.assertThrows(RuntimeException.class,()->{invoker.saveManufacturingResult2(null,simpleResult);});
    }//

    @Test
    public void input_saveSimpleResultCommand_and_NullResult_should_throw_exception(){
        Assertions.assertThrows(RuntimeException.class,()->{invoker.saveManufacturingResult2(simpleCommand,null);});
    }//

    @Test
    public void input_saveSimpleResultCommand_and_ComplexResult_should_throw_exception(){
        ComplexManufacturingResult complexManufacturingResult=ComplexManufacturingResult.builder().build();
        Assertions.assertThrows(RuntimeException.class,()->{invoker.saveManufacturingResult2(simpleCommand,complexManufacturingResult);});
    }//

}//class
