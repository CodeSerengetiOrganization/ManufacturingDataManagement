package com.mytech.savecommand;

import com.google.common.collect.Sets;
import com.mytech.domain.ComplexManufacturingResult;
import com.mytech.domain.ManufacturingResult;
import com.mytech.domain.SimpleManufacturingResult;
import com.mytech.service.ComplexResultService;
import com.mytech.service.SimpleResultService;
import com.mytech.util.BeanContext;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-11-04
 * @description :
 */
@Component
public class CommandFactory {

    public static IManufacturingResultSaveCommand getCommand(ManufacturingResult result){
        SimpleResultService simpleResultServiceBean = BeanContext.getBean(SimpleResultService.class);
        ComplexResultService complexResultServiceBean = BeanContext.getBean(ComplexResultService.class);
/*        if (null== simpleResultServiceBean){
            simpleResultServiceBean=new SimpleResultService();
            System.out.println("new SimpleResultService created by CommandFactory, hashcode:"+simpleResultServiceBean.hashCode());
        }*/
//        BeanContext.getBean()
        if(result instanceof ComplexManufacturingResult){
            return SaveComplexResultCommand.builder().complexResult((ComplexManufacturingResult) result).complexResultService(complexResultServiceBean).build();
        }else if(result instanceof SimpleManufacturingResult){
            return SaveSimpleResultCommand.builder().simpleResult((SimpleManufacturingResult) result).simpleResultService(simpleResultServiceBean).build();
        }else{
            throw new RuntimeException("can not find any IManufacturingResult implement for this ManufacturingResult instance:"+result);
        }
    }

    public static Set<IManufacturingResultSaveCommand> getCommandSet(Set<ManufacturingResult> resultSet){
        if (null==resultSet || resultSet.size()==0) return null;
        HashSet<IManufacturingResultSaveCommand> commandSet= Sets.newHashSet();
        for (ManufacturingResult result:resultSet) {
            commandSet.add(CommandFactory.getCommand(result));
        }
        return commandSet;
    }
}
