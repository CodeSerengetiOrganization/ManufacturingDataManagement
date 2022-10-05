package com.mytech;

import com.google.common.collect.Sets;
import com.mytech.domain.SimpleManufacturingResult;
import com.mytech.savecommand.CommandFactory;
import com.mytech.savecommand.IManufacturingResultSaveCommand;
import com.mytech.savecommand.SaveCommandInvoker;
import com.mytech.savecommand.SaveSimpleResultCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashSet;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-06-17
 * @description :
 */
@SpringBootTest
public class SaveSimpleResultCommandTests {
    SimpleManufacturingResult firstResult;
    SimpleManufacturingResult secondResult;
    SimpleManufacturingResult thirdResult;
    @Autowired
    SaveCommandInvoker commandInvoker;
    @Autowired
    CommandFactory commandFactory;

    @Test
    public void given_ManufacturingResult_Set_should_saveAll_manual_check_in_database(){
        LocalDateTime startTime=LocalDateTime.parse("2021-09-16T12:12");
        LocalDateTime endTime=LocalDateTime.parse("2021-09-16T13:13");
        firstResult = SimpleManufacturingResult.builder().barcode("first product")
                .productCode(1)
//                .featureType("clip")
//                .featureName("TP12")
                .stationCode(1)
                .stationChannelNo(1)
//                .testItem("TP12 height")
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
        HashSet<SimpleManufacturingResult> resultHashSet = Sets.newHashSet(firstResult, secondResult, thirdResult);
        for (SimpleManufacturingResult result:resultHashSet) {
            System.out.println("result:"+result);
        }

/*        IManufacturingResultSaveCommand command=commandFactory.getCommand("simpleResult");
        commandInvoker.saveAll(command,resultHashSet);*/

    }
}
