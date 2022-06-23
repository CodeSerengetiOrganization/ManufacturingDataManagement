package com.mytech;

import com.google.common.collect.Sets;
import com.mytech.domain.ManufacturingResult;
import com.mytech.domain.SimpleManufacturingResult;
import com.mytech.savecommand.CommandFactory;
import com.mytech.savecommand.IManufacturingResultSaveCommand;
import com.mytech.savecommand.SaveComplexResultCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-11-12
 * @description :
 */
@SpringBootTest
public class CommandFactoryTests {
/*    @Autowired
    CommandFactory commandFactory;
    @Test
    public void test_getCommand_with_simpleResult_string_should_pass(){
        IManufacturingResultSaveCommand simpleResult = commandFactory.getCommand("simpleResult");
        Assertions.assertTrue(simpleResult instanceof SaveSimpleResultCommandBackup,"Incorrect class type: expected:"+SaveSimpleResultCommandBackup.class.getSimpleName()+"; real class type:"+simpleResult.getClass().getSimpleName());
    }

    @Test
    public void test_getCommand_with_complexResult_string_should_pass(){
        IManufacturingResultSaveCommand complexResult = commandFactory.getCommand("complexResult");
        Assertions.assertTrue(complexResult instanceof SaveComplexResultCommand,"Incorrect class type: expected:"+SaveSimpleResultCommandBackup.class.getSimpleName()+"; real class type:"+complexResult.getClass().getSimpleName());

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
    }*/

    SimpleManufacturingResult firstResult;
    SimpleManufacturingResult secondResult;
    SimpleManufacturingResult thirdResult;
    @Test
    public void given_a_SimpleManufacturingResult_should_return_a_command_with_simpleResultService(){
        LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
        SimpleManufacturingResult firstResult = SimpleManufacturingResult.builder().barcode("first product")
                .productCode(1)
                .featureType("clip")
                .featureName("TP12")
                .stationCode(1)
                .stationChannelNo(1)
                .testItem("TP12 height")
                .result(true)
                .operator("AliceTest")
                .startTime(startTime)
                .endTime(endTime)
                .comment("created by service test program for saveAll")
                .build();
        IManufacturingResultSaveCommand command = CommandFactory.getCommand(firstResult);
        System.out.println("command in test:"+command);
    }

    @Test
    public void given_SimpleManufacturingResult_Set_should_return_a_set_of_command_with_simpleResultService(){
        LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
        firstResult = SimpleManufacturingResult.builder().barcode("first product")
                .productCode(1)
                .featureType("clip")
                .featureName("TP12")
                .stationCode(1)
                .stationChannelNo(1)
                .testItem("TP12 height")
                .result(true)
                .operator("AliceTest")
                .startTime(startTime)
                .endTime(endTime)
                .comment("created by service test program for saveAll")
                .build();
        secondResult = new SimpleManufacturingResult();
        BeanUtils.copyProperties(firstResult,secondResult);
        secondResult.setBarcode("second product");
        thirdResult=new SimpleManufacturingResult();
        BeanUtils.copyProperties(firstResult,thirdResult);
        thirdResult.setBarcode("third product");
        HashSet<ManufacturingResult> resultHashSet = Sets.newHashSet(firstResult, secondResult, thirdResult);
        Set<IManufacturingResultSaveCommand> commandSet = CommandFactory.getCommandSet(resultHashSet);

        for (IManufacturingResultSaveCommand command:commandSet) {
            System.out.println("command set:"+command);
        }
    }
}//class
